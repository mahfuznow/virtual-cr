package mahfuz.virtualcr01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    /**
        public MyFirebaseMessagingService() {
        }



        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);

            String messageTitle= remoteMessage.getNotification().getTitle();
            String messageBody= remoteMessage.getNotification().getBody();



            Intent intent = new Intent(this, TabedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"classTime");
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            //notificationBuilder.setColor(getResources().getColor(R.color.colorAccent));
            notificationBuilder.setContentTitle(messageTitle);
            notificationBuilder.setContentText(messageBody);
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setSound(defaultSoundUri);
            notificationBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            int id=(int) System.currentTimeMillis();
            notificationManager.notify(id, notificationBuilder.build());

        }
        */


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }



    public void showNotification(String title,String message) {

        Intent intent = new Intent(this, TabedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"VCR")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.notification_large_icon)
                .setAutoCancel(true)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.notification_large_icon2));
        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        manager.notify(0,builder.build());
    }
}
