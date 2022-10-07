package com.prianshuprasad.targetcp

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabsIntent
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.spinner_item.*

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "Adactivity"
    private var islink:String=""



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        myAd.setVisibility(View.GONE)
        closebutton.setVisibility(View.GONE)
        val myadurl="https://raw.githubusercontent.com/rphrp1985/contest-schedule-app-helper-repo/main/opttext"
      val myadimageurl="https://raw.githubusercontent.com/rphrp1985/contest-schedule-app-helper-repo/main/Adimage.png"
       val imgv= findViewById(R.id.myAd) as ImageView


        val strings= StringRequest(
            Request.Method.GET,myadurl,
            {
                    response ->
                val x:String= response.toString()



//                Toast.makeText(this,"$x ${x.subSequence(2,3) as String}  $temp ",Toast.LENGTH_LONG).show()
                if(x.subSequence(0,1) as String =="Y")
                {

                    allbutton.setVisibility(View.GONE)
                    text.setVisibility(View.GONE)
                    sitelist.setVisibility(View.GONE)
                    myAd.setVisibility(View.VISIBLE)
                    closebutton.setVisibility(View.VISIBLE)
              Glide.with(this).load(myadimageurl).into(imgv)

                  if(x.subSequence(2,3)as String=="Y"){

                      islink=  x.subSequence(4,x.length) as String
                  }





                }

                // Display the first 500 characters of the response string.

            },
            Response.ErrorListener {
//                Toast.makeText(this,"youfr ad error",Toast.LENGTH_LONG).show()

            })

        MySingleton.getInstance(this).addToRequestQueue(strings)





        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-7730150285838464/4714797795", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.message?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

           override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad was dismissed.")
            }

//            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
//                Log.d(TAG, "Ad failed to show.")
//            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Ad showed fullscreen content.")
                mInterstitialAd = null
            }
        }




        val spinner: Spinner = findViewById(R.id.sitelist)

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this, R.array.sitearray, R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val intent =Intent(this,ScheduleviewActivity::class.java)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {




              when(position){

                  1-> {
                      intent.putExtra("url","https://kontests.net/api/v1/code_chef")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }


                  2-> {
                      intent.putExtra("url","https://kontests.net/api/v1/codeforces")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  3-> {
                      intent.putExtra("url","https://kontests.net/api/v1/top_coder")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  4-> {
                      intent.putExtra("url","https://kontests.net/api/v1/at_coder")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  5-> {
                      intent.putExtra("url","https://kontests.net/api/v1/cs_academy")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  6-> {
                      intent.putExtra("url","https://kontests.net/api/v1/hacker_rank")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  7-> {
                      intent.putExtra("url","https://kontests.net/api/v1/hacker_earth")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  8-> {
                      intent.putExtra("url","https://kontests.net/api/v1/kick_start")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  9-> {
                      intent.putExtra("url","https://kontests.net/api/v1/leet_code")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }




              }



                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this@MainActivity)
                } else {

//            Toast.makeText(this,"not ready",Toast.LENGTH_LONG).show()
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }













//                Toast.makeText(this@MainActivity," $position",Toast.LENGTH_LONG).show()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // sometimes you need nothing here
            }
        }

//






    }




    fun buttonclicked(view: View){




        val intent =Intent(this,ScheduleviewActivity::class.java)
        intent.putExtra("url","https://kontests.net/api/v1/all")
        var temp=0
        intent.putExtra("siteindex","$temp")
        startActivity(intent)
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {

//            Toast.makeText(this,"not ready",Toast.LENGTH_LONG).show()
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }

    }


    fun closebuttonclicked(view: View){
        allbutton.setVisibility(View.VISIBLE)
        text.setVisibility(View.VISIBLE)
        sitelist.setVisibility(View.VISIBLE)
        closebutton.setVisibility(View.GONE)
        myAd.setVisibility(View.GONE)
    }


    fun onmyadclicked(view: View){
//        islink="https://www.google.co.in/"
        val coustomtabs= CustomTabsIntent.Builder()
        val coustomtabintent= coustomtabs.build()
        if(islink!=""){

            coustomtabintent.launchUrl(this, Uri.parse(islink))
        }

    }
    override fun onDestroy() {
        super.onDestroy()

        val serintent= Intent(this, notificationServices::class.java)

        startService(serintent)
        sendBroadcast(Intent("YouWillNeverKillMe"))
//        Toast.makeText(this, "YouWillNeverKillMe TOAST!!", Toast.LENGTH_LONG).show()

    }

}