package com.grechur.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.grechur.common.Applications;

public class NetworkUtils {
    /**
     * 获取网络连接方式名称
     */
    public static String getNetTypeName() {
        try {
            Context context = Applications.getCurrent();
            if (context != null) {
                ConnectivityManager mConnectivityManager =
                        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                    int type = mNetworkInfo.getType();
                    int subType = mNetworkInfo.getSubtype();
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        return ConstantUtils.NET_TYPE_WIFI;
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        switch (subType) {
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                                return ConstantUtils.NET_TYPE_2G; // ~ 50-100 kbps
                            case TelephonyManager.NETWORK_TYPE_CDMA:
                                return ConstantUtils.NET_TYPE_2G; // ~ 14-64 kbps
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                                return ConstantUtils.NET_TYPE_2G; // ~ 50-100 kbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                return ConstantUtils.NET_TYPE_3G; // ~ 400-1000 kbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                return ConstantUtils.NET_TYPE_3G; // ~ 600-1400 kbps
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                                return ConstantUtils.NET_TYPE_2G; // ~ 100 kbps
                            case TelephonyManager.NETWORK_TYPE_HSDPA:
                                return ConstantUtils.NET_TYPE_2G; // ~ 2-14 Mbps
                            case TelephonyManager.NETWORK_TYPE_HSPA:
                                return ConstantUtils.NET_TYPE_3G; // ~ 700-1700 kbps
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                                return ConstantUtils.NET_TYPE_2G; // ~ 1-23 Mbps
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                return ConstantUtils.NET_TYPE_3G; // ~ 400-7000 kbps
                            case TelephonyManager.NETWORK_TYPE_EHRPD://
                                return ConstantUtils.NET_TYPE_3G; // ~ 1-2 Mbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_B://
                                return ConstantUtils.NET_TYPE_3G; // ~ 5 Mbps
                            case TelephonyManager.NETWORK_TYPE_HSPAP://
                                return ConstantUtils.NET_TYPE_3G; // ~ 10-20 Mbps
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                return ConstantUtils.NET_TYPE_2G; // ~25 kbps
                            case TelephonyManager.NETWORK_TYPE_LTE://
                                return ConstantUtils.NET_TYPE_4G; // ~ 10+ Mbps
                            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                                return ConstantUtils.NET_TYPE_UNKNOW;
                            default:
                                return ConstantUtils.NET_TYPE_2G;
                        }
                    } else {
                        return ConstantUtils.NET_TYPE_NONE;
                    }
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ConstantUtils.NET_TYPE_NONE;
    }
}
