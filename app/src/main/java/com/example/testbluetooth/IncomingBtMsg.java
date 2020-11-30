package com.example.testbluetooth;

public class IncomingBtMsg {
    private String message;
    public IncomingBtMsg(){
        message = new String();
    }
    public void concat_string(String msg){
        message = message.concat(msg.substring(4));//msg.substring(4)
    }
    public String getIncomingMessage(){
        return message;
    }
}
