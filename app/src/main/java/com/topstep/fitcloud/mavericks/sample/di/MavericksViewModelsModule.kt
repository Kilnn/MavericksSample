package com.topstep.fitcloud.mavericks.sample.di

import com.topstep.fitcloud.mavericks.sample.ModifyPwdViewModel
import com.topstep.fitcloud.mavericks.sample.di.mavericks.AssistedViewModelFactory
import com.topstep.fitcloud.mavericks.sample.di.mavericks.MavericksViewModelComponent
import com.topstep.fitcloud.mavericks.sample.di.mavericks.MavericksViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface MavericksViewModelsModule {
    @Binds
    @IntoMap
    @MavericksViewModelKey(ModifyPwdViewModel::class)
    fun signInViewModelFactory(factory: ModifyPwdViewModel.Factory): AssistedViewModelFactory<*, *>
}