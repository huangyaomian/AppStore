package com.hym.appstore.dagger2.component;


import com.hym.appstore.dagger2.module.AppInfoModule;
import com.hym.appstore.dagger2.module.LoginModule;
import com.hym.appstore.dagger2.scope.FragmentScope;
import com.hym.appstore.ui.activity.LoginActivity;
import com.hym.appstore.ui.fragment.GameFragment;
import com.hym.appstore.ui.fragment.RankingFragment;

import dagger.Component;

@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
