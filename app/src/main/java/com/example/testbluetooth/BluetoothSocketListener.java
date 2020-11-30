package com.example.testbluetooth;

import android.bluetooth.BluetoothSocket;
import android.icu.text.SymbolTable;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class BluetoothSocketListener {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private byte[] mmBuffer; // mmBuffer store for the stream
    private boolean transmissionIsOver;
    IncomingBtMsg message;

    public BluetoothSocketListener(BluetoothSocket socket, IncomingBtMsg msg){
        message = msg;
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        transmissionIsOver = false;
        try {
            tmpIn = socket.getInputStream();
        } catch (IOException e) {
            System.out.println("Failed to get Input Stream");
        }
        try {
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Failed to get Output Stream");
        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        System.out.println("data input stream created");
    }
    public void listen_socket(){
        mmBuffer = new byte[10000];
        int numBytes; // bytes returned from read()
        try {
            numBytes = mmInStream.read(mmBuffer);
            String msgString = new String(mmBuffer, 0, numBytes );
            System.out.println("Message Received " + numBytes +" of Bytes and the message is: " + msgString);
            message.concat_string(msgString);
            byte[] bytesAns = new byte[3];
            bytesAns = "OK".getBytes();
            mmOutStream.write(bytesAns);
            if(mmBuffer[1]==mmBuffer[3]){
                transmissionIsOver=true;
            }
            mmSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to read message");
        }

    }
    public boolean isTransmissionOver(){
        return transmissionIsOver;
    }
}
