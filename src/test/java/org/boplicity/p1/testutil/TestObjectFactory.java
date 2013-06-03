package org.boplicity.p1.testutil;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;

public class TestObjectFactory {

    public static String getTestDatagram() {
        StringWriter stringWriter = new StringWriter();
        String result = null;

        try {
            IOUtils.copy(TestObjectFactory.class.getResourceAsStream("/test-data.txt"), stringWriter);
            result = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
