package com.example.testbluetooth;

import android.bluetooth.BluetoothAdapter;


public class TransmissionManager extends Thread {
    private static final int INITIAL = 0;
    private static final int WAITING_BT_SOCKET = 1;
    private static final int TRANSMITTING_IHAP = 2;
    private static final int COMPLETE_IHAP_TRANSMISSION = 3;

    BluetoothServer btServer = null;
    int transmissionState = INITIAL;
    IncomingBtMsg btReceivedMsg = new IncomingBtMsg();
    public TransmissionManager(BluetoothAdapter btAdapt){
        btServer = new BluetoothServer(btAdapt, btReceivedMsg);
        transmissionState = WAITING_BT_SOCKET;
        btServer.run_server();
    }
    public void run(){
        while(true) {
            switch (transmissionState) {
                case INITIAL:
                    break;
                case WAITING_BT_SOCKET:
                    if (btServer.isReceivingComplete()) {
                        transmissionState = TRANSMITTING_IHAP;
                        System.out.println("Complete Received Message is " + btReceivedMsg.getIncomingMessage());
                    }
                    break;
                case TRANSMITTING_IHAP:
                    break;
                case COMPLETE_IHAP_TRANSMISSION:
                    break;
            }
        }
    }
}
