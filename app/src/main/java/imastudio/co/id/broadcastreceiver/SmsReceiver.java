package imastudio.co.id.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    //TODO Process Message
    final SmsManager sms = SmsManager.getDefault();

    public SmsReceiver() {
    }

    //TODO Process SMS Yang Masuk
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle mBundle = intent.getExtras();
        try {
            if (mBundle != null) {
                final Object[] pdusObj = (Object[]) mBundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = getIncomingMessage(pdusObj[i], mBundle);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("Sms Receiver", "noPengirim: " + senderNum + "message: " + message);
                    //TODO Run Receiver Activitynya Via Intent
                    Intent showIntent = new Intent(context, SmsReceiverActivity.class);
                    showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, phoneNumber);
                    showIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message);
                    context.startActivity(showIntent);
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }

    //TODO Get Object currentMessage
    private SmsMessage getIncomingMessage(Object o, Bundle mBundle) {
        SmsMessage currentSms;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = mBundle.getString("format");
            currentSms = SmsMessage.createFromPdu((byte[]) o, format);
        } else {
            currentSms = SmsMessage.createFromPdu((byte[]) o);
        }
        return currentSms;
    }
}
