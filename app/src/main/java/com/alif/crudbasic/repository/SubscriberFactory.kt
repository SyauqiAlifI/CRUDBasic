package com.alif.crudbasic.repository

import com.alif.crudbasic.db.SubscriberDAO

class SubscriberFactory(private val dao: SubscriberDAO) {
    val subscribers = dao.getAllSubscriber()
}