package com.grechur.common.util;

import android.os.Build;
import android.text.TextUtils;

public class DevicesUtils {
    public static String getSupportedAbis() {
        String[] supportedAbis;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            supportedAbis = Build.SUPPORTED_ABIS;
        else {
            String abi1 = Build.CPU_ABI;
            String abi2 = Build.CPU_ABI2;
            if (TextUtils.isEmpty(abi1))
                return "";
            if (TextUtils.isEmpty(abi2))
                supportedAbis = new String[]{abi1};
            else
                supportedAbis = new String[]{abi1, abi2};
        }
        StringBuilder supportedAbisSb = null;
        if (null != supportedAbis && supportedAbis.length > 0) {
            for (String abis : supportedAbis) {
                if (null == supportedAbisSb)
                    supportedAbisSb = new StringBuilder(abis);
                else
                    supportedAbisSb.append(",").append(abis);
            }
        }
        return null != supportedAbisSb ? supportedAbisSb.toString() : "";
    }
}
