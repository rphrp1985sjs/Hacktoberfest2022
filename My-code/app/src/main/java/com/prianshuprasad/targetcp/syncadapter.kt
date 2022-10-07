package com.prianshuprasad.targetcp

import android.accounts.Account
import android.accounts.AccountManager
import android.content.*
import android.os.Bundle


class BookSyncAdapter : AbstractThreadedSyncAdapter {
    // Global variables
    // Define a variable to contain a content resolver instance
    var contentResolver: ContentResolver
    private val mAccountManager: AccountManager

    /**
     * Set up the sync adapter
     */
    constructor(context: Context, autoInitialize: Boolean) : super(context, autoInitialize) {
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */contentResolver = context.getContentResolver()
        mAccountManager = AccountManager.get(context)
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    constructor(context: Context, autoInitialize: Boolean, allowParallelSyncs: Boolean) : super(
        context,
        autoInitialize,
        allowParallelSyncs) {
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */contentResolver = context.getContentResolver()
        mAccountManager = AccountManager.get(context)
    }

    /**
     * Specify the code you want to run in the sync adapter. The entire
     * sync adapter runs in a background thread, so you don't have to set
     * up your own background processing.
     */
    override fun onPerformSync(
        account: Account,
        extras: Bundle,
        authority: String,
        provider: ContentProviderClient,
        syncResult: SyncResult,
    ) {

        // Get the auth token for the current account
        //String authToken = mAccountManager.blockingGetAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, true);

        //Perform your network operation using the authToken

        //Store the data gotten from the server to the local database using the provider object

        //Vice Versa of above operation

        //release all resources at the end
    }
}