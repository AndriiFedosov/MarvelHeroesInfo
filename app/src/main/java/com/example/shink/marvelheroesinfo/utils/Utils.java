package com.example.shink.marvelheroesinfo.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;


import com.example.shink.marvelheroesinfo.R;
import com.example.shink.marvelheroesinfo.activity.detail.DetailActivity;
import com.example.shink.marvelheroesinfo.db.entity.Character;

import java.io.Serializable;

/**
 * Created by shink on 08.03.2018.
 */

public class Utils {
    //create method that check connection to network
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();

    }

    //create notification
    public static void createNotification(int nId, Character character, Context context) {

        //create notifiction from dto of charter
        String characterName = character.getName();
        String body = String.format(context.getResources().getString(R.string.number_of_comics), characterName,
                character.getComicsDto().getAvailable());

        //create intent in detail activity
        Intent intent = new Intent(context, DetailActivity.class);
        //add parameters
        intent.putExtra(Constants.CHARACTER_ID, nId);
        intent.putExtra(Constants.CHARACTER_NAME, characterName);
        intent.putExtra(Constants.CHARACTER_PHOTO, character.getThumbnail());

        //create flag who get info about update of intent
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, flags);

        //create full notification of heroes
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(characterName)
                        .setContentText(body)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(nId, mBuilder.build());
    }
}