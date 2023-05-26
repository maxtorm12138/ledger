import datetime
import decimal
import json
import os
import time
import urllib.request

import requests_cache
import urllib3
from flask import Flask, request
import akshare as ak
import yfinance as yf

app = Flask(__name__)


class Quote:
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


@app.route('/fund_quote')
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


@app.route('/security_quote')
def get_security_quote():
    code = request.args.get('code')

    quote = Quote()

    http = urllib3.PoolManager()
    if os.environ['PROXY_URL']:
        http = urllib3.ProxyManager(proxy_url=os.environ['PROXY_URL'])

    http_request = http.request(method='GET',
                                url='https://query2.finance.yahoo.com/v11/finance/quoteSummary/{}?modules=price,summaryDetail,defaultKeyStatistics'.format(
                                    code))

    if http_request.status != 200:
        quote.success = False
        quote.errormsg = 'http fail {}'.format(http_request.status)
        return quote.to_dict()

    data = json.loads(http_request.data.decode('utf-8'))
    data = data['quoteSummary']['result'][0]['price']
    app.logger.debug('response: %s', data)

    quote.currency = data['currency']
    quote.last = data['regularMarketPrice']['raw']
    quote.isodate = datetime.datetime.fromtimestamp(data['regularMarketTime'])

    quote.success = True

    app.logger.info(quote.to_dict())
    return quote.to_dict()


@app.route('/currency_quote')
def get_currency_quote():
    currency_rate = CurrencyRate()
    from_currency = request.args.get('from')
    to_currency = request.args.get('to')

    http = urllib3.PoolManager()
    if os.environ['PROXY_URL']:
        http = urllib3.ProxyManager(proxy_url=os.environ['PROXY_URL'])

    http_request = http.request(method='GET',
                                url='https://query2.finance.yahoo.com/v11/finance/quoteSummary/{}{}=X?modules=price,summaryDetail,defaultKeyStatistics'.format(
                                    from_currency, to_currency))
    if http_request.status != 200:
        currency_rate.success = False
        currency_rate.errormsg = 'http fail {}'.format(http_request.status)
        return currency_rate.to_dict()

    data = json.loads(http_request.data.decode('utf-8'))
    data = data['quoteSummary']['result'][0]['price']
    app.logger.debug('response: %s', data)

    currency_rate.from_amount = 1.0
    currency_rate.to_amount = data['regularMarketPrice']['raw']
    currency_rate.isodate = datetime.datetime.fromtimestamp(data['regularMarketTime'])
    currency_rate.success = True

    app.logger.info(currency_rate.to_dict())
    return currency_rate.to_dict()


if __name__ == '__main__':
    app.run()
