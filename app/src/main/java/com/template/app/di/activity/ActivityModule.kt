package com.template.app.di.activity

import android.app.Activity
import com.template.app.ui.BottomNavigationController
import com.template.app.ui.MainActivity
import com.template.app.ui.Navigator
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
    fun provideNavigator(activity: Activity): Navigator {
        return (activity as MainActivity).getNavigator()
    }
}