package com.baway.yuwentao.application;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.io.File;

/**
 * 类用途 :Application配置类
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 9:02
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        try {
            File cacheDir = StorageUtils.getOwnCacheDirectory(this, Environment.getExternalStorageDirectory().getPath());

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                    .threadPoolSize(3)//配置线程的数量
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))//内存缓存图片
                    .diskCache(new UnlimitedDiskCache(cacheDir))//配置sdcard路径
                    .diskCacheSize(50 * 1024 * 1024)//sdcard上缓存50M的图片
                    .diskCacheFileCount(100)//缓存文件的数量 100
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    .build();

            ImageLoader.getInstance().init(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
