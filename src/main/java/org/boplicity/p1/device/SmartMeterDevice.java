package org.boplicity.p1.device;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class SmartMeterDevice implements SerialPortEventListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SerialPort serialPort;
    private String portName = "/dev/ttyUSB0";
    private ByteBuffer readByteBuffer;
    private final int START_CHARACTER = 47; // "/"
    private final int FINISH_CHARACTER = 33; // "!";

    private SmartMeterListener smartMeterListener;

    protected int maxBufferSize = 1024;

    public void init() {

        logger.info("Initializing SmartMeterDevice");

        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();

        int count = 0;

        while (portList.hasMoreElements()) {
            count++;
            CommPortIdentifier commPortIdentifier = portList.nextElement();
            logger.info("Port found: " + commPortIdentifier.getName());

            if (commPortIdentifier.getName().equals(portName)) {
                try {
                    serialPort = (SerialPort) commPortIdentifier.open("p1meter", 2000);
                } catch (PortInUseException e) {
                    logger.error(e.toString(), e);
                }

                try {
                    serialPort.addEventListener(this);
                } catch (TooManyListenersException e) {
                    logger.error(e.toString(), e);
                }

                serialPort.notifyOnDataAvailable(true);

                try {
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_7, SerialPort.STOPBITS_1,
                            SerialPort.PARITY_EVEN);
                } catch (UnsupportedCommOperationException e) {
                    logger.error(e.toString(), e);
                }
            }
        }

        readByteBuffer = ByteBuffer.allocate(maxBufferSize);

        logger.info("Finished initializing SmartMeterDevice. Found " + count + " devices");
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {

            try {
                int nextByte;

                while ((nextByte = serialPort.getInputStream().read()) != -1) {
                    readByteBuffer.put((byte) nextByte);

                    if (nextByte == FINISH_CHARACTER) {
                        smartMeterListener.put(new String(readByteBuffer.array()));
                        readByteBuffer.clear();
                    }
                }
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }
    }

    public void setSmartMeterListener(SmartMeterListener smartMeterListener) {
        this.smartMeterListener = smartMeterListener;
    }
}
