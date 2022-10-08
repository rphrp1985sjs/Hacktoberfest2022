package com.prianshuprasad.brainsmasher

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class cloudDao {

    val db = FirebaseFirestore.getInstance()
    val collection= db.collection("Score")



    fun get():Task<DocumentSnapshot>{

        return collection.document("Score").get()

    }



    fun update( time:Long) {

        GlobalScope.launch {
            val temp = get().await().toObject(cloudData::class.java)!!.recordTime
            if(temp>=time){
            collection.document("Score").set(cloudData(time))
        }
        }
    }


}