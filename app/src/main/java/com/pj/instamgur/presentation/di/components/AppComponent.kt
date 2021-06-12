package com.pj.instamgur.presentation.di.components

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
//@Component( modules = [::class])
interface AppComponent {
    fun inject(application: Application)
}