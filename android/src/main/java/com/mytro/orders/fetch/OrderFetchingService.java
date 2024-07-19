package com.mytro.orders.fetch;

import android.util.Log;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.plugin.util.JsonUtils;

@CapacitorPlugin(name = "OrderFetchingService")
public class OrderFetchingService extends Plugin {

  private static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 1;

    @Override
    public void load() {
        super.load();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    REQUEST_CODE_NOTIFICATION_PERMISSION
                );
            } else {
                startOrderFetchingService();
            }
        } else {
            startOrderFetchingService();
        }
    }

    @Override
    public void handleOnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleOnRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startOrderFetchingService();
            }
        }
    }

    private void startOrderFetchingService() {
        if (!foregroundServiceRunning()) {
            Intent serviceIntent = new Intent(getContext(), OrderFetchingService.class);
            ContextCompat.startForegroundService(getContext(), serviceIntent);
        }
    }

    private boolean foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (OrderFetchingService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
