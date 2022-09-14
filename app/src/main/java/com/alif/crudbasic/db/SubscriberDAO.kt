package com.alif.crudbasic.db

import androidx.lifecycle.LiveData
import androidx.room.*

/* TODO: The Database table might be locked in the main thread */

//  Access Database table (unlocked it using coroutines)
@Dao
interface SubscriberDAO {

    /* TODO: Using suspend function so it can be paused and resume in a specific time,
    *   Also suspend functions works in background */

    @Insert
    suspend fun insertSubscriber(subscriber: Subscribers): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscribers)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscribers)

    @Query("DELETE FROM tb_subscriber")
    suspend fun deleteAll()

    /* TODO: Not using suspend, cause it's only return a LiveData,
    *   Which means we only get (view) the data's not edit (access) it */

    @Query("SELECT * FROM tb_subscriber")
    fun getAllSubscriber(): LiveData<List<Subscribers>>
}