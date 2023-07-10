package com.akash.sportup.data.datasources.networksource

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Request
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkInterceptor(_context : Context) : Interceptor  {
    private val context: Context = _context

    override fun intercept(chain: Interceptor.Chain) : Response {
        if (!isConnected()) {
            throw  IOException("NoConnectivityException")
        }
        val builder : Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    fun isConnected() : Boolean {
        val connectivityManager : ConnectivityManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo : NetworkInfo? = connectivityManager.getActiveNetworkInfo()
        return (netInfo != null && netInfo.isConnected())
    }
}


