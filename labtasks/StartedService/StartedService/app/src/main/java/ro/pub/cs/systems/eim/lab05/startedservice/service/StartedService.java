package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        // TODO: exercise 5 - implement and start the ProcessingThread

        Thread serviceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Intent stringIntent = new Intent();
                        stringIntent.setAction(Constants.ACTION_STRING);
                        stringIntent.putExtra(Constants.DATA, Constants.STRING_DATA);
                        sendBroadcast(stringIntent);
                        Thread.sleep(Constants.SLEEP_TIME);

                        Intent integerIntent = new Intent();
                        integerIntent.setAction(Constants.ACTION_INTEGER);
                        integerIntent.putExtra(Constants.DATA, Constants.INTEGER_DATA + "");
                        sendBroadcast(integerIntent);
                        Thread.sleep(Constants.SLEEP_TIME);

                        Intent arrayListIntent = new Intent();
                        arrayListIntent.setAction(Constants.ACTION_ARRAY_LIST);
                        arrayListIntent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA + "");
                        sendBroadcast(arrayListIntent);
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        serviceThread.start();
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());

        return START_REDELIVER_INTENT;
    }

}
