package org.maxtorm.ledger.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class LedgerDecimal {
    public static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.HALF_UP);
    public static final BigDecimal ZERO = new BigDecimal(BigInteger.ZERO, 5, MATH_CONTEXT);

}
