package org.boplicity.p1.device;

import org.boplicity.p1.model.SmartMeterMeasurement;

public interface SmartMeterListener {
    void put(String datagram);

    SmartMeterMeasurement getCurrentMeasurement();
}
