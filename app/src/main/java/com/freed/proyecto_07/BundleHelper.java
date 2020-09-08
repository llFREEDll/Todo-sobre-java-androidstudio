package com.freed.proyecto_07;

import android.os.Bundle;

public class BundleHelper {
    private Bundle bundle;
    BundleHelper(){
        bundle = new Bundle();
    }
    public Bundle getBundle(){
        return bundle;
    }
    public void setBundle(){
        bundle = new Bundle();
    }
    public void writeBundle(String key,Object data, Bundle bundle){
        if (data instanceof String)
            bundle.putString(key,(String) data);
        else if (data instanceof Integer)
            bundle.putInt(key,(int) data);
    }
    public String readBundleString(String key, Bundle bundle){
        return bundle.getString(key);
    }
    public int readBundleInt(String key, Bundle bundle){
        return bundle.getInt(key);
    }
}
