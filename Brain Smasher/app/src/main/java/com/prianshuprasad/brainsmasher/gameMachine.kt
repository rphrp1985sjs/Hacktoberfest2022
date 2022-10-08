package com.prianshuprasad.brainsmasher

import java.util.Collections.swap
import kotlin.random.Random

class gameMachine(private var size:Int=4) {

    private  val list : MutableList<Box> = ArrayList();
    private var availableIndex : MutableList<Int> = ArrayList();
    private  var indexOne:Int=0
    private  var indexTwo:Int=0
    private var sizeD:Int=size*size -1;

    // Generates random value (sync with time)
    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }



    // initialize variables
    private fun Intiliaze(){
        var prev:Int =0;
        for(i in 0..sizeD)
        {
            if(i%2==0){
                prev = rand(1,6);
            }
            list.add(Box(prev))
            availableIndex.add(i)


        }
    }

   // generates a random Index to place card
   private fun swap(){
       indexOne= rand(0,sizeD)
       indexTwo= rand(0,sizeD)

       var temp = list[indexTwo].label
       list[indexTwo].label = list[indexOne].label
       list[indexOne].label= temp;

    }

    // create
   fun create()
   {
       Intiliaze()

      for(i in 0..sizeD+rand(100,200))
          swap()

   }

    // returns list
    fun getList(): MutableList<Box>
    {
    return list
    }


    // validiates user choice
    fun validiateUserChoice(AIChoice:Int,userChoice:Int):Boolean
    {
        var temp = false;
        if(list[AIChoice].label== list[userChoice].label)
        {
            temp=true
            list[AIChoice].label=0
            list[userChoice].label=0

            availableIndex.remove(AIChoice)
            availableIndex.remove(userChoice)


        }else
            temp=false


        return temp

    }

    fun isGameFinish():Boolean{
        if(availableIndex.size==0)
            return true;
        return false;
    }



}