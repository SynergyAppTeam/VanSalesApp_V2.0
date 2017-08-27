package synergy.ps.vansalesapp.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ahmadnasser on 12/7/16.
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context," connectivity changed", Toast.LENGTH_SHORT).show();
    }
}
