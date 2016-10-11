package google.android.com.myapplicationthread_broadcastreceiver_dynregister;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver{
    private final static String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";

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
}

