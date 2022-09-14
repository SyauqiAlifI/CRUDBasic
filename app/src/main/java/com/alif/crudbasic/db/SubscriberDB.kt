package com.alif.crudbasic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* TODO: This is the actual database */

// The annotation works to indicate the room that this (file) is a Database
@Database(entities = [Subscribers::class], version = 1)
abstract class SubscriberDB: RoomDatabase() {

    abstract val subscriberDao: SubscriberDAO

    /* TODO: Use only one interface room for the whole app
    *   to avoid an unexpected errors */

    // Create a singleton (explain later) using a companion object
    companion object {

        /* This annotation indicates that these fields will immediately made visible to other threads. */
        @Volatile
        private var INSTANCE : SubscriberDB? = null

        fun getInstance(context: Context): SubscriberDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDB::class.java,
                        "subscriber_tb.db"
                    ).build()
                }
                return instance
            }
        }

    }
}