package com.joses.amphibian

import android.app.Application
import com.joses.amphibian.data.AppContainer
import com.joses.amphibian.data.DefaultAppContainer

class AmphibianDataApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}