package google.android.com.myapplicationthread_broadcastreceiver_dynregister;

/**
 * Created by mail on 10/11/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MonReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Detection d’un intent.", Toast.LENGTH_LONG).show();

    }
}
