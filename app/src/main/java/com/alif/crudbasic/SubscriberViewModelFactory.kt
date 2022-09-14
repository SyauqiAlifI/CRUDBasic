package com.alif.crudbasic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alif.crudbasic.repository.SubscriberRepository

class SubscriberViewModelFactory(private val repository: SubscriberRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown")
        }
    }
}