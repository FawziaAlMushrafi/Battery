package com.yameen.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.ImageView
import android.widget.TextView


class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val statusLabel = (context as MainActivity).findViewById<TextView>(R.id.statusLabel)
        val percentageLabel = context.findViewById<TextView>(R.id.percentageLabel)
        val batteryImage = context.findViewById<ImageView>(R.id.batteryImage)
        val action = intent.action
        if (action != null && action == Intent.ACTION_BATTERY_CHANGED) {

            // Status
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            var message = ""
            when (status) {
                BatteryManager.BATTERY_STATUS_FULL -> message = "Full"
                BatteryManager.BATTERY_STATUS_CHARGING -> message = "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> message = "Discharging"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> message = "Not charging"
                BatteryManager.BATTERY_STATUS_UNKNOWN -> message = "Unknown"
            }
            statusLabel.text = message


            // Percentage
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val percentage = level * 100 / scale
            percentageLabel.text = "$percentage%"


            // Image
            val res = context.getResources()
            if (percentage >= 90) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100))
            } else if (89 > percentage && percentage >= 65) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75))
            } else if (64 > percentage && percentage >= 40) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50))
            } else if (39 > percentage && percentage >= 20) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b20))
            } else {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0))
            }
        }
    }
}