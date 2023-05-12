import datetime
import decimal
from decimal import Decimal
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
    print(quote.to_dict())
    return quote.to_dict()


if __name__ == '__main__':
    app.run()
