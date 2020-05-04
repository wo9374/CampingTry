package com.example.mylogin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
    private Activity activity;

    public NetworkReceiver() {
        super();
    }
    public NetworkReceiver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();

        // 네트워크 상태 값 받아오기
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            try {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
                NetworkInfo _wifi_network =
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if(_wifi_network != null) {
                    // wifi, 3g 둘 중 하나라도 있을 경우
                    if(_wifi_network != null && activeNetInfo != null){
                        LoginActivity.tv_state.setText("");
                    }
                    // wifi, 3g 둘 다 없을 경우
                    else{
                        LoginActivity.tv_state.setText("네트워크 상태를 확인해주세요..");
                    }
                }
            } catch (Exception e) {
                Log.i("ULNetworkReceiver", e.getMessage());
            }
        }

//        // 네트워크 상태 값 받아오기
//        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction()))
//        {
//            NetworkInfo info = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
//            NetworkInfo.DetailedState state = info.getDetailedState();
//            if (state == NetworkInfo.DetailedState.CONNECTED) {//네트워크 연결 상태
//                LoginActivity.tv_state.setText("");
//            } else if (state == NetworkInfo.DetailedState.DISCONNECTED){//네트워크 비연결 상태
//                LoginActivity.tv_state.setText("네트워크 상태를 확인해주세요..");
//            }
//        }
    }
}
