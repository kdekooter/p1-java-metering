package org.boplicity.p1.parser;

import org.boplicity.p1.model.SmartMeterMeasurement;
import org.boplicity.p1.testutil.TestObjectFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DatagramParserTest {

    private DatagramParser parser;

    @BeforeClass
    public void init() {
        parser = new DatagramParser();
    }

    @Test
    public void testParse() throws Exception {

        SmartMeterMeasurement reference = new SmartMeterMeasurement();
//        reference.setTimestamp();
        reference.setCurrentPowerProductionW(new BigDecimal(550));
        reference.setCurrentPowerConsumptionW(BigDecimal.ZERO);
        reference.setElectricityConsumptionNormalRateKwh(new BigDecimal("26.006"));
        reference.setElectricityConsumptionLowRateKwh(new BigDecimal("38.113"));
        reference.setElectricityProductionNormalRateKwh(new BigDecimal("6.696"));
        reference.setElectricityProductionLowRateKwh(new BigDecimal("1.104"));
        reference.setGasConsumptionM3(new BigDecimal("47.057"));

        SmartMeterMeasurement result = parser.parse(TestObjectFactory.getTestDatagram());

        Assert.assertEquals(result, reference);
    }
}
