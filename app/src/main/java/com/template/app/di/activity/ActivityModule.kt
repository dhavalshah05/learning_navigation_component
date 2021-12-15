package com.template.app.di.activity

import android.app.Activity
import com.template.app.ui.MainNavigator
import com.template.app.ui.base.BaseActivity
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    /*
    * ****************************************
    * Keyboard
    * ****************************************
    * */

    @Provides
    @ActivityScoped
    fun provideKeyboardVisibilityHandler(activity: Activity): KeyboardVisibilityHandler {
        return KeyboardVisibilityHandler(activity)
    }

    @Provides
    @ActivityScoped
    fun provideBaseActivity(activity: Activity): BaseActivity {
        return activity as BaseActivity
    }

    @Provides
    @ActivityScoped
    fun provideNavigator(activity: BaseActivity): MainNavigator {
        return MainNavigator(activity.supportFragmentManager)
    }
}