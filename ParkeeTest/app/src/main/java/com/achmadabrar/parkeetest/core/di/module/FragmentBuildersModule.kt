package com.tokopedia.searchonboardingtokped.core.di.module

import com.tokopedia.searchonboardingtokped.ui.fragments.SearchFragment
import com.tokopedia.searchonboardingtokped.ui.fragments.LocalMerchantFragment
import com.tokopedia.searchonboardingtokped.ui.fragments.NewProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLocalMerchantModule(): LocalMerchantFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeProduct(): NewProductFragment
}