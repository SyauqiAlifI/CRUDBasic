package com.alif.crudbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alif.crudbasic.db.Subscribers
import com.alif.crudbasic.repository.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    //  Coroutine not included, cause it's only a LiveData (Get or Display a Data)
    val subscribers = repository.subscribers

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscribers(0, name, email))
        inputName.value = ""
        inputEmail.value = ""
    }

    fun clearAllOrDelete() {
        clearAll()
    }

    fun insert(subscribers: Subscribers) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(subscribers)
        }
    }

    fun update(subscribers: Subscribers) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscribers)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun delete(subscribers: Subscribers) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscribers)
        }
    }
}