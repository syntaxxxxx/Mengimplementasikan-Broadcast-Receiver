package imastudio.co.id.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_download)
    Button btnDownload;

    //TODO 3 Accept Broadcast Result Download From DownloadService
    public static final String ACTION_DOWNLOAD_STATUS = "action_download_status";
    private BroadcastReceiver downloadReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Incoming Message");

        //TODO 4 Setelah Acc Objek Ini Dieksekusi
        downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Download Done", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter downloadIntentFilter = new IntentFilter(ACTION_DOWNLOAD_STATUS);
        registerReceiver(downloadReceiver, downloadIntentFilter);
    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        Intent downloadServiceIntent = new Intent(this, DownloadService.class);
        startService(downloadServiceIntent);
    }

    //TODO Copot Object downloadReceiver
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver);
        }
    }
}
