package com.prianshuprasad.targetcp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class contestAdapter(private val listener: ScheduleviewActivity) :
    RecyclerView.Adapter<contestAdapter.ViewHolder>() {


    private val item: ArrayList<contest> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val conName: TextView
        val contSite: TextView
        val conStime: TextView
        val conEtime: TextView
        val siteImg: ImageView




        init {




            // Define click listener for the ViewHolder's View.
            conName = view.findViewById(R.id.contestname)
            contSite=view.findViewById(R.id.site)
            conStime= view.findViewById(R.id.stime)
            conEtime= view.findViewById(R.id.etime)
            siteImg= view.findViewById(R.id.siteicon)


        }


        interface oncomtestclicked{

            fun onitemclicked(item:contest){}


        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contest_row, viewGroup, false)

        val viewHolder= ViewHolder(view)
        view.setOnClickListener{

            listener.onitemclicked(item[viewHolder.adapterPosition ])
        }



        return viewHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val curritem = item[position]
        val modifystime= curritem.stime
        val modifyetime= curritem.etime








        viewHolder.conName.text= curritem.name
        viewHolder.contSite.text=curritem.site
        viewHolder.conStime.text=timemodifyist(modifystime)
       viewHolder.conEtime.text= timemodifyist((modifyetime))

        if(curritem.site=="HackerRank")
        {
            viewHolder.siteImg.setImageResource(R.drawable.hrl)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="CodeForces")
        {
            viewHolder.siteImg.setImageResource(R.drawable.cflogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="LeetCode")
        {
            viewHolder.siteImg.setImageResource(R.drawable.leetlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="TopCoder")
        {
            viewHolder.siteImg.setImageResource(R.drawable.topcoderlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="AtCoder")
        {
            viewHolder.siteImg.setImageResource(R.drawable.atcoderlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="CS Academy")
        {
            viewHolder.siteImg.setImageResource(R.drawable.csacademylogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else

        if(curritem.site=="HackerEarth")
        {
            viewHolder.siteImg.setImageResource(R.drawable.hackerearthlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="Kick Start")
        {
            viewHolder.siteImg.setImageResource(R.drawable.kickstartlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="LeetCode")
        {
            viewHolder.siteImg.setImageResource(R.drawable.leetlogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
        if(curritem.site=="CodeChef")
        {
            viewHolder.siteImg.setImageResource(R.drawable.cclogo)
//            viewHolder.siteImg.setBackgroundColor(R.drawable.green)
        }else
            viewHolder.siteImg.setImageResource(R.drawable.niimagelogo)


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = item.size


    fun updatenews(array:ArrayList<contest>){

        item.clear()
        item.addAll(array)

        notifyDataSetChanged()

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
