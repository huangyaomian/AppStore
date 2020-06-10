package com.hym.appstore.bean;

import java.io.Serializable;
import java.util.List;

public class AppInfoBean  implements Serializable {


    /**
     * addTime : 0
     * hasSameDevApp : false
     * videoId : 2531
     * versionName : 6.23.1.951
     * ratingScore : 5.0
     * briefShow : 动漫二次元搞笑弹幕视频社区
     * developerId : 0
     * fitness : 0
     * id : 56059
     * level1CategoryId : 27
     * releaseKeyHash : 3c95754d1f1c57dabe42486acf0e4da3
     * relateAppHasMore : false
     * rId : 0
     * suitableType : 0
     * briefUseIntro : false
     * ads : 0
     * publisherName : 北京弹幕网络科技有限公司
     * level2CategoryId : 132
     * position : 0
     * favorite : false
     * isFavorite : false
     * appendSize : 0
     * level1CategoryName : 影音视听
     * samDevAppHasMore : false
     * displayName : AcFun
     * icon : AppStore/0dd565d7520745fd8ca624b3c868d529843400241
     * screenshot : AppStore/088eb4e1ef0d155c768ef0dd328b4764bca43a404,AppStore/0d3c5c400fab8495a20d4f006d518e5310176fe7a,AppStore/0a3c540009a186957a0d49006df18f5e1ef427dec,AppStore/0ab8975b101cf4cd8044f1cc7338ddb09a8515961,AppStore/003c5c400fa98495720d49006de18e511017dfe7a
     * ratingTotalCount : 0
     * adType : 1
     * apkSize : 70684702
     * packageName : tv.acfundanmaku.video
     * updateTime : 1591006104718
     * grantCode : 0
     * versionCode : 951
     * appTags : []
     * diffFileSize : 0
     * hdIcon : {"main":"AppStore/0737e44738f7649ba1438f6b68f65d5d34c678cc9"}
     * source :
     */

    private int addTime;
    private boolean hasSameDevApp;
    private int videoId;
    private String versionName;
    private double ratingScore;
    private String briefShow;
    private int developerId;
    private int fitness;
    private int id;
    private int level1CategoryId;
    private String releaseKeyHash;
    private boolean relateAppHasMore;
    private int rId;
    private int suitableType;
    private boolean briefUseIntro;
    private int ads;
    private String publisherName;
    private int level2CategoryId;
    private int position;
    private boolean favorite;
    private boolean isFavorite;
    private int appendSize;
    private String level1CategoryName;
    private boolean samDevAppHasMore;
    private String displayName;
    private String icon;
    private String screenshot;
    private int ratingTotalCount;
    private int adType;
    private int apkSize;
    private String packageName;
    private long updateTime;
    private int grantCode;
    private int versionCode;
    private int diffFileSize;
    private HdIconBean hdIcon;
    private String source;
    private List<Tag> appTags;

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public boolean isHasSameDevApp() {
        return hasSameDevApp;
    }

    public void setHasSameDevApp(boolean hasSameDevApp) {
        this.hasSameDevApp = hasSameDevApp;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public boolean isRelateAppHasMore() {
        return relateAppHasMore;
    }

    public void setRelateAppHasMore(boolean relateAppHasMore) {
        this.relateAppHasMore = relateAppHasMore;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public int getAds() {
        return ads;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public boolean isSamDevAppHasMore() {
        return samDevAppHasMore;
    }

    public void setSamDevAppHasMore(boolean samDevAppHasMore) {
        this.samDevAppHasMore = samDevAppHasMore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getRatingTotalCount() {
        return ratingTotalCount;
    }

    public void setRatingTotalCount(int ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getApkSize() {
        return apkSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(int grantCode) {
        this.grantCode = grantCode;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public HdIconBean getHdIcon() {
        return hdIcon;
    }

    public void setHdIcon(HdIconBean hdIcon) {
        this.hdIcon = hdIcon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Tag> getAppTags() {
        return appTags;
    }

    public void setAppTags(List<Tag> appTags) {
        this.appTags = appTags;
    }

    public static class Tag {
        /**
         * "tagId": 159,
         * "link": "sametag/159",
         * "tagName": "占卜"
         *
         */

        private int tagId;
        private String link;
        private String tagName;

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }



    }

    public static class HdIconBean {
        /**
         * main : AppStore/0737e44738f7649ba1438f6b68f65d5d34c678cc9
         */

        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

}
