package dhairyapandya.com.grocforme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        int status = NetworkUtil.getConnectivityStatusString(context);
////        boolean isConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
//        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
//            if (isConnected) {
//                Toast.makeText(context, "FJKRBCBS Connected", Toast.LENGTH_SHORT).show();
//                //write code for internet is disconnected...
//
//            } else {
//                Toast.makeText(context, "FJKRBCBS DisConnected", Toast.LENGTH_SHORT).show();
//
//                //write code for internet is connected...
//
//            }
//        }

        int status = NetworkUtil.getConnectivityStatusString(context);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {

                //write code for internet is disconnected...
                Toast.makeText(context, "FJKRBCBS DisConnected", Toast.LENGTH_SHORT).show();

            } else {

                //write code for internet is connected...
                Toast.makeText(context, "FJKRBCBS Connected", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
