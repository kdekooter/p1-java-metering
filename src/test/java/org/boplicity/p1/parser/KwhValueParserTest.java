package org.boplicity.p1.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class KwhValueParserTest {
    @Test
    public void testParse() throws Exception {
        KwhValueParser parser = new KwhValueParser();
        Assert.assertEquals(parser.parse("1-0:1.8.1(00038.113*kWh)"), new BigDecimal("38.113"));
    }
}
