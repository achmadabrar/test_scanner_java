package com.tokopedia.searchonboardingtokped.core.di.module

import com.tokopedia.searchonboardingtokped.ui.activities.SearchActivity
import com.tokopedia.searchonboardingtokped.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesSearchActivity(): SearchActivity
}