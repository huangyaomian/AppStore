package com.hym.appstore.dagger2.component;


import com.hym.appstore.dagger2.module.AppInfoModule;
import com.hym.appstore.dagger2.scope.FragmentScope;
import com.hym.appstore.ui.fragment.RankingFragment;

import dagger.Component;

@FragmentScope
@Component(modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {
    void inject(RankingFragment fragment);
}
