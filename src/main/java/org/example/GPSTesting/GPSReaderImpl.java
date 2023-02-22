package org.example.GPSTesting;

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

    String GNRMC = "";
    String GNVTG = "";
    String GNGGA = "";
    String GPGSV = "";
    String BDGSV = "";
    String GNGLL = "";
    /*
$GNRMC,204525.086,V,,,,,0.03,223.58,170223,,,N*51
$GNVTG,223.58,T,,M,0.03,N,0.05,K,N*24
$GNGGA,204525.086,,,,,0,0,,,M,,M,,*5C
$GPGSA,A,1,,,,,,,,,,,,,,,*1E
$BDGSA,A,1,,,,,,,,,,,,,,,*0F
$GPGSV,3,1,09,08,68,220,,21,54,287,,10,52,061,29,27,44,166,18*7C
$GPGSV,3,2,09,32,39,121,,01,26,281,,23,18,055,19,14,15,323,*75
$GPGSV,3,3,09,24,05,043,*44
$BDGSV,1,1,00*68
$GNGLL,,,,,204525.086,V,N*6E
     */

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
                                    System.out.println(line);
                                    line = new StringBuilder();
                                }
                            } else {
                                line.append((char) b);
                            }
                        }
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (RuntimeException e) {
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

    @Override
    public String fetchData(SerialPort serialPort, BufferedReader br) {
        try {
            StringBuilder line = new StringBuilder();

                var bytesAvailable = serialPort.bytesAvailable();
                //byte[] readBuffer = new byte[600];
                if (bytesAvailable > 0) {
                    for (int i = 0; i < bytesAvailable; i++) {
                        byte b = (byte) br.read();
                        if (b < 32) {
                            // All non-string bytes are handled as line breaks
                            if (line.length() > 0) {
                                //String S = new String(readBuffer, StandardCharsets.UTF_8);
                                //System.out.println("Received -> "+ S);
                                System.out.println("Line: " + line);
                                //line = new StringBuilder();
                                return line.toString();


                            }
                        } else {
                            line.append((char) b);
                        }
                    }
                }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            serialPort.closePort();
        }
        return "";
    }

    public String fetchData2 (SerialPort serialPort) {
        byte[] readBuffer = new byte[400];
        int numRead = serialPort.readBytes(readBuffer,
                readBuffer.length);
        System.out.print("Read " + numRead + " bytes - \n");
        //Convert bytes to String
        String S = new String(readBuffer, StandardCharsets.UTF_8);
        System.out.println(S);
        return S;
    }

    @Override
    public SerialPort prepareForFetchingData() throws InterruptedException {

        SerialPort serialPort = getPortNameOfGPS();
        if (serialPort == null) {
            return null;
        }

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
            return serialPort;
        }
        else {
            System.out.println("Port not open... waiting...");
            Thread.sleep(1000);
        }
        serialPort.closePort(); //Close the port
        return  null;
    }

    @Override
    public void getDataFromGPSNew2(SerialPort serialPort) throws InterruptedException {

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

            while(continueReading) {
                fetchData2(serialPort);
            }
            if (fetchData(serialPort, br) == null) {
                stopReading();
            }
        }
        else {
            System.out.println("Port not open... waiting...");
            Thread.sleep(1000);
        }
        serialPort.closePort(); //Close the port

    }
}


