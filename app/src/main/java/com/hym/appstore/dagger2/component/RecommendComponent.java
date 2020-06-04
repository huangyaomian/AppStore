package com.hym.appstore.dagger2.component;

import com.hym.appstore.dagger2.module.RecommendModule;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.fragment.RecommendFragment;

import dagger.Component;

@Component(modules = RecommendModule.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
