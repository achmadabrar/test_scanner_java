package com.achmadabrar.parkeetest.core.di.component

import android.app.Application
import com.tokopedia.searchonboardingtokped.core.base.BaseApplication
import com.tokopedia.searchonboardingtokped.core.di.module.ActivityBuildersModule
import com.tokopedia.searchonboardingtokped.core.di.module.FragmentBuildersModule
import com.tokopedia.searchonboardingtokped.core.di.module.NetworkModule
import com.tokopedia.searchonboardingtokped.core.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        abstract fun networkModule(networkModule: NetworkModule): Builder
    }
}