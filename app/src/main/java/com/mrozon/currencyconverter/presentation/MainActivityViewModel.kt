package com.mrozon.currencyconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrozon.currencyconverter.CoroutineContextDispatchers
import com.mrozon.currencyconverter.data.repository.IUpdateValutesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: IUpdateValutesRepository,
    private val dispatchers: CoroutineContextDispatchers
): ViewModel() {
    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception,"updateDb error")
    }
    fun updateDb() {
        viewModelScope.launch(dispatchers.IO+handler) {
            repository.update()
        }
    }
}
