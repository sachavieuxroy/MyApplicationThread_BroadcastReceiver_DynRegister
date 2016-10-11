package google.android.com.myapplicationthread_broadcastreceiver_dynregister;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;



public class MainActivity extends Activity {
    BroadcastReceiver smsReceiver;
    IntentFilter filter;
    private final static String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ACTION_RECEIVE_SMS)) {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Object[] pdus = (Object[]) bundle.get("pdus");

                        final SmsMessage[] messageList = new SmsMessage[pdus.length];
                        int pos = 0;
                        for (Object msgPdu : pdus) {
                            messageList[pos] = SmsMessage.createFromPdu((byte[])
                                    msgPdu);
                            ++pos;
                        }

                        for (SmsMessage message : messageList) {
                            final String messageBody = message.getMessageBody();
                            final String messagePhoneNb = message
                                    .getDisplayOriginatingAddress();

                            Toast.makeText(context, "Expediteur : " +
                                    messagePhoneNb, Toast.LENGTH_LONG).show();

                            Toast.makeText(context, "Message : " + messageBody,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        };


    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        registerReceiver(smsReceiver, filter);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterReceiver(smsReceiver);
    }


}
