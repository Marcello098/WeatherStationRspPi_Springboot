package org.example.GPSTesting;

import com.pi4j.context.Context;
import com.pi4j.io.serial.*;
import com.pi4j.util.Console;

public class OldReader {

    public void executeGPSReader2(Context pi4j) throws InterruptedException {

        // Create Pi4J console wrapper/helper
        // (This is a utility class to abstract some boilerplate stdin/stdout code)
        var console = new Console();

        // Print program title/header
        console.title("<-- The Pi4J Project -->", "Serial Example project");

        // ------------------------------------------------------------
        // Output Pi4J Context information
        // ------------------------------------------------------------
        // The created Pi4J Context initializes platforms, providers
        // and the I/O registry. To help you to better understand this
        // approach, we print out the info of these. This can be removed
        // from your own application.
        // OPTIONAL

        // Here we will create I/O interface for the serial communication.
        SerialConfig serialConfig = Serial.newConfigBuilder(pi4j)
                .use_9600_N81()
                .dataBits_8()
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE)
                .id("my-serial")
                .device("/dev/ttyUSB0")
                .build();
        SerialProvider serialProvider = pi4j.provider("pigpio-serial");
        Serial serial = serialProvider.create(serialConfig);

        // Wait till the serial port is open
        console.print("Waiting till serial port is open");
        while (!serial.isOpen()) {
            console.print(".");
            Thread.sleep(250);
        }
        console.println("");
        console.println("Serial port is open");

        // Start a thread to handle the incoming data from the serial port
        OldReader2 serialReader = new OldReader2(console, serial);
        Thread serialReaderThread = new Thread(serialReader, "OldReader2");
        serialReaderThread.setDaemon(true);
        serialReaderThread.start();

        while (serial.isOpen()) {
            Thread.sleep(500);
        }
        
        serialReader.stopReading();
        console.println("Serial is no longer open");

    }

    public void executeGPSReader(Context pi4j) throws InterruptedException {

        // Create Pi4J console wrapper/helper
        // (This is a utility class to abstract some boilerplate stdin/stdout code)
        var console = new Console();

        // Print program title/header
        console.title("<-- The Pi4J Project -->", "Serial Example project");

        // ------------------------------------------------------------
        // Output Pi4J Context information
        // ------------------------------------------------------------
        // The created Pi4J Context initializes platforms, providers
        // and the I/O registry. To help you to better understand this
        // approach, we print out the info of these. This can be removed
        // from your own application.
        // OPTIONAL

        // Here we will create I/O interface for the serial communication.
        Serial serial = pi4j.create(Serial.newConfigBuilder(pi4j)
                .use_9600_N81()
                .dataBits_8()
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE)
                .id("gps-serial")
                .device("/dev/ttyUSB0")
                .provider("pigpio-serial")
                .build());
        serial.open();

        // Wait till the serial port is open
        console.print("Waiting till serial port is open");
        while (!serial.isOpen()) {
            console.print(".");
            Thread.sleep(250);
        }
        console.println("");
        console.println("Serial port is open");

        // Start a thread to handle the incoming data from the serial port
        OldReader2 serialReader = new OldReader2(console, serial);
        Thread serialReaderThread = new Thread(serialReader, "OldReader2");
        serialReaderThread.setDaemon(true);
        serialReaderThread.start();

        while (serial.isOpen()) {
            Thread.sleep(500);
        }

        serialReader.stopReading();
        console.println("Serial is no longer open");

    }
}