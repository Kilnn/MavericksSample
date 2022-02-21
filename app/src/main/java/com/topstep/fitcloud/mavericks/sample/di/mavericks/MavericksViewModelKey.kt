package com.topstep.fitcloud.mavericks.sample.di.mavericks

import com.airbnb.mvrx.MavericksViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * A [MapKey] for populating a map of ViewModels and their factories.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class MavericksViewModelKey(val value: KClass<out MavericksViewModel<*>>)