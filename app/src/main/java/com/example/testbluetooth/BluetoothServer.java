package com.example.testbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class BluetoothServer {
    private final BluetoothServerSocket mmServerSocket;
    private static final UUID MY_UUID = UUID.fromString("00000000-0000-0000-0000-00000000ABCD");
    public static final String serviceName = "IHAP_BT_SERV";
    IncomingBtMsg message =null;
    private boolean isTransmissionOver;

    public BluetoothServer(BluetoothAdapter btAdapt, IncomingBtMsg msg){
        BluetoothServerSocket tmp = null;
        isTransmissionOver = false;
        message = msg;
        try{
            tmp = btAdapt.listenUsingRfcommWithServiceRecord(serviceName, MY_UUID);
        }catch(IOException e){
            Log.e("Bluetooth", "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }
    public void run_server(){
        BluetoothSocket socket = null;
        BluetoothSocketListener listenerSock = null;
        while(true){
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e("Bluetooth", "Socket's accept() method failed", e);
                break;
            }
            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                listenerSock = new BluetoothSocketListener(socket, message);
                listenerSock.listen_socket();
                if(listenerSock.isTransmissionOver()){
                    isTransmissionOver = true;
                    break;
                }
                /*
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                */

            }
        }
    }
    public boolean isReceivingComplete(){
        return isTransmissionOver;
    }

}
