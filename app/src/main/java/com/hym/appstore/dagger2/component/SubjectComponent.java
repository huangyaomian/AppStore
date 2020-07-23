package com.hym.appstore.dagger2.component;


import com.hym.appstore.dagger2.module.AppDetailModule;
import com.hym.appstore.dagger2.module.SubjectModule;
import com.hym.appstore.dagger2.scope.FragmentScope;
import com.hym.appstore.ui.fragment.AppDetailFragment;

import dagger.Component;

@FragmentScope
@Component(modules = SubjectModule.class,dependencies = AppComponent.class)
public interface SubjectComponent {
    void inject(AppDetailFragment fragment);
}
