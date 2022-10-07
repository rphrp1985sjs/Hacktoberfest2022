package com.prianshuprasad.targetcp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import java.lang.Thread.sleep
import kotlin.concurrent.thread


class notificationServices() : Service() {

    private var mAuthenticator: BookAuthenticator? = null

    private var wakeLock: PowerManager.WakeLock? = null
    var visited= mutableMapOf<String,String>()
    private lateinit var notificationManager: NotificationManagerCompat
    var channelId  = "Progress Notification" as String
    var openurl="";


   private lateinit var  sharedPreferences:SharedPreferences


    var notitext=""
    var header=""

    lateinit var notification : NotificationCompat.Builder

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

       sharedPreferences = getSharedPreferences("DATA",Context.MODE_PRIVATE )

        channelId = "${(0..20000000).random()}"
        createNotificationChannel()

        mAuthenticator =  BookAuthenticator(this);

        notificationManager = NotificationManagerCompat.from(this)



        thread {

            while (true) {

                fetchcondata("https://kontests.net/api/v1/all")

          sleep(1000*60*60)


            }
        }






        return START_NOT_STICKY;
    }


    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
//        Toast.makeText(this,"OnTaskREmoved",Toast.LENGTH_LONG).show()
        val serintent= Intent(this, notificationServices::class.java)
        startService(serintent)


        sendBroadcast(Intent("YouWillNeverKillMe"))
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)

//        Toast.makeText(this,"OnUnbind",Toast.LENGTH_LONG).show()


    }

    override fun onDestroy() {
        super.onDestroy()
//        Toast.makeText(this,"OnDestroy",Toast.LENGTH_LONG).show()
        val serintent= Intent(this, notificationServices::class.java)

        startService(serintent)

        sendBroadcast(Intent("YouWillNeverKillMe"))
//        Toast.makeText(this, "YouWillNeverKillMe TOAST!!", Toast.LENGTH_LONG).show()

    }

    override fun onBind(intent: Intent): IBinder? {
         return mAuthenticator!!.iBinder
    }






    public fun startNotification(){

        createNotificationChannel()
        val intent = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK


        }

        intent.putExtra("url", "https://kontests.net/api/v1/all")
        intent.putExtra("open-url","$openurl")
        intent.putExtra("siteindex","0")


        var pi: PendingIntent=  PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        notification =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon( R.mipmap.ic_launcher   )
                .setContentTitle("$header")
                .setContentText("$notitext")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)


//        if(notification!=null)
//            notificationManager.notify( (0..10000).random(), notification.build())

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify((0..10000).random(), notification.build())
        }

    }




    private fun createNotificationChannel() {
        channelId= "${System.currentTimeMillis()}"
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelId
            val descriptionText = "RPPPPPP"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }





    fun fetchcondata( url:String)
    {
      var tempVisited = mutableMapOf<String,String>()

       visited = DataManupilate().toDataMap(sharedPreferences.getString("DATA","${DataManupilate().toString(visited)}")!!)
        val jore = JsonArrayRequest(
            Request.Method.GET, url,null,
            { response ->

                val jsonarray: JSONArray = response
                val contestArray= ArrayList<contest>()

                for(i in 0 until jsonarray.length())
                {
                    val tempjsonobjct= jsonarray.getJSONObject(i)
                    val tempnews= contest(
                        tempjsonobjct.getString("name" ),
                        tempjsonobjct.getString("url" ),
                        tempjsonobjct.getString("start_time" ),
                        tempjsonobjct.getString("end_time" ),
                        "rp"

                        ,
                        tempjsonobjct.getString("in_24_hours" )
//

                    )


                        tempnews.site= tempjsonobjct.getString("site")



                        if(tempnews.today=="Yes" && !visited.containsKey(tempnews.name) ) {
                         notitext= timemodifyist(tempnews.stime)
                            header= tempnews.name
                            openurl= tempnews.url
                            startNotification()



                        }
                    tempVisited[tempnews.name] = tempnews.name


                }

                sharedPreferences.edit().putString("DATA",DataManupilate().toString(tempVisited)).apply()





            },




            {


            })
        MySingleton.getInstance(this).addToRequestQueue(jore)


    }








    fun timemodifyist(s:String):String{


        var yearstring:String = s.subSequence(0,4) as String
        var years:Int= yearstring.toInt()
        var monthstring:String = s.subSequence(5,7) as String
        var months:Int= monthstring.toInt()
        var daystring:String = s.subSequence(8,10) as String
        var days:Int= daystring.toInt()
        var hoursstring:String = s.subSequence(11,13) as String
        var hours:Int= hoursstring.toInt()
        var minutesstring:String = s.subSequence(14,16) as String
        var minutes:Int= minutesstring.toInt()



        minutes+=30;
        if(minutes>=60){
            hours++;
            minutes%=60
        }
        hours+=5
        if(hours>=24){

            days++;
            hours%=24
        }

        if(days>30){

            if(months!=1 || months!=3 || months!=5 ||months!=7 ||months!=8 ||months!=10 ||months!=12  )
            {
                months++
                days %= 30



            }else{

                if(days>31){
                    months++;
                    days%=31

                }
            }


            if(months>12)
            {
                years++;
                months%=12

            }


        }

        if(minutes<10) {
            minutesstring = " 0${minutes.toString()}"
        }
        hoursstring=hours.toString()
        daystring=days.toString()
        monthstring=months.toString()
        yearstring=years.toString()

        if(days<10)
        {
            daystring= "0$daystring"
        }

        if(months<10)
        {
            monthstring= "0$monthstring"
        }

        return "$hoursstring:$minutesstring IST Date: $daystring-$monthstring-$yearstring"




    }



}
