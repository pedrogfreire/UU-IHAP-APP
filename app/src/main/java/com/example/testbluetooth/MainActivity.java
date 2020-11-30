package com.example.testbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        OkHttpClient client = new OkHttpClient();

        String url = "https://reqres.in/api/users?page=2";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();
                    System.out.println("Response: " + myResponse);
                }
            }
        });
*/
        BluetoothAdapter btfAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btfAdapter == null) {
            System.out.println("Device doesn't support Bluetooth");
        }
        else {
            if (!btfAdapter.isEnabled()) {
                System.out.println("Bluetooth is not Enable");
            }
            else {
                Thread tr = new Thread(new TransmissionManager(btfAdapter));
                tr.run();
            }
        }
        /*
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //send message
            }
        });

        BluetoothAdapter btfAdapter = BluetoothAdapter.getDefaultAdapter();
        */

    }
}