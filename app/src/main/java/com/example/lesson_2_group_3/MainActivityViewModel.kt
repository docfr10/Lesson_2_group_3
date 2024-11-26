package com.example.lesson_2_group_3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Класс MainActivityViewModel, наследник системного класса ViewModel
// Имеет доступ к жизненному циклу ViewModel
class MainActivityViewModel : ViewModel() {
    // Свойство, которое имеет тип данных MutableLiData
    private val editText = MutableLiveData<String>()

    fun getEditText(): MutableLiveData<String> = editText
}