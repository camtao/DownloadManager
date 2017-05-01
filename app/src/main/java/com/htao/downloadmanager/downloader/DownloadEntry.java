package com.htao.downloadmanager.downloader;

import java.io.Serializable;

/**
 * Created by 10607 on 2017/4/30.
 */

public class DownloadEntry implements Serializable {
    public String id;
    public String downloadUrl;
    public String name;

    public enum DownloadStatus {waiting, downloading, pause, resume, cancel,completed}

    public DownloadStatus mDownloadStatus;

    public long curLength;
    public long totalLength;

    @Override
    public String toString() {
        return "DownloadEntry: " + downloadUrl + " is " + mDownloadStatus.name() + " with " + curLength + "/" + totalLength;
    }
}
