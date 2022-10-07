package com.prianshuprasad.targetcp

import android.app.Service
import android.content.Context
import android.content.Intent

import android.os.IBinder



class BookSyncAdapterService : Service() {
    /*
     * Instantiate the sync adapter object.
     */
    override fun onCreate() {
        super.onCreate()
        /**
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized(sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter =
                    BookSyncAdapter(   getApplicationContext(),
                        true)
            }
        }
    }

    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     *
     */

   override  fun onBind(intent: Intent?): IBinder {
        /**
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter!!.syncAdapterBinder
    }

    companion object {
        // Storage for an instance of the sync adapter
        private var sSyncAdapter: BookSyncAdapter? = null

        // Object to use as a thread-safe lock
        private val sSyncAdapterLock = Any()
    }
}