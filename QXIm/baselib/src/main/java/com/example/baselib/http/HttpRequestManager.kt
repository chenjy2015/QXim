package com.example.baselib.http


import android.annotation.SuppressLint
import android.content.Context
import com.example.baselib.gson.GsonIntegerDefaultAdapter
import com.example.baselib.gson.JsonManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory


class HttpRequestManager private constructor() : ISSlHttpRequest {

    private var mOkHttpClient: OkHttpClient? = null

    private var mRetrofit: Retrofit? = null

    private var mContext: Context? = null

    //创建一个不验证证书链的证书信任管理器。 客户端并为对ssl证书的有效性进行校验
    override val sslSocketFactory: SSLSocketFactory
        @Throws(Exception::class)
        get() {
            return SSLSocketFactoryUtils.getSSLSocketFactory()
        }

    private object Singleton {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = HttpRequestManager()
    }

    fun init(context: Context): HttpRequestManager {
        this.mContext = context
        initOKHttp()
        initRetrofit()
        return this
    }

    private fun initOKHttp() {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(6, TimeUnit.SECONDS)
        initIntercept(okHttpClientBuilder)
        mOkHttpClient = okHttpClientBuilder.build()
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "你要传递的json字符串")
    }

    private fun initIntercept(okHttpClientBuilder: OkHttpClient.Builder) {
        try {
            //缓存地址
            val cacheFile = File(mContext!!.externalCacheDir, "HttpCache")
            //大小50Mb
            val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
            //设置缓存方式、时长、地址
            val cacheIntercept = CacheIntercept()
            okHttpClientBuilder.addInterceptor(cacheIntercept)
            okHttpClientBuilder.addNetworkInterceptor(cacheIntercept)
            okHttpClientBuilder.cache(cache)
            //TODO 需要用到ssl时再解开注释
//            okHttpClientBuilder.sslSocketFactory(sslSocketFactory)
//                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)

            //init header
            val headerIntercept = HeaderIntercept()
            okHttpClientBuilder.addInterceptor(headerIntercept)
            /**
             * init logger
             *
             * 注意：如果采用的是okhttp官方的com.squareup.okhttp3:logging-interceptor日志库，
             * 可能会遇到以下两个问题：部分机型无法正常打印网络日志；
             * 当两个api调用时间非常接近是，两个请求的打印日志会混乱，并且当返回数据过多时，日志打印不完整
             */
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            okHttpClientBuilder.addInterceptor(loggingInterceptor)

//        val loggingInterceptor = LoggingInterceptor.Builder()
//            .loggable(BuildConfig.DEBUG)
//            .setLevel(Level.BASIC)
//            .log(Platform.INFO)
//            .request("Request")
//            .response("Response")
//            .build()
//            okHttpClientBuilder.addInterceptor(loggingInterceptor)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun initRetrofit() {
        mRetrofit = Retrofit.Builder()
            .baseUrl(HttpConstant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(JsonManager.create()))
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mOkHttpClient!!)
            .build()
    }

    private fun buildGson(): Gson {
        return GsonBuilder().serializeNulls()
            .registerTypeAdapter(Int::class.javaPrimitiveType, GsonIntegerDefaultAdapter()).create()

    }

    fun <T> create(cls: Class<T>): T {
        return mRetrofit!!.create(cls)
    }

    companion object {
        val instance: HttpRequestManager
            get() = Singleton.INSTANCE
    }

}
