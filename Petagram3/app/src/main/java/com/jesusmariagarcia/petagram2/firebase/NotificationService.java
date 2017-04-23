package com.jesusmariagarcia.petagram2.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jesusmariagarcia.petagram2.MainActivity;
import com.jesusmariagarcia.petagram2.R;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;


/**
 * Created by jesusmgarcia on 14/4/17.
 */

public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        enviarNotificacion(remoteMessage);

    }

    public void enviarNotificacion(RemoteMessage remoteMessage) {

        Intent i = new Intent(this, MainActivity.class);
        i.setAction("OPEN_TAB_1");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.dogbone, getString(R.string.texto_accion), pendingIntent).build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.dogboneyellow))
                .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.dogbone)
                .setContentTitle("Notification")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
