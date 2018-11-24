package pl.edu.pjatk.stefanczuk.shoppinglistbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AddedProductReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, NotificationService.class);
        serviceIntent.putExtras(intent);
        context.startService(serviceIntent);
    }
}
