package com.hym.appstore.dagger2.module;

import android.app.Application;
import android.os.Environment;

import com.hym.appstore.common.Constant;
import com.hym.appstore.common.utils.ACache;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application, Retrofit retrofit,File downDir){

        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR,downDir.getPath());

        return RxDownload.getInstance(application)
                .defaultSavePath(downDir.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10)
                .maxRetryCount(2);//下载失败重试次数;
    }

    @Provides
    @Singleton
    public File provideDownloadDir(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

}
