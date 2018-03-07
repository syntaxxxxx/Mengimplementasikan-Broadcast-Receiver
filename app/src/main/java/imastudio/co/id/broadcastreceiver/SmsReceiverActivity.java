package imastudio.co.id.broadcastreceiver;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsReceiverActivity extends AppCompatActivity {

    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_close)
    Button btnClose;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);
        ButterKnife.bind(this);
        //TODO Get Sesuai Type Data
        String noPengirim = getIntent().getStringExtra(EXTRA_SMS_NO);
        String pesanPengirim = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);
        tvNo.setText("from : " + noPengirim);
        tvMessage.setText(pesanPengirim);
    }

    //TODO Click Event
    @OnClick(R.id.btn_close)
    public void onViewClicked(View view) {
    }
}
