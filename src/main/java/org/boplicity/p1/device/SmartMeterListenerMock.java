package org.boplicity.p1.device;

import org.boplicity.p1.model.SmartMeterMeasurement;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Random;

public class SmartMeterListenerMock implements SmartMeterListener {

    private SmartMeterMeasurement smartMeterMeasurement;
    private Random random = new Random();

    public void init() {
        smartMeterMeasurement = new SmartMeterMeasurement();
        smartMeterMeasurement.setTimestamp(new DateTime());
        smartMeterMeasurement.setCurrentPowerProductionW(new BigDecimal(100));
        smartMeterMeasurement.setCurrentPowerProductionW(new BigDecimal(0));

        loadDummyData();
    }

    private void loadDummyData() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(10000);
                    smartMeterMeasurement.setTimestamp(new DateTime());
                    smartMeterMeasurement.setCurrentPowerConsumptionW(new BigDecimal(random.nextInt(2500)));
                    smartMeterMeasurement.setCurrentPowerProductionW(new BigDecimal(random.nextInt(1600)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public void put(String datagram) {
    }

    @Override
    public SmartMeterMeasurement getCurrentMeasurement() {
        return smartMeterMeasurement;
    }
}
