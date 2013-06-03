package org.boplicity.p1.device;

import org.boplicity.p1.model.SmartMeterMeasurement;
import org.boplicity.p1.parser.DatagramParser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartMeterListenerImpl implements SmartMeterListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SmartMeterMeasurement currentMeasurement;

    private DatagramParser datagramParser;

    public SmartMeterListenerImpl() {
        datagramParser = new DatagramParser();
    }

    @Override
    public void put(String datagram) {

        if (logger.isTraceEnabled()) {
            logger.trace(datagram);
        }

        SmartMeterMeasurement measurement = datagramParser.parse(datagram);
        measurement.setTimestamp(new DateTime());

        currentMeasurement = measurement;
    }

    @Override
    public SmartMeterMeasurement getCurrentMeasurement() {
        return currentMeasurement;
    }
}
