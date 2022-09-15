package com.alif.crudbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alif.crudbasic.db.Subscribers
import com.alif.crudbasic.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {

    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    //  Coroutine not included, cause it's only a LiveData (Get or Display a Data)
    val subscribers = repository.subscribers

    private lateinit var subscriberToUpdateOrDelete: Subscribers
    private var isUpdateOrDelete: Boolean = false

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDelete(subscribers: Subscribers) {
        inputName.value = subscribers.name
        inputEmail.value = subscribers.email
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Clear"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = name
            subscriberToUpdateOrDelete.email = email
            update(subscriberToUpdateOrDelete)
        } else {
            insert(Subscribers(0, name, email))
            inputName.value = ""
            inputEmail.value = ""
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = ""
            subscriberToUpdateOrDelete.email = ""
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun insert(subscribers: Subscribers) {
        viewModelScope.launch {
            repository.insert(subscribers)
        }
    }

    fun update(subscribers: Subscribers) {
        viewModelScope.launch {
            repository.update(subscribers)

            deleteAndUpdateSupport()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun delete(subscribers: Subscribers) {
        viewModelScope.launch {
            repository.delete(subscribers)

            deleteAndUpdateSupport()
        }
    }

    private fun deleteAndUpdateSupport() {
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }
}