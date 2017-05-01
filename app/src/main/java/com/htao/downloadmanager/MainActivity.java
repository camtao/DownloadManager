package com.htao.downloadmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.htao.downloadmanager.downloader.DownloadEntry;
import com.htao.downloadmanager.downloader.DownloadManger;
import com.htao.downloadmanager.downloader.DownloadWatcher;
import com.htao.downloadmanager.utils.Trace;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadManger mDownloadManger;
    private DownloadEntry mDownloadEntry;

    DownloadWatcher mDownloadWatcher=new DownloadWatcher() {

        @Override
        public void notityDownloadChange(DownloadEntry downloadEntry) {
            mDownloadEntry = downloadEntry;
            if (mDownloadEntry.mDownloadStatus== DownloadEntry.DownloadStatus.cancel){
                mDownloadEntry=null;
            }
            Trace.d(downloadEntry.toString());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_download).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_resume).setOnClickListener(this);
        mDownloadManger = DownloadManger.getInstance(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        mDownloadManger.addWatcher(mDownloadWatcher);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadManger.deteleWatcher(mDownloadWatcher);
    }

    @Override
    public void onClick(View v) {
        if (mDownloadEntry==null){
            mDownloadEntry = new DownloadEntry();
            mDownloadEntry.name="下载测试";
            mDownloadEntry.downloadUrl="www.baidu.com";
            mDownloadEntry.mDownloadStatus= DownloadEntry.DownloadStatus.downloading;
        }
        switch (v.getId()){
            case R.id.btn_download:
                mDownloadManger.add(mDownloadEntry);
                break;
            case R.id.btn_pause:
                mDownloadManger.pause(mDownloadEntry);
                break;
            case R.id.btn_resume:
                mDownloadManger.resume(mDownloadEntry);
                break;
            case R.id.btn_cancel:
                mDownloadManger.cancel(mDownloadEntry);
                break;
        }

    }
}
