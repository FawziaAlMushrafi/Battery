package com.yameen.battery

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val mBatteryReceiver: BatteryReceiver = BatteryReceiver()
    private val mIntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mBatteryReceiver, mIntentFilter)
    }

    override fun onPause() {
        unregisterReceiver(mBatteryReceiver)
        super.onPause()
    }
}