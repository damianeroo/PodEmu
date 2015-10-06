/**

 Copyright (C) 2015, Roman P., dev.roman [at] gmail

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see http://www.gnu.org/licenses/

 */

package com.rp.podemu;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;

import com.hoho.android.usbserial.driver.ProlificSerialDriver;
import com.hoho.android.usbserial.driver.UsbId;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class SerialInterface_USBSerial implements SerialInterface
{
    private static Map<Integer, String> deviceList = new LinkedHashMap<>();
    private static Map<Integer, Integer> deviceBufferSizes = new LinkedHashMap<>();

    private static UsbDeviceConnection connection;
    private static UsbSerialPort port;

    private static int baudRate=57600;

    public void setBaudRate(int rate)
    {
        if(rate<9600) rate=9600;
        if(rate>115200) rate=115200;
        baudRate=rate;
    }

    public int getBaudRate()
    {
        return baudRate;
    }

    public SerialInterface_USBSerial()
    {
        deviceList.put((UsbId.VENDOR_FTDI << 16) + UsbId.FTDI_FT232R, "FTDI FT232R");
        deviceList.put((UsbId.VENDOR_FTDI << 16) + UsbId.FTDI_FT231X, "FTDI FT231X");

        deviceList.put((UsbId.VENDOR_ATMEL << 16) + UsbId.ATMEL_LUFA_CDC_DEMO_APP, "Atmel Lufa");

        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_UNO, "Arduino Uno");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_2560, "Arduino Mega2560");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_SERIAL_ADAPTER, "Arduino Serial Adapter");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_ADK, "Arduino ADK");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_2560_R3, "Arduino Mega2560 R3");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_UNO_R3, "Arduino Uno R3");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_ADK_R3, "Arduino ADK R3");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_SERIAL_ADAPTER_R3, "Arduino Serial Adapter R3");
        deviceList.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_LEONARDO, "Arduino Leonardo");

        deviceList.put((UsbId.VENDOR_VAN_OOIJEN_TECH << 16) + UsbId.VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL, "Van Ooijen Tech TEENSYDUINO");

        deviceList.put((UsbId.VENDOR_LEAFLABS << 16) + UsbId.LEAFLABS_MAPLE, "Leaf Labs Maple");

        deviceList.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2102, "SiLabs CP2102");
        deviceList.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2105, "SiLabs CP2105");
        deviceList.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2108, "SiLabs CP2108");
        deviceList.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2110, "SiLabs CP2110");

        deviceList.put((UsbId.VENDOR_PROLIFIC << 16) + UsbId.PROLIFIC_PL2303, "Prolific PL2303");
        
        
        // read buffer sizes
        deviceBufferSizes.put((UsbId.VENDOR_FTDI << 16) + UsbId.FTDI_FT232R, 128);
        deviceBufferSizes.put((UsbId.VENDOR_FTDI << 16) + UsbId.FTDI_FT231X, 512);

        deviceBufferSizes.put((UsbId.VENDOR_ATMEL << 16) + UsbId.ATMEL_LUFA_CDC_DEMO_APP, 512);

        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_UNO, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_2560, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_SERIAL_ADAPTER, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_ADK, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_2560_R3, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_UNO_R3, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_MEGA_ADK_R3, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_SERIAL_ADAPTER_R3, 512);
        deviceBufferSizes.put((UsbId.VENDOR_ARDUINO << 16) + UsbId.ARDUINO_LEONARDO, 512);

        deviceBufferSizes.put((UsbId.VENDOR_VAN_OOIJEN_TECH << 16) + UsbId.VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL, 512);

        deviceBufferSizes.put((UsbId.VENDOR_LEAFLABS << 16) + UsbId.LEAFLABS_MAPLE, 512);

        deviceBufferSizes.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2102, 576);
        deviceBufferSizes.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2105, 288);
        deviceBufferSizes.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2108, 1536);
        deviceBufferSizes.put((UsbId.VENDOR_SILABS << 16) + UsbId.SILABS_CP2110, 480);

        deviceBufferSizes.put((UsbId.VENDOR_PROLIFIC << 16) + UsbId.PROLIFIC_PL2303, 258);

    }
    

    public void init(UsbManager manager)
    {
        UsbManager usbManager=manager;

        // Find all available drivers from attached devices.
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        connection = manager.openDevice(driver.getDevice());
        if (connection == null)
        {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            PodEmuLog.log("Cannot establish serial connection!");
            return;
        }

        // Read some data! Most have just one port (port 0).
        List<UsbSerialPort> ports = driver.getPorts();
        port = ports.get(0);
        try {
            port.open(connection);
            port.setParameters(baudRate, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        }
        catch (IOException e)
        {
            // TODO Deal with error
            PodEmuLog.error(e.getMessage());
        }


    }

    public int write(byte[] buffer, int numBytes)
    {
        // if connection is not set then nothing to write
        if(!isConnected()) return 0;

        try
        {
            return port.write(buffer, 0);
        }
        catch (IOException e)
        {
            // TODO Deal with log.
        }

        // if we are here then there was exception and 0 bytes were read
        return 0;
    }

    /**
     * Function reads bytes from serial port and returns number of read bytes
     * Warning: function may read maximum 258 bytes (256 basing on PL2303 datasheet)
     *
     * @param buffer - buffer for read data to be stored in. Should be at least
     *                 258 bytes long
     * @return       - number of bytes read
     */
    public int read(byte[] buffer)
    {
        int numBytesRead=0;

        // if connection is not set then nothing to read
        if(!isConnected()) return 0;

        try {
            numBytesRead = port.read( buffer, 0);
            //Log.d("RPP", "Read " + numBytesRead + " bytes.");
        }
        catch (IOException e)
        {
            // TODO Deal with log.
            return -1;
        }
        finally
        {
        }

        return numBytesRead;
    }

    public String readString()
    {
        byte[] buffer=new byte[ProlificSerialDriver.ProlificSerialPort.DEFAULT_READ_BUFFER_SIZE];
        int numBytesRead=read(buffer);
        String str=new String(buffer,0,numBytesRead);
        //return "["+numBytesRead+"] "+str;
        return str;
    }

    public boolean isConnected()
    {
        return (connection!=null && port!=null);
    }

    public void close()
    {
        if(connection!=null) connection.close();
        connection=null;


        try
        {
            if(port!=null) port.close();
        }
        catch (IOException e)
        {
            PodEmuLog.error("Cannot close serial port. Force closing.");
            // TODO Deal with error.
        }
        finally
        {
            port=null;
        }

    }

    public int getVID()
    {
        return port.getDriver().getDevice().getVendorId();
    }

    public int getPID()
    {
        return port.getDriver().getDevice().getProductId();
    }


    public String getName()
    {
        int pid=port.getDriver().getDevice().getProductId();
        int vid=port.getDriver().getDevice().getVendorId();
        int key=(vid << 16) + pid;

        if(deviceList.containsKey(key))
            return deviceList.get(key);
        else
            return "Unknown device";
    }

    public int getReadBufferSize()
    {
        return 600;
    }
}