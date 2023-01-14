package com.keepcoding.androidavanzado.depInjection

import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.RepositoryImpl
import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.local.LocalDataSourceImpl
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl) : Repository
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource
}