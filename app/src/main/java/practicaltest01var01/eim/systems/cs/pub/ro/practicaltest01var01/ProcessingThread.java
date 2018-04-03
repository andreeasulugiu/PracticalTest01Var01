package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by student on 03.04.2018.
 */

public class ProcessingThread extends Thread{
    private Context context = null;
    private boolean isRunning = true;

    private String text;

    public ProcessingThread(Context context, String texta) {
        this.context = context;

        text = texta;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        //intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.setAction("action");
        intent.putExtra("text", new Date(System.currentTimeMillis()) + " " + text);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
