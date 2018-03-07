package imastudio.co.id.broadcastreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

//TODO 1 Download File Secara Asynchronous
public class DownloadService extends IntentService {

    public static final String TAG = "Download Service";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Download Service Dijalankan");
        if (intent != null) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TODO 2 Send Result Download
            Intent notifyFinishIntent = new Intent(MainActivity.ACTION_DOWNLOAD_STATUS);
            sendBroadcast(notifyFinishIntent);
        }
    }
}

