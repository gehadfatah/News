package com.goda.newstk.core.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.goda.newstk.data.common.ApplicationIntegration
import dagger.hilt.android.HiltAndroidApp
import java.util.*
@HiltAndroidApp
class NewApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationIntegration.with(this)

    }


    companion object {

        private var instance: NewApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
         fun setLocaleEn(context: Context) {
            val locale = Locale("en_US")
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            context.applicationContext.resources.updateConfiguration(config, null)
        }

    }


}