import datetime
import decimal
import json
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
    success: bool = None
    errormsg: str = ''

    def to_dict(self) -> dict:
        d = {
            'name': self.name,
            'last': self.last,
            'isodate': self.isodate.strftime('%Y-%m-%d'),
            'nav': self.nav,
            'currency': self.currency,
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

    http = urllib3.ProxyManager(proxy_url='http://slave-router.lan:7893')
    http_request = http.request(method='GET', url='https://query1.finance.yahoo.com/v6/finance/quote?symbols=' + code)
    if http_request.status != 200:
        quote.success = False
        quote.errormsg = 'http fail %d'.format(http_request.status)
        return quote.to_dict()

    data = json.loads(http_request.data.decode('utf-8'))
    data = data['quoteResponse']['result'][0]

    quote.currency = data['currency']
    quote.last = data['regularMarketPrice']
    quote.isodate = datetime.datetime.fromtimestamp(data['regularMarketTime'])

    quote.success = True

    return quote.to_dict()

@app.route('/currency_quote')
def get_currency_quote():
    pass

if __name__ == '__main__':
    app.run()
