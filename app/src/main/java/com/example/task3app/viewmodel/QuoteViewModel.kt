package com.example.task3app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3app.model.ZenQuote
import com.example.task3app.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val _quote = MutableStateFlow<ZenQuote?>(null)
    val quote: StateFlow<ZenQuote?> = _quote

    fun fetchQuote() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRandomQuote()
                if (response.isNotEmpty()) {
                    _quote.value = response[0]
                } else {
                    _quote.value = ZenQuote("No quote found", "System")
                }
            } catch (e: Exception) {
                _quote.value = ZenQuote("Network error occurred", "Error")
            }
        }
    }
}
