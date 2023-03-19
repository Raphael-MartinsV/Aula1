package com.example.aula1

import android.app.Application
import com.example.aula1.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        //starta as dependências do Koin setando os módulos
        //setando o contexto do SharedPreferences
        startKoin {
            androidLogger()
            androidContext(this@MyApp)

            modules(mainModule)

            instance = this@MyApp
            prefs = Prefs(applicationContext)
        }
    }

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: MyApp
        private set
    }
}