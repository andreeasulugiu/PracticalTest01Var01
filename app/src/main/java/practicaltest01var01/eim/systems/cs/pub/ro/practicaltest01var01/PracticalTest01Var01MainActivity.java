package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {
    Button sud;
    Button nord;
    Button est;
    Button vest;
    Button send;
    TextView text;
    int number;

    ButtonOnClickListener listener = new ButtonOnClickListener();

    private class ButtonOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            number ++;
            switch(view.getId()) {
                case R.id.nord:
                    String a =text.getText().toString().concat(",N");
                    text.setText(text.getText().toString().concat(",N"));
                    break;
                case R.id.sud:
                    text.setText(text.getText().toString().concat(",S"));
                    break;
                case R.id.vest : {
                    text.setText(text.getText().toString().concat(",V"));
                    break;
                }
                case R.id.est : {
                    text.setText(text.getText().toString().concat(",E"));
                    break;
                }
                case R.id.send: {
                    String txt = text.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                    intent.putExtra("text",txt);
                    startActivityForResult(intent, 1 );
                    break;
                }

            }
            Log.d("test",Integer.toString(number));
            if (number >= 4){
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra("text",text.getText().toString());
                getApplicationContext().startService(intent);
            }
        }
    }

    private MyBroadcastReceiver messageBroadcastReceiver = new MyBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("text"));
        }
    }

    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);
        nord = (Button) findViewById(R.id.nord);
        nord.setOnClickListener(listener);
        sud = (Button) findViewById(R.id.sud);
        sud.setOnClickListener(listener);
        vest = (Button) findViewById(R.id.vest);
        vest.setOnClickListener(listener);
        est = (Button) findViewById(R.id.est);
        est.setOnClickListener(listener);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(listener);
        text = (TextView) findViewById(R.id.text);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("text")) {
                text.setText(savedInstanceState.get("text").toString());
            }

            if (savedInstanceState.containsKey("number")) {
                number = Integer.parseInt(savedInstanceState.get("number").toString());
            }
        }

        intentFilter = new IntentFilter();
        intentFilter.addAction("action");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text",text.getText().toString());
        outState.putInt("number",number);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("text")) {
            text.setText(savedInstanceState.get("text").toString());
        }
        if (savedInstanceState.containsKey("number")) {
            number = Integer.parseInt(savedInstanceState.get("number").toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            number = 0;
            text.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: exercise 8c - register the broadcast receiver with the corresponding intent filter
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        unregisterReceiver(messageBroadcastReceiver);

        super.onPause();
    }

}
