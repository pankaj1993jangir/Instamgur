package com.pj.instamgur.presentation.di.components

import com.pj.instamgur.presentation.di.scope.ActivityScope
import com.pj.instamgur.presentation.view.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}