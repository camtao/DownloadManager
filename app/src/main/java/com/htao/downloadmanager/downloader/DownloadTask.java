package com.htao.downloadmanager.downloader;

/**
 * Created by 10607 on 2017/4/30.
 */

public class DownloadTask implements Runnable{

    private  DownloadEntry entry;
    private boolean isPaused;
    private boolean isCanceled;

    public DownloadTask(DownloadEntry entry) {
        this.entry=entry;
    }

    public void start() {
        entry.mDownloadStatus= DownloadEntry.DownloadStatus.downloading;
        int totalLength=1024*100;
        entry.totalLength=totalLength;
        for (long i= entry.curLength; i <entry.totalLength; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isPaused||isCanceled){
                entry.mDownloadStatus=isPaused? DownloadEntry.DownloadStatus.pause: DownloadEntry.DownloadStatus.cancel;
                DownloadChanger.getInstance().postChange(entry);
                return;
            }
            entry.curLength+=1024;
            DownloadChanger.getInstance().postChange(entry);
        }
        entry.mDownloadStatus= DownloadEntry.DownloadStatus.completed;
        DownloadChanger.getInstance().postChange(entry);
    }

    public void pause() {
        isPaused=true;
    }

    public void cancel() {
        isCanceled= true;
    }

    @Override
    public void run() {
        start();
    }
}
