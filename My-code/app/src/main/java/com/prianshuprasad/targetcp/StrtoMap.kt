package com.prianshuprasad.targetcp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import org.json.JSONObject

class DataManupilate() {


   fun toDataMap(str:String):MutableMap<String,String>  {

//       var arr: ArrayList<data> = ArrayList()

       val map = ObjectMapper().readValue<MutableMap<String, String>>(str)



       return map;

   }

    fun toString(datai : MutableMap<String,String>):String
    {
        val gson = Gson()
        val json = JSONObject(gson.toJson(datai))

        return  json.toString()


    }




}