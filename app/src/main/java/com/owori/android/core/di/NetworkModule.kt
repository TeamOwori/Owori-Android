package com.owori.android.core.di

import com.owori.android.R
import com.owori.android.core.OworiApplication
import com.owori.android.data.api.auth.AuthApi
import com.owori.android.data.api.comment.CommentApi
import com.owori.android.data.api.family.FamilyApi
import com.owori.android.data.api.heart.HeartApi
import com.owori.android.data.api.image.ImageApi
import com.owori.android.data.api.keyword.KeywordApi
import com.owori.android.data.api.member.MemberApi
import com.owori.android.data.api.saying.SayingApi
import com.owori.android.module.HttpRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/*
* Created by JJJoonngg
*/

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val NETWORK_EXCEPTION_OFFLINE_CASE = "network status is offline"
    const val NETWORK_EXCEPTION_BODY_IS_NULL = "result body is null"

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .retryOnConnectionFailure(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(OworiApplication.getString(R.string.base_url))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideCommentApi(retrofit: Retrofit): CommentApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideFamilyApi(retrofit: Retrofit): FamilyApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideHeartApi(retrofit: Retrofit): HeartApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideKeywordApi(retrofit: Retrofit): KeywordApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideSayingApi(retrofit: Retrofit): SayingApi {
        return retrofit.buildService()
    }

    @Provides
    @Singleton
    fun provideMemberApi(retrofit: Retrofit): MemberApi {
        return retrofit.buildService()
    }

    private inline fun <reified T> Retrofit.buildService(): T {
        return this.create(T::class.java)
    }

//    class AppInterceptor : HttpRequestInterceptor() {
//
//    }
}