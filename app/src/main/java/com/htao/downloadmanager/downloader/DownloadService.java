package com.htao.downloadmanager.downloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 10607 on 2017/4/30.
 */

public class DownloadService extends Service {

   public HashMap<String,DownloadTask> downloadTasks=new HashMap<>();
    private ExecutorService mExecutorService;

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutorService = Executors.newCachedThreadPool();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        DownloadEntry downloadEntry = (DownloadEntry) intent.getSerializableExtra(Constants.KEY_DOWNLOAD_ENTRY);
        //TODO 判断action 进行操作
        int action = intent.getIntExtra(Constants.KEY_DOWNLOAD_ACTION, Constants.KEY_DOWNLOAD_ACTION_ADD);
        doAction(action,downloadEntry);
    }

    private void doAction(int action, DownloadEntry entry) {
        switch (action){
            case Constants.KEY_DOWNLOAD_ACTION_ADD:
                startDownload(entry);
                break;
            case Constants.KEY_DOWNLOAD_ACTION_PAUSE:
                pauseDownload(entry);
                break;
            case Constants.KEY_DOWNLOAD_ACTION_RESUME:
                resumeDownload(entry);
                break;
            case Constants.KEY_DOWNLOAD_ACTION_CANCEL:
                cancelDownload(entry);
                break;
        }
    }

    private void resumeDownload(DownloadEntry entry) {
        startDownload(entry);
    }

    private void cancelDownload(DownloadEntry entry) {
        DownloadTask downloadTask = downloadTasks.get(entry.id);
        if (downloadTask!=null){
            downloadTask.cancel();
        }
    }

    private void pauseDownload(DownloadEntry entry) {
        DownloadTask downloadTask = downloadTasks.get(entry.id);
        if (downloadTask!=null){
            downloadTask.pause();
        }
    }

    private void startDownload(DownloadEntry entry) {
        DownloadTask downloadTask = new DownloadTask(entry);
        downloadTasks.put(entry.id,downloadTask);
        mExecutorService.execute(downloadTask);

    }

}
