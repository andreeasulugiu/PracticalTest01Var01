package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by student on 03.04.2018.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private TextView messageTextView;

    // TODO: exercise 10 - default constructor
    public MyBroadcastReceiver() {
        this.messageTextView = null;
    }

    public MyBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("[Message]", intent.getStringExtra("text"));
    }
}
