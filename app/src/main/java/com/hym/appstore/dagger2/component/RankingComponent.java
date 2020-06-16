package com.hym.appstore.dagger2.component;


import com.hym.appstore.dagger2.module.RankingModule;
import com.hym.appstore.dagger2.scope.FragmentScope;
import com.hym.appstore.ui.fragment.RankingFragment;

import dagger.Component;

@FragmentScope
@Component(modules = RankingModule.class,dependencies = AppComponent.class)
public interface RankingComponent {
    void inject(RankingFragment fragment);
}
