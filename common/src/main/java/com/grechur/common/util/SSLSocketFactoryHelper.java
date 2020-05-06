package com.grechur.common.util;

import android.content.Context;


import com.grechur.common.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLSocketFactoryHelper {

    public static final String CER_NAME = "wanandroid";
    public static final String VERITY_HOST_NAME = "wanandroid";
    public static SSLSocketFactory getSSLSocketFactory(Context context, String  filePath) {
        if(!BuildConfig.SSL_SWITCH_STATUS) {
            //ssl 认证状态关闭
            return null;
        }

        try {
            InputStream inputStream = context.getAssets().open(filePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(inputStream));
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HostnameVerifier getHostVeritifer() {
        return new CustomHostNameVerifier();
    }
    public static class  CustomHostNameVerifier implements  HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            if(!BuildConfig.SSL_SWITCH_STATUS) {
                //ssl认证开关关闭，不接受ssl认证校验
                return true;
            }
            return true;
        }
    }

}
