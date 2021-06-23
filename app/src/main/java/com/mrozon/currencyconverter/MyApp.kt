package com.mrozon.currencyconverter

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.mrozon.currencyconverter.data.repository.IUpdateValutesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyApp: Application() {

    @Inject
    lateinit var repository: IUpdateValutesRepository

    override fun onCreate() {
        super.onCreate()
        initTimber()
        updateDb()
    }

    private fun updateDb() {
        Timber.d("updating db...")
        val handler = CoroutineExceptionHandler { _, exception ->
            Timber.e(exception,"updateDb error")
        }
        GlobalScope.launch(Dispatchers.Default+handler) {
            repository.update()
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        const val PRECISION = 4
    }
}
