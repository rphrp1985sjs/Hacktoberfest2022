package com.prianshuprasad.brainsmasher

import Adapter
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.concurrent.thread


class playground : AppCompatActivity() {

    private lateinit var gridView:GridView
    private lateinit var timer:TextView
    private lateinit var llinear:LinearLayout
    private lateinit var globalTime:TextView
    var isGameFinished=0
    var time:Long=0;
    private lateinit var scoreDao:cloudDao
    private lateinit var score:TextView
    private var globalTimeLong:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)
        supportActionBar?.hide()

        //intiliazing global variables
        timer= findViewById(R.id.timer)
        scoreDao= cloudDao()
        globalTime= findViewById(R.id.HighScore)
        score = findViewById(R.id.score)
        llinear= findViewById(R.id.linearLayout)

        gridView = findViewById(R.id.gridView)
        var gm = gameMachine()
        gm.create()
        val adapter = Adapter(this, this,gm,gm.getList())

        gridView.adapter= adapter

         // on item click listener of gridView
        gridView.setOnItemClickListener { parent, view, position, id ->

              if(adapter.userIndex==-1|| adapter.userIndex2==-1) {

                  if (gm.getList()[position].label != 0) {
                      if (adapter.userIndex == -1) {
                          adapter.userIndex = position

                      } else {
                          if (position != adapter.userIndex)
                              adapter.userIndex2 = position
                      }

                      adapter.update()
                  }
              }

        }

        // thread to run timer
        thread {
             time= System.currentTimeMillis()
            while(isGameFinished==0)
            {

               var time2= System.currentTimeMillis()
               var temp2=  (time2-time)
                var ten:Long = 1
                var longZero:Long= 0

                if( temp2%(ten)==(longZero) && temp2!=(longZero) ){

                        updateGlobalTime()

                }

           runOnUiThread {
               updateTimer("Time: ${getTime(time,time2)}")
           }
                Thread.sleep(1000)

            }

        }

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        updateGlobalTime()
        Handler().postDelayed({
            StartTime.visibility= View.GONE
        },2000)


    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    fun gameOver()
    {
        gridView.visibility= View.GONE
        isGameFinished=1;
        llinear.visibility = View.VISIBLE

        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.move)
        llinear.startAnimation(animation)

        timer.visibility= View.GONE
        val currTime = System.currentTimeMillis()
        val timeS= getTime(time,currTime)
        scoreDao.update(currTime-time)
        score.setText("Your Time : ${timeS}")

        if(globalTimeLong>= (currTime-time))
            Toast("Congratualations, You set a new record")





    }


    fun updateTimer(time:String)
    {
        timer.setText(time)
    }

    fun getTime(time:Long,time1:Long):String{

        var time2= time1-time
        time2/=1000;

        val second = time2%60
        val minute= time2/60
        var zeroM=""
        var  zeroS=""
        if(second<=9)
            zeroS="0"
        if(minute<=9)
            zeroM="0"


        var s = "${zeroM}${minute}:${zeroS}${second}"

        return s;


    }

    fun Toast(s:String)
    {
    android.widget.Toast.makeText(this,"$s", android.widget.Toast.LENGTH_SHORT).show()
    }

    fun updateGlobalTime() {
        GlobalScope.launch {
            val temp = scoreDao.get().await().toObject(cloudData::class.java)!!.recordTime
            globalTimeLong= temp
            runOnUiThread {
                globalTime.setText(("Global Minimum Time: ${temp?.let { getTime(0, it) }}"))
            }
        }
    }
}