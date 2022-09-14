package com.alif.crudbasic.repository

import com.alif.crudbasic.db.SubscriberDAO
import com.alif.crudbasic.db.Subscribers

/* TODO: The repository is used as a bridge between the "Data" and the "View" */

class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscriber()

    suspend fun insert(subscribers: Subscribers) {
        dao.insertSubscriber(subscribers)
    }

    suspend fun update(subscribers: Subscribers) {
        dao.updateSubscriber(subscribers)
    }

    suspend fun delete(subscribers: Subscribers) {
        dao.deleteSubscriber(subscribers)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}