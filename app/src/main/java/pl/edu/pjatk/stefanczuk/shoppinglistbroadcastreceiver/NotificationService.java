package pl.edu.pjatk.stefanczuk.shoppinglistbroadcastreceiver;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationService extends Service {

    private static final String channelID = "channel1";
    private int id = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent newIntent = new Intent();
        newIntent.putExtras(intent);
        newIntent.setComponent(new ComponentName("pl.edu.pjatk.stefanczuk.shoppinglist",
                "pl.edu.pjatk.stefanczuk.shoppinglist.activity.NotificationHandlerActivity"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, newIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        String message = "Nazwa: " + intent.getStringExtra("productName") + "\n" +
                "Cena: " + intent.getDoubleExtra("productPrice", 0.0) + "\n" +
                "Ilość: " + intent.getIntExtra("productQuantity", 0);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentTitle("Dodano nowy produkt")
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(id++, notification.build());
        return Service.START_NOT_STICKY;
    }
}
