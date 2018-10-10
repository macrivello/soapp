package co.cognized.SOApp.di

import co.cognized.SOApp.network.SOApi
import dagger.Component

@Component(modules = arrayOf(NetworkModule::class))
interface ApiInjector {
    fun provideApi() : SOApi
}