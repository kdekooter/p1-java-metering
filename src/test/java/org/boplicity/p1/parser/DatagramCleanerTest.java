package org.boplicity.p1.parser;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringWriter;

public class DatagramCleanerTest {

    @Test
    public void testAsArray() throws Exception {
        Assert.assertEquals(19, DatagramCleaner.asArray(getTestDatagram()).length);
    }

    private String getTestDatagram() {
        StringWriter stringWriter = new StringWriter();
        String result = null;

        try {
            IOUtils.copy(getClass().getResourceAsStream("/test-data.txt"), stringWriter);
            result = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
