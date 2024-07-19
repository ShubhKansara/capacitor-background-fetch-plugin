package com.mytro.orders.fetch;

import android.util.Log;

public class OrderFetchingService {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
