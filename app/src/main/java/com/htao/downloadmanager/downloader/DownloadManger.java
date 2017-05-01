package com.htao.downloadmanager.downloader;

import android.content.Context;
import android.content.Intent;

/**
 * Created by 10607 on 2017/4/30.
 */

public class DownloadManger {

    private static DownloadManger instance ;
    private final Context mContext;

    private DownloadManger(Context context){
        mContext =context;
    }

    public static DownloadManger getInstance(Context context){
        if (instance == null) {
            synchronized (DownloadManger.class){
                if (instance == null) {
                    instance = new DownloadManger(context) ;
                }
            }
        }
        return instance ;
    }

    public void add(DownloadEntry downloadEntry){
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY,downloadEntry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION,Constants.KEY_DOWNLOAD_ACTION_ADD);
        mContext.startService(intent);

    }

    public void pause(DownloadEntry downloadEntry){
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY,downloadEntry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION,Constants.KEY_DOWNLOAD_ACTION_PAUSE);
        mContext.startService(intent);
    }

    public void cancel(DownloadEntry downloadEntry){
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY,downloadEntry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION,Constants.KEY_DOWNLOAD_ACTION_CANCEL);
        mContext.startService(intent);

    }

    public void resume(DownloadEntry downloadEntry){
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(Constants.KEY_DOWNLOAD_ENTRY,downloadEntry);
        intent.putExtra(Constants.KEY_DOWNLOAD_ACTION,Constants.KEY_DOWNLOAD_ACTION_RESUME);
        mContext.startService(intent);

    }

    public void addWatcher(DownloadWatcher downloadWatcher){
        DownloadChanger.getInstance().addObserver(downloadWatcher);
    }

    public void deteleWatcher(DownloadWatcher downloadWatcher){
        DownloadChanger.getInstance().deleteObserver(downloadWatcher);
    }
}
