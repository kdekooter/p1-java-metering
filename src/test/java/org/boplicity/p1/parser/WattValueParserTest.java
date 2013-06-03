package org.boplicity.p1.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class WattValueParserTest {
    @Test
    public void testParse() throws Exception {
        WattValueParser parser = new WattValueParser();
        Assert.assertEquals(parser.parse("1-0:1.7.0(0000.55*kW)"), new BigDecimal("550"));
    }
}
