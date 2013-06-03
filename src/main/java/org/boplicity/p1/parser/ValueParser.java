package org.boplicity.p1.parser;

import java.math.BigDecimal;

public interface ValueParser {
    BigDecimal parse(String value);
}
