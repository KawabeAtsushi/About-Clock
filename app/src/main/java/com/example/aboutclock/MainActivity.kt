package com.example.aboutclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 開始ボタン
        val button = findViewById<Button>(R.id.button1)
        val str = "Alarm Start"
        button.text = str
        button.setOnClickListener { // 時間をセットする
            val calendar = Calendar.getInstance()
            // Calendarを使って現在の時間をミリ秒で取得
            calendar.timeInMillis = System.currentTimeMillis()
            // 5秒後に設定
            calendar.add(Calendar.SECOND, 5)

            //明示的なBroadCast
            val intent = Intent(
                applicationContext,
                AlarmBroadcastReceiver::class.java
            )
            val pending = PendingIntent.getBroadcast(
                applicationContext, 0, intent, 0
            )

            // アラームをセットする
            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending)
            Toast.makeText(
                applicationContext,
                "Set Alarm ", Toast.LENGTH_SHORT
            ).show()
        }
    }
}