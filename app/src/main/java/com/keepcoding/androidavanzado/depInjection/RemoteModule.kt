package com.keepcoding.androidavanzado.depInjection

import android.util.Log
import com.keepcoding.androidavanzado.data.remote.DragonBallAPI
import com.keepcoding.androidavanzado.ui.login.LoginFragment
import com.keepcoding.androidavanzado.ui.login.LoginFragment.Companion.TAG_PASSWORD
import com.keepcoding.androidavanzado.ui.login.LoginFragment.Companion.TAG_USERNAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{

        val credentials = Credentials.basic(TAG_USERNAME, TAG_PASSWORD)
        if(!LoginFragment.isUserLoggedIn){
            return OkHttpClient.Builder()
                .addInterceptor{ chain ->
                    val originalRequest = chain.request()
                    val newRequest = originalRequest.newBuilder().header("Authorization", credentials).build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(httpLoggingInterceptor)
                .build()
        } else {
            return OkHttpClient.Builder()
                .addInterceptor{ chain ->
                    val originalRequest = chain.request()
                    val newRequest = originalRequest.newBuilder().header("Authorization", "Bearer ${LoginFragment.TOKEN}").build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit) : DragonBallAPI{
        return retrofit.create(DragonBallAPI::class.java)
    }
}