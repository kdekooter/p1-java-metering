p1-java-metering
================

This is a client library written in Java enabling a connection to (dutch) smart meters.

The P1 meter delivers datagrams like these every 10 seconds:

```
!
/XMX5XMXABCE000062529

0-0:96.1.1(31333631363433322020202020202020)
1-0:1.8.1(00032.519*kWh)
1-0:1.8.2(00020.176*kWh)
1-0:2.8.1(00001.104*kWh)
1-0:2.8.2(00003.910*kWh)
0-0:96.14.0(0002)
1-0:1.7.0(0000.39*kW)
1-0:2.7.0(0000.00*kW)
0-0:17.0.0(999*A)
0-0:96.3.10(1)
0-0:96.13.1()
0-0:96.13.0()
0-1:96.1.0(3238303131303038323437303539393132)
0-1:24.1.0(03)
0-1:24.3.0(130204210000)(00)(60)(1)(0-1:24.2.0)(m3)
(00034.998)
0-1:24.4.0(1)
```

This library uses the GNU RXTXComm library to perform serial communication with the smart meter.

Feel free to fork and improve the code!

linux configuration
===================

`sudo apt-get install librxtx-java`

Make sure to put the native stuff in the library path:

`JAVA_OPTS=-Djava.library.path=/usr/lib/jni`


usage
=====

Hook this library into your application like so:

```java
// Instantiate the listener
SmartMeterListener smartMeterListener = new SmartMeterListenerImpl();
...
// Periodically read the values
SmartMeterMeasurement smartMeterMeasurement = smartMeterListener.getCurrentMeasurement();
```

This library is built with Maven. To include it in your project add the following repository and dependency:

```xml
<repository>
    <id>boplicity.nl</id>
    <name>boplicity snapshots</name>
    <url>https://boplicity.nl/nexus/content/repositories/snapshots/</url>
</repository>

<dependency>
    <groupId>org.boplicity</groupId>
    <artifactId>p1-java-metering</artifactId>
    <version>1.1-SNAPSHOT</version>
</dependency>
```

Or check out the code and install it in your local repository using ```mvn install```

permissions
===========
Make sure add the user running the software to the `dialout` group.
