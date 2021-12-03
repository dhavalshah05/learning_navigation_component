package com.template.app.di.activity

import android.app.Activity
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

}