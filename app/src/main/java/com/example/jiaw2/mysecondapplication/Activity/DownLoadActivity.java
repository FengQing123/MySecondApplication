package com.example.jiaw2.mysecondapplication.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.jiaw2.mysecondapplication.R;
import com.golshadi.majid.core.DownloadManagerPro;
import com.golshadi.majid.report.ReportStructure;
import com.golshadi.majid.report.listener.DownloadManagerListener;

import java.io.IOException;

/**
 * Created by jiaw2 on 2016/11/24.
 */
public class DownLoadActivity extends Activity implements DownloadManagerListener {

    private String TAG = DownLoadActivity.class.getSimpleName();
    private String downloadPath = "WKCX/MOVIE/DV_123456789";
    private String filename = "2016_1122_143155_176_86032990_1232499739";
    private String httpUrl = "http://192.168.1.254/CARDV/MOVIE/2016_1122_143155_176.MOV";
    private int token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);

        setEnterTran();
        setExitTran();

        final DownloadManagerPro dm = new DownloadManagerPro(this);
        dm.init(downloadPath, 12, this);
        token = dm.addTask(filename, httpUrl, true, false);
        ReportStructure report = dm.singleDownloadStatus(token);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnTransparencyClickHandler();
                try {
                    dm.startDownload(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.pauseDownload(token);
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.delete(token, false);

            }
        });
    }

    @Override
    public void OnDownloadStarted(long taskId) {
        Log.e(TAG, "OnDownloadStarted---taskId==" + taskId);
    }

    @Override
    public void OnDownloadPaused(long taskId) {
        Log.e(TAG, "OnDownloadPaused---taskId==" + taskId);

    }

    @Override
    public void onDownloadProcess(long taskId, double percent, long downloadedLength) {
        Log.e(TAG, "onDownloadProcess---taskId==" + taskId);
        Log.e(TAG, "onDownloadProcess---percent==" + percent);
        Log.e(TAG, "onDownloadProcess---downloadedLength==" + downloadedLength);
    }

    @Override
    public void OnDownloadFinished(long taskId) {
        Log.e(TAG, "OnDownloadFinished----taskId==" + taskId);

    }

    @Override
    public void OnDownloadRebuildStart(long taskId) {
        Log.e(TAG, "OnDownloadRebuildStart----taskId==" + taskId);

    }

    @Override
    public void OnDownloadRebuildFinished(long taskId) {
        Log.e(TAG, "OnDownloadRebuildFinished----taskId==" + taskId);

    }

    @Override
    public void OnDownloadCompleted(long taskId) {
        Log.e(TAG, "OnDownloadCompleted-----taskId==" + taskId);

    }

    @Override
    public void connectionLost(long taskId) {
        Log.e(TAG, "connectionLost------taskId==" + taskId);

    }

    public void turnTransparencyClickHandler() {
        changeBackgroundAlphaTo(0.5f);
    }

    private void changeBackgroundAlphaTo(float alphaValue) {
        final WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = alphaValue;//０.０全透明．１.０不透明．
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getWindow().setAttributes(attributes);
            }
        });
    }

    @TargetApi(21)
    public void setEnterTran() {
        getWindow().setEnterTransition(new Explode().setDuration(2000));
    }

    @TargetApi(21)
    public void setExitTran() {
        getWindow().setExitTransition(new Explode().setDuration(2000));
    }
}
