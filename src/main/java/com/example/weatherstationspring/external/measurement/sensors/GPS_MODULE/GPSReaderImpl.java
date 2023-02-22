package com.example.weatherstationspring.external.measurement.sensors.GPS_MODULE;

import com.fazecast.jSerialComm.SerialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GPSReaderImpl implements GPSReader {
    final int BaudRate = 9600;
    final int DataBits = 8;
    final int StopBits = SerialPort.ONE_STOP_BIT;
    final int Parity   = SerialPort.NO_PARITY;
    String portName = "";

    private boolean continueReading = true;
    
    @Override
    public SerialPort getPortNameOfGPS() {
        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port: ports) {
            portName = port.getSystemPortName();
            System.out.println(portName);
            if (portName.startsWith("ttyUSB")) {
                return port;
            }
        }
        return null;
    }

    @Override
    public void stopReading() {
        this.continueReading = false;
    }
    
    @Override
    public void getDataFromGPS(SerialPort serialPort) {

        if (serialPort == null) {
            System.out.println("GPS not found!");
        }
        assert serialPort != null;
        serialPort.setComPortParameters(BaudRate,
                                          DataBits,
                                          StopBits,
                                          Parity);
        serialPort.openPort();

        if (serialPort.isOpen()) {
            System.out.println("is Open ");
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,
                    1000,
                    0);
            serialPort.openPort();
            try {
                while (continueReading)
                {
                    byte[] readBuffer = new byte[600];
                    int numRead = serialPort.readBytes(readBuffer,
                            readBuffer.length);
                    System.out.print("Read " + numRead + " bytes - \n");
                    //Convert bytes to String
                    String S = new String(readBuffer, StandardCharsets.UTF_8);
                    System.out.println("Received -> "+ S);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                serialPort.closePort();
            }
        }
        
        else {
            System.out.println(" Port not open ");
        }
        serialPort.closePort(); //Close the port
      
    }
    
    @Override
    public void getDataFromGPSNew(SerialPort serialPort) throws InterruptedException {

        BufferedReader br = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

        serialPort.setComPortParameters(BaudRate,
                DataBits,
                StopBits,
                Parity);
        serialPort.openPort();

        if (serialPort.isOpen()) {
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,
                    1000,
                    0);
            serialPort.openPort();
            try {
                StringBuilder line = new StringBuilder();

                while (continueReading) {
                    var bytesAvailable = serialPort.bytesAvailable();
                    if (bytesAvailable > 0) {
                        for (int i = 0; i < bytesAvailable; i++) {
                            byte b = (byte) br.read();
                            if (b < 32) {
                                // All non-string bytes are handled as line breaks
                                if (line.length() > 0) {
                                    // Here we should add code to parse the data to a GPS data object
                                    System.out.println("Data: '" + line + "'");
                                    line = new StringBuilder();
                                }
                            } else {
                                line.append((char) b);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                serialPort.closePort();
            }
        }
        else {
            System.out.println("Port not open... waiting...");
            Thread.sleep(1000);
        }
        serialPort.closePort(); //Close the port

    }
}
