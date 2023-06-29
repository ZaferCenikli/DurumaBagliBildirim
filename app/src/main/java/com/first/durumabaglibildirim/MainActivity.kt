package com.first.durumabaglibildirim

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonBildirim.setOnClickListener {
            val builder:NotificationCompat.Builder
            val bildirimYoneticisi=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val ıntent=Intent(this,MainActivity::class.java)
            val gidilecekIntent=PendingIntent.getActivity(this,1,ıntent,PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                var kanalid="kanalid"
                var kanalad="kanalad"
                var kanaltanitim="kanaltanitim"
                var kanalOnceligi=NotificationManager.IMPORTANCE_HIGH

                var kanal:NotificationChannel?=bildirimYoneticisi.getNotificationChannel(kanalid)
                if(kanal==null){
                    kanal= NotificationChannel(kanalid,kanalad,kanalOnceligi)
                    kanal.description=kanaltanitim
                    bildirimYoneticisi.createNotificationChannel(kanal)

                }

                builder=NotificationCompat.Builder(this,kanalid)
                builder.setContentTitle("Baslık")
                    .setContentText("İçerik")
                    .setSmallIcon(R.drawable.resim)
                    .setContentIntent(gidilecekIntent)
                    .setAutoCancel(true)




            }else{
                builder=NotificationCompat.Builder(this)
                builder.setContentTitle("Baslık")
                    .setContentText("İçerik")
                    .setSmallIcon(R.drawable.resim)
                    .setContentIntent(gidilecekIntent)
                    .setAutoCancel(true)
                    .priority=Notification.PRIORITY_HIGH
            }
          bildirimYoneticisi.notify(1,builder.build())

        }


    }
}