package com.hym.appstore.bean;

import java.util.List;

public class HomeBean {

    private List<BannerBean> banners;

    private List<AppInfoBean> homeApps;

    private List<AppInfoBean> homeGames;

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public List<AppInfoBean> getHomeApps() {
        return homeApps;
    }

    public void setHomeApps(List<AppInfoBean> homeApps) {
        this.homeApps = homeApps;
    }

    public List<AppInfoBean> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(List<AppInfoBean> homeGames) {
        this.homeGames = homeGames;
    }


}
