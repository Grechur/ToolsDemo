package com.grechur.net;

import android.content.Context;
import android.text.TextUtils;


import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by han on 2017/5/26.
 */

public class HttpService {
    private final List<Interceptor> interceptors;
    private final List<Interceptor> networkInterceptors;
    private final int connectTimeout;
    private final int readTimeout;
    private final int writeTimeout;
    private final Retrofit mRetrofit;
    private final OkHttpClient mOkHttpClient;
    private final String baseUrl;
    Converter.Factory converterFactory;
    CallAdapter.Factory callAdapterFactory;

    public HttpService() {
        this(new Builder());
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        HttpService.context = context;
    }

    HttpService(Builder builder) {
        this.interceptors = Util.immutableList(builder.interceptors);
        this.networkInterceptors = Util.immutableList(builder.networkInterceptors);
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.baseUrl = builder.baseUrl;
        this.converterFactory = builder.converterFactory;
        this.callAdapterFactory = builder.callAdapterFactory;
        if (builder.okHttpClientBuilder.interceptors() != null) {
            builder.okHttpClientBuilder.interceptors().clear();
        }
        for (Interceptor interceptor : interceptors) {
            builder.okHttpClientBuilder.addInterceptor(interceptor);
        }
        for (Interceptor interceptor : networkInterceptors) {
            builder.okHttpClientBuilder.addNetworkInterceptor(interceptor);
        }
        builder.okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        builder.okHttpClientBuilder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        builder.okHttpClientBuilder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        mOkHttpClient = builder.okHttpClientBuilder.build();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    /**
     * Default connect timeout (in milliseconds).
     */
    public int connectTimeoutMillis() {
        return connectTimeout;
    }

    /**
     * Default read timeout (in milliseconds).
     */
    public int readTimeoutMillis() {
        return readTimeout;
    }

    /**
     * Default write timeout (in milliseconds).
     */
    public int writeTimeoutMillis() {
        return writeTimeout;
    }

    public OkHttpClient okHttpClient() {
        return mOkHttpClient;
    }

    public <T> T createRetrofitService(final Class<T> service) {
        return mRetrofit.create(service);
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private final static String DEFAULT_URL = "";
        private final List<Interceptor> interceptors = new ArrayList<>();
        private final List<Interceptor> networkInterceptors = new ArrayList<>();
        private OkHttpClient.Builder okHttpClientBuilder;
        Converter.Factory converterFactory;
        CallAdapter.Factory callAdapterFactory;
        private String baseUrl;
        private int connectTimeout;
        private int readTimeout;
        private int writeTimeout;

        public Builder() {
            connectTimeout = 20_000;
            readTimeout = 10_000;
            writeTimeout = 10_000;
            okHttpClientBuilder = defaultOkHttpClientBuilder();
            baseUrl = DEFAULT_URL;
            converterFactory = GsonConverterFactory.create();
            callAdapterFactory = RxJava2CallAdapterFactory.create();
        }

        Builder(HttpService service) {
            this.interceptors.addAll(service.interceptors);
            this.networkInterceptors.addAll(service.networkInterceptors);
            this.connectTimeout = service.connectTimeout;
            this.readTimeout = service.readTimeout;
            this.writeTimeout = service.writeTimeout;
            this.okHttpClientBuilder = service.mOkHttpClient.newBuilder();
            this.converterFactory = service.converterFactory;
            this.callAdapterFactory = service.callAdapterFactory;
            this.baseUrl = service.baseUrl;
        }

        private OkHttpClient.Builder defaultOkHttpClientBuilder() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logging);
            }
            X509TrustManager trustManager = defaultX509TrustManager();
            builder.sslSocketFactory(defaultSSLSocketFactory(trustManager), trustManager);
            return builder;
        }

        private X509TrustManager defaultX509TrustManager() {
            try {
                TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                factory.init((KeyStore) null);
                TrustManager[] trustManagers = factory.getTrustManagers();
                return (X509TrustManager) trustManagers[0];
            } catch (NoSuchAlgorithmException | KeyStoreException exception) {
//            Log.e(getClass().getSimpleName(), "not trust manager available", exception);
            }

            return null;
        }


        private SSLSocketFactory defaultSSLSocketFactory(X509TrustManager trustManager) {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                return sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException exception) {
//            Log.e(getClass().getSimpleName(), "not tls ssl socket factory available", exception);
            }

            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        }

        public List<Interceptor> interceptors() {
            return interceptors;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        /**
         * Returns a modifiable list of interceptors that observe a single network request and response.
         * These interceptors must call {@link Interceptor.Chain#proceed} exactly once: it is an error
         * for a network interceptor to short-circuit or repeat a network request.
         */
        public List<Interceptor> networkInterceptors() {
            return networkInterceptors;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            networkInterceptors.add(interceptor);
            return this;
        }

        public Builder connectTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE)
                throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0)
                throw new IllegalArgumentException("Timeout too small.");
            connectTimeout = (int) millis;
            return this;
        }

        /**
         * Sets the default read timeout for new connections. A value of 0 means no timeout, otherwise
         * values must be between 1 and {@link Integer#MAX_VALUE} when converted to milliseconds.
         */
        public Builder readTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE)
                throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0)
                throw new IllegalArgumentException("Timeout too small.");
            readTimeout = (int) millis;
            return this;
        }

        /**
         * Sets the default write timeout for new connections. A value of 0 means no timeout, otherwise
         * values must be between 1 and {@link Integer#MAX_VALUE} when converted to milliseconds.
         */
        public Builder writeTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE)
                throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0)
                throw new IllegalArgumentException("Timeout too small.");
            writeTimeout = (int) millis;
            return this;
        }

        public Builder sslSocketFactory(
                SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
            if (sslSocketFactory != null && trustManager != null) {
                this.okHttpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager);
            }
            return this;
        }


        public Builder sslSocketFactory(
                SSLSocketFactory sslSocketFactory) {
            if (sslSocketFactory != null) {
                this.okHttpClientBuilder.sslSocketFactory(sslSocketFactory);
            }
            return this;
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            if (okHttpClient != null) {
                this.okHttpClientBuilder = okHttpClient.newBuilder();
            }
            return this;
        }

        public Builder baseUrl(String url) {
            if (!TextUtils.isEmpty(url)) {
                this.baseUrl = url;
            }
            return this;
        }

        public Builder cache(Cache cache) {
            if (cache != null) {
                okHttpClientBuilder.cache(cache);
            }
            return this;
        }

        public Builder dns(Dns dns) {
            if (dns != null) {
                okHttpClientBuilder.dns(dns);
            }
            return this;
        }

        /**
         * Add converter factory for serialization and deserialization of objects.
         */
        public Builder addConverterFactory(Converter.Factory factory) {
            if (factory != null) {
                converterFactory = factory;
            }
            return this;
        }

        /**
         * Add a call adapter factory for supporting service method return types other than {@link
         * Call}.
         */
        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            if (factory != null) {
                callAdapterFactory = factory;
            }
            return this;
        }

        public HttpService build() {
            return new HttpService(this);
        }

        public void hostVeritifer(HostnameVerifier hostVeritifer) {
            if (hostVeritifer != null) {
                this.okHttpClientBuilder.hostnameVerifier(hostVeritifer);
            }
        }
    }
}
