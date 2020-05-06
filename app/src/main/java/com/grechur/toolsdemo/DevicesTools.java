package com.grechur.toolsdemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.Context.WIFI_SERVICE;


/**
 * @ProjectName: ToolsDemo
 * @Package: com.grechur.toolsdemo
 * @ClassName: DevicesTools
 * @Description: 手机信息获取工具类
 * @Author: Grechur
 * @CreateDate: 2020/3/11 10:25
 * @UpdateUser: Grechur
 * @UpdateDate: 2020/3/11 10:25
 */
public class DevicesTools {

    /**
     * 开机时间
     * @return
     */
    public static String bootTime() {
        long time = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        return TimeTools.convertTime(time);
    }

    /**
     * 手机开机到现在时间
     */
    public static String uptimeMillis() {
        long time = SystemClock.uptimeMillis();
        return TimeTools.stringToDay(time);
    }

    /**
     * 系统激活时间，最近的一次系统更新时间
     */
    public static String systemActive() {
        long time = Build.TIME;
        return TimeTools.convertTime(time);
    }

    /**
     *时区
     */
    public static String timeZone() {
        return TimeZone.getDefault().getDisplayName();
    }

    /**
     * 语言
     */
    public static String languages(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale.getDisplayLanguage();
    }

    /**
     * 屏幕亮度
     */
    public static String brightness(Context context) {
        String systemBright = Settings.System.getString(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        return systemBright;
    }

    /**
     * 充电状态
     */
    public static String batteryStatus(Context context) {
        boolean charging = false;
        BatteryManager manager = (BatteryManager) context.getSystemService(context.BATTERY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            charging = manager.isCharging();
        } else {
            charging = isCharging(context);
        }
        if (charging) {
            return "充电中";
        } else {
            return "未充电";
        }
    }

    public static boolean isCharging(Context context) {
        Intent batteryBroadcast = context.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        // 0 means we are discharging, anything else means charging
        boolean isCharging = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) != 0;
        return isCharging;
    }

    /**
     * 电池温度
     */
    public static String batteryTemp(Context context) {
        Intent batteryBroadcast = context.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        float temp = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;

        return String.valueOf(temp) + "℃";
    }

    /**
     * 电池电量
     */
    public static String batteryPercentage(Context context) {
        Intent batteryBroadcast = context.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        float per = level * 100 / scale;
        return String.valueOf(per) + "%";
    }


    /**
     * cpu型号
     */
    public static String cpuType() {
        String[] strings;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            strings = Build.SUPPORTED_ABIS;
        } else {
            strings = getCpuInfo();
        }
        if (strings != null && strings.length > 0) {
            for (String string : strings) {
                Log.e("cpuType", string);
            }

            return strings[0];
        }
        return null;
    }

    public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return cpuInfo;
    }

    /**
     * BASEBAND-VER
     * 基带版本
     * return String
     */
    public static String basebandVersion() {
        String version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.getDeclaredConstructor().newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            version = (String) result;
        } catch (Exception e) {
            version=e.getMessage();
        }
        return version;
    }


    /**
     * 内核版本
     * return String
     */
    public static String getLinuxCore() {
        String result = System.getProperty("os.version");
        return result;
    }

    /**
     * 设备名称
     */
    public static String modelName() {
        String result = Build.MODEL;
        return result;
    }

    /**
     * 厂商名称
     */
    public static String manufacturerName() {
        String result = Build.MANUFACTURER;
        return result;
    }

    /**
     * 系统版本
     */
    public static String systemtVersion() {
        String result = Build.VERSION.RELEASE;
        return result;
    }

    /**
     * 主板信息
     */
    public static String board() {
        String result = Build.BOARD;
        return result;
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkEnable(Context context) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }

    /**
     * 无线网络名称
     */
    public static String getSsid(Context context) {
        if (checkEnable(context)) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O || Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                assert mWifiManager != null;
                WifiInfo info = mWifiManager.getConnectionInfo();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    return info.getSSID();
                } else {
                    return info.getSSID().replace("\"", "");
                }
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {

                ConnectivityManager connManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                assert connManager != null;
                NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
                if (networkInfo.isConnected()) {
                    if (networkInfo.getExtraInfo() != null) {
                        return networkInfo.getExtraInfo().replace("\"", "");
                    }
                }
            }
        }
        return null;
    }

    /**
     * 无线bssid
     */
    public static String getBSsid(Context context) {
        if (checkEnable(context)) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                return wifiInfo.getBSSID();
            }
        }
        return null;
    }

    /**
     * ip地址
     */
    public static String carrierIpAddress(Context context) {
        if (checkEnable(context)) {
            String ip = null;
            ConnectivityManager conMann = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mobileNetworkInfo.isConnected()) {
                ip = getLocalIpAddress();
            } else if (wifiNetworkInfo.isConnected()) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                ip = int2ip(ipAddress);
            }
            return ip;
        }
        return null;
    }

    public static String getLocalIpAddress() {
        try {
            String ipv4;
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if(networkInterfaces!=null) {
                ArrayList<NetworkInterface> nilist = Collections.list(networkInterfaces);
                for (NetworkInterface ni : nilist) {
                    ArrayList<InetAddress> ialist = Collections.list(ni.getInetAddresses());
                    for (InetAddress address : ialist) {
                        if (!address.isLoopbackAddress()) {
                            ipv4 = address.getHostAddress();
                            return ipv4;
                        }
                    }

                }
            }

        } catch (SocketException ex) {
            Log.e("localip", ex.toString());
        }
        return null;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 搜索到的周边WIFI信号信息
     * @param mContext
     * @return
     */
    public static String wifiList(Context mContext) {
        StringBuffer sInfo = new StringBuffer();
        WifiManager mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = mWifiManager.getScanResults();//搜索到的设备列表
        for (ScanResult scanResult : scanResults) {
            sInfo.append("\n设备名：" + scanResult.SSID);//+" 信号强度："+scanResult.level+"/n :"+mWifiManager.calculateSignalLevel(scanResult.level,4)
        }
        return sInfo.toString();
    }

    /**
     * 临近基站信息
     */
    public static String nearbyBaseStat(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
        }
        List<NeighboringCellInfo> infos;
        List<CellInfo> cel = null;
        StringBuffer sb = new StringBuffer("总数 : ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cel = telephonyManager.getAllCellInfo();
            sb.append(cel.size() + "\n");
            for (CellInfo info : cel) {
                if(info instanceof CellInfoGsm){
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) info;
                    CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
                    sb.append("CellInfoGsm");
                    sb.append(" LAC : " + cellIdentityGsm.getLac());
                    sb.append(" CID : " + cellIdentityGsm.getCid());
                    sb.append("\n");
                }else if(info instanceof CellInfoCdma){
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) info;
                    CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                    sb.append("CellInfoCdma");
                    sb.append(" LAC : " + cellIdentityCdma.getNetworkId());
                    sb.append(" CID : " + cellIdentityCdma.getBasestationId());
                    sb.append("\n");
                }else if(info instanceof CellInfoLte){
                    CellInfoLte cellInfoLte  = (CellInfoLte) info;
                    CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                    sb.append("CellInfoLte");
                    sb.append(" LAC : " + cellIdentityLte.getTac());
                    sb.append(" CID : " + cellIdentityLte.getCi());
                    sb.append("\n");
                }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    if(info instanceof CellInfoWcdma){
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) info;
                        CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                        sb.append("CellInfoWcdma");
                        sb.append(" LAC : " + cellIdentityWcdma.getLac());
                        sb.append(" CID : " + cellIdentityWcdma.getCid());
                        sb.append("\n");
                    }
                }

            }
        } else {
            infos = telephonyManager.getNeighboringCellInfo();
            sb.append(infos.size() + "\n");
            for (NeighboringCellInfo info1 : infos) { // 根据邻区总数进行循环
                sb.append(" LAC : " + info1.getLac()); // 取出当前邻区的LAC
                sb.append(" CID : " + info1.getCid()); // 取出当前邻区的CID
                sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // 获取邻区基站信号强度
            }
        }


        return sb.toString();
    }

    /**
     * 获取本机号码
     */
    public static String phoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            }
        }
        String tel = telephonyManager.getLine1Number();
        return tel;
    }

    /**
     * 判断sdcard状态
     */
    public static boolean hasSDCard() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //sdcard状态是没有挂载的情况
            return false;
        } else {
            return true;
        }
    }

    /**
     * sd卡存储大小
     */
    public static String totalDiskSize() {
        if (hasSDCard()) {
            File sdcard_filedir = Environment.getExternalStorageDirectory();
            return sdcard_filedir.getTotalSpace() + "byte";
        }
        return null;
    }

    /**
     * sd卡可用空间大小
     */
    public static String availableDiskSize() {
        if (hasSDCard()) {
            File sdcard_filedir = Environment.getExternalStorageDirectory();
            return sdcard_filedir.getUsableSpace() + "byte";
        }
        return null;
    }

    /**
     * 注册的运营商名字
     */
    public static String carrierName(Context context) {
        String ProvidersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            }
        }
        String IMSI = telephonyManager.getSubscriberId();
        Log.i("qweqwes", "运营商代码" + IMSI);
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001")  || IMSI.startsWith("46006")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            }
            return ProvidersName;
        } else {
            return "没有获取到sim卡信息";
        }

    }

    /**
     * 是否root
     * @return
     */
    public static boolean isRooted() {
        Process process = null;
        try {
            //   /system/xbin/which 或者  /system/bin/which
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }

    /**
     * 设备boot后经历的时间
     */
    public static String elapsedRealtime(){
        long time = SystemClock.elapsedRealtime();
        return TimeTools.stringToDay(time);
    }

    /**
     * 是否设置代理
     */
    public static boolean  isUsingProxyPort(Context context){
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;

        String proxyAddress;

        int proxyPort;

        if (IS_ICS_OR_LATER) {

            proxyAddress = System.getProperty("http.proxyHost");

            String portStr = System.getProperty("http.proxyPort");

            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));

        } else {

            proxyAddress = android.net.Proxy.getHost(context);

            proxyPort = android.net.Proxy.getPort(context);

        }

        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    /**
     * 是否模拟器
     */
    public static boolean isSimulatorNew(Context context){
        String url = "tel:123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction("android.intent.action.DIAL");
        boolean canResolveIntent = intent.resolveActivity(context.getPackageManager()) != null;
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox")
                || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator") || Build.SERIAL.equalsIgnoreCase("unknown")
                || Build.SERIAL.equalsIgnoreCase("android") || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || "google_sdk".equals(Build.PRODUCT) ||
                ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName().toLowerCase().equals("android")
                || !canResolveIntent;
    }

    /**
     * 存储的图片数量
     */
    public static int picNum(Context context){
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if(cursor!=null){
            return cursor.getCount();
        }
        return 0;
    }

    public static String getRecords(ContentResolver contentResolver) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            String records = null;
            StringBuilder recordBuilder = new StringBuilder();
            Cursor cursor = contentResolver.query(
                    Uri.parse("content://browser/bookmarks"), new String[]{
                            "title", "url", "date"}, "date!=?",
                    new String[]{"null"}, "date desc");
            while (cursor != null && cursor.moveToNext()) {
                String url = null;
                String title = null;
                String time = null;
                String date = null;

                title = cursor.getString(cursor.getColumnIndex("title"));
                url = cursor.getString(cursor.getColumnIndex("url"));

                date = cursor.getString(cursor.getColumnIndex("date"));

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm;ss");
                Date d = new Date(Long.parseLong(date));
                time = dateFormat.format(d);
                recordBuilder.append("title:" + title + " url:" + url);
            }
            return recordBuilder.toString();
        }
        return null;
    }
}
