package org.boplicity.p1.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class CubicMetreValueParserTest {
    @Test
    public void testParse() throws Exception {
        ValueParser parser = new CubicMetreValueParser();
//        Assert.assertEquals(parser.parseCurrentValues("0-1:24.3.0(130206140000)(00)(60)(1)(0-1:24.2.0)(m3)(00047.057)"), new BigDecimal("47.057"));
        Assert.assertEquals(parser.parse("(00047.057)"), new BigDecimal("47.057"));
    }
}
