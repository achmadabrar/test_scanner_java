package com.achmadabrar.myapplication.core.di

import com.achmadabrar.myapplication.core.base.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = []
)
interface Application: AndroidInjector<BaseApplication> {
}