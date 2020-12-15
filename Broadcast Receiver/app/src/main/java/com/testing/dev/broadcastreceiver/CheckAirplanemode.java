package com.testing.dev.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class CheckAirplanemode extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED){

            boolean isActive = isActive(context);

            if(isActive){
                Toast.makeText(context,"Airplane mode On",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context,"Airplane mode Off",Toast.LENGTH_LONG).show();
            }

        }

    }

    private boolean isActive(Context context) {

        return Settings.System.getInt(context.getContentResolver(),Settings.System.AIRPLANE_MODE_ON,-1) !=0;

    }
}
