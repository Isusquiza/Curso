package com.jesusmariagarcia.petagram2.firebase;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jesusmariagarcia.petagram2.MainActivity;
import com.jesusmariagarcia.petagram2.R;

import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;


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

        List<NotificationCompat.Action> ListActions = new ArrayList<NotificationCompat.Action>();

        Intent i = new Intent(this, MainActivity.class);
        i.setAction("OPEN_TAB_1");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        // Intent para ver mi perfil
        Intent intentVerPerfil = new Intent(this, MainActivity.class);
        intentVerPerfil.setAction("OPEN_TAB_1");

        // Intent para dar follow al usuario que ha dado like a mi foto
        Intent intentDarFollow = new Intent();
        intentDarFollow.setAction("FOLLOW_USER");

        // Extraer el nombre del usuario de la notificacion
        String text = remoteMessage.getNotification().getBody().toString();
        String words[] = text.split(" ", 2);
        String userName = words[0];

        intentDarFollow.putExtra("username", userName);

        // Intent para ver el perfil del usuario que ha dado like a mi foto
        SharedPreferences misPreferencias = getSharedPreferences("Instagram", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("FollowUser", userName);
        editor.commit();

        Intent intentVerUsuario = new Intent(this, MainActivity.class);
        intentVerUsuario.setAction("OPEN_TAB_2");

        // Configuración de los pending intent
        PendingIntent pendingIntentVerPerfil = PendingIntent.getActivity(this, 0, intentVerPerfil, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntentDarFollow = PendingIntent.getBroadcast(this, 0, intentDarFollow, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentVerUsuario = PendingIntent.getActivity(this, 0, intentVerUsuario, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action actionVerPerfil =
                new NotificationCompat.Action.Builder(R.drawable.dogbone, getString(R.string.wear_accion_ver_perfil), pendingIntentVerPerfil).build();

        NotificationCompat.Action actionDarFollow =
                new NotificationCompat.Action.Builder(R.drawable.dogbone, getString(R.string.wear_accion_dar_follow), pendingIntentDarFollow).build();

        NotificationCompat.Action actionVerUsuario =
                new NotificationCompat.Action.Builder(R.drawable.dogbone, getString(R.string.wear_accion_ver_usuario), pendingIntentVerUsuario).build();

        ListActions.add(actionVerPerfil);
        ListActions.add(actionDarFollow);
        ListActions.add(actionVerUsuario);

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
                .extend(wearableExtender.addActions(ListActions));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
