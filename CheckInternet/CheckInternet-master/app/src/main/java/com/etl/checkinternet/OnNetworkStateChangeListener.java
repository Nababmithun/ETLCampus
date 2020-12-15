package com.etl.checkinternet;

public interface OnNetworkStateChangeListener {
    void onChange(boolean isConnected);
}
