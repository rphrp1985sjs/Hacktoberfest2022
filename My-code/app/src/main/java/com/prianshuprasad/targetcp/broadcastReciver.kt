package com.prianshuprasad.targetcp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StartAppOnBoot : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

//        Toast.makeText(context,"Strating ser",Toast.LENGTH_LONG).show()

            val serintent= Intent(context, notificationServices::class.java)

            context!!.startService(serintent)


    }
}