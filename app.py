import datetime
import decimal
import json
import os

import urllib3
from flask import Flask, request
import akshare as ak
from lxml import etree
import re

app = Flask(__name__)


class Quote:
    def __init__(self):
        pass

    name: str = None
    last: float = None
    isodate: datetime.date = None
    nav: float = None
    currency: str = None
    success: bool = False
    errormsg: str = ''

    def to_dict(self) -> dict:
        d = {
            'name': self.name,
            'last': self.last,
            'isodate': self.isodate.strftime('%Y-%m-%d') if self.isodate is not None else None,
            'nav': self.nav,
            'currency': self.currency,
            'success': self.success,
            'errormsg': self.errormsg
        }
        return d


class CurrencyRate:
    from_amount: float = None
    to_amount: float = None
    isodate: datetime.date = None
    success: bool = False
    errormsg: str = ''

    def to_dict(self) -> dict:
        d = {
            'from_amount': self.from_amount,
            'to_amount': self.to_amount,
            'isodate': self.isodate.strftime('%Y-%m-%d') if self.isodate is not None else None,
            'success': self.success,
            'errormsg': self.errormsg
        }
        return d


@app.route('/fund')
def get_fund_quote():
    code = request.args.get('code')
    quote = Quote()
    # get fund name
    # get fund history quotes
    nav_all = ak.fund_open_fund_info_em(code, '单位净值走势')
    last_nav = nav_all.tail(n=1)
    quote.nav = float(decimal.Decimal.from_float(last_nav.iloc[0][1]).quantize(decimal.Decimal('0.00000')))
    quote.isodate = last_nav.iloc[0][0]
    quote.currency = 'CNY'
    quote.success = True
    app.logger.info(quote.to_dict())
    return quote.to_dict()


@app.route('/security')
def get_security_quote():
    code = request.args.get('code').upper()
    quote = Quote()
    http = urllib3.PoolManager()
    if os.environ.get('PROXY_URL'):
        http = urllib3.ProxyManager(proxy_url=os.environ['PROXY_URL'])

    http_request = http.request(method='GET',
                                url='https://finance.yahoo.com/quote/{}/history?p={}'.format(
                                    code, code), headers={'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36'})
    if http_request.status != 200:
        quote.success = False
        quote.errormsg = 'http fail {}'.format(http_request.status)
        return quote.to_dict()

    data = etree.fromstring(http_request.data.decode('utf-8'), etree.HTMLParser())
    quote.name = re.search('^(.+) \(([^)]+)\)', data.xpath('//*[@id="quote-header-info"]//div//h1')[0].text).group(1)
    quote.currency = re.search('^(.+)[.] Currency in (.+)$', data.xpath('//*[@id="quote-header-info"]//div//span')[0].text).group(2)
    quote.last = float(data.xpath('//*[@data-symbol="{}"][@data-field="regularMarketPrice"]'.format(code))[0].text)
    quote.isodate = datetime.datetime.strptime(data.xpath('//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[1]/td[1]/span')[0].text, '%b %d, %Y')
    quote.success = True

    return quote.to_dict()


@app.route('/currency')
def get_currency_quote():
    currency_rate = CurrencyRate()
    from_currency = request.args.get('from')
    to_currency = request.args.get('to')

    data_symbol = '{}{}=X'.format(from_currency, to_currency).upper()
    if data_symbol == 'USDCNY=X':
        data_symbol = 'CNY=X'

    http = urllib3.PoolManager()
    if os.environ.get('PROXY_URL'):
        http = urllib3.ProxyManager(proxy_url=os.environ['PROXY_URL'])

    http_request = http.request(method='GET',
                                url='https://finance.yahoo.com/quote/{}/history?p={}'.format(data_symbol,data_symbol), headers={'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36'})

    if http_request.status != 200:
        currency_rate.success = False
        currency_rate.errormsg = 'http fail {}'.format(http_request.status)
        return currency_rate.to_dict()

    data = etree.fromstring(http_request.data.decode('utf-8'), etree.HTMLParser())
    currency_rate.from_amount = 1.0
    currency_rate.to_amount = float(data.xpath('//*[@data-symbol="{}"][@data-field="regularMarketPrice"]'.format(data_symbol))[0].text)
    currency_rate.isodate = datetime.datetime.strptime(data.xpath('//*[@id="Col1-1-HistoricalDataTable-Proxy"]/section/div[2]/table/tbody/tr[1]/td[1]/span')[0].text, '%b %d, %Y')
    currency_rate.success = True

    return currency_rate.to_dict()


if __name__ == '__main__':
    app.run()
