package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import util.UIHelper;

/**
 * Created by jiaw2 on 2016/10/21.
 */
public class AlarmTestReceiver extends BroadcastReceiver {
    private String TAG = AlarmTestReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        UIHelper.ToastMessage(context, msg);
        Log.e(TAG, "-------receiver");
    }
}
