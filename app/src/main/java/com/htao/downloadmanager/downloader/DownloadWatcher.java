package com.htao.downloadmanager.downloader;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 10607 on 2017/4/30.
 */

public abstract class DownloadWatcher implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof DownloadEntry){
            notityDownloadChange((DownloadEntry) arg);
        }
    }

    public abstract void notityDownloadChange(DownloadEntry downloadEntry) ;
}
