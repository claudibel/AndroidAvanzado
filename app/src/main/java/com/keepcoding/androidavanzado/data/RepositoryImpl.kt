package com.keepcoding.androidavanzado.data

import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.mappers.LocalToPresentationMapper
import com.keepcoding.androidavanzado.data.mappers.RemoteToLocalMapper
import com.keepcoding.androidavanzado.data.mappers.RemoteToPresentationMapper
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.data.remote.response.SuperHeroLocation
import com.keepcoding.androidavanzado.domain.SuperHero
import com.keepcoding.androidavanzado.ui.detail.DetailState
import com.keepcoding.androidavanzado.ui.superherolist.SuperHeroListState
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(

    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToPresentationMapper: RemoteToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper

): Repository {

    override suspend fun getHeros(): List<SuperHero> {
        return remoteToPresentationMapper.map(remoteDataSource.getHeros())
    }

    override suspend fun getHeroLocations(heroId: String): List<SuperHeroLocation> {
        return remoteDataSource.getHeroLocations(heroId)
    }

    override suspend fun getHeroDetail(name: String): DetailState {
        val result = remoteDataSource.getHeroDetail(name)
        return when{
            result.isSuccess ->{
                result.getOrNull()?.let{
                    DetailState.Success(remoteToPresentationMapper.map(it))
                }?: DetailState.Failure(result.exceptionOrNull()?.message)
            }
            else -> {
                when(val exception = result.exceptionOrNull()){
                    is HttpException -> DetailState.NetworkFailure(exception.code())
                    else -> DetailState.Failure(result.exceptionOrNull()?.message)
                }
            }
        }
    }

    override suspend fun getHerosWithCache(): List<SuperHero> {
        val localSuperHeros = localDataSource.getHeros()
        if(localSuperHeros.isEmpty()){
            val remoteSuperHeros = remoteDataSource.getHeros()
            localDataSource.insertHeros(remoteToLocalMapper.map(remoteSuperHeros))
        }
        println("Database-->"+localDataSource.getHeros().toString())
        return localToPresentationMapper.map(localDataSource.getHeros())
    }

    override suspend fun getHerosWithException(): SuperHeroListState {
        val result = remoteDataSource.getHerosWithException()
        return when{
            result.isSuccess ->{
                result.getOrNull()?.let{
                    SuperHeroListState.Success(remoteToPresentationMapper.map(it))
                }?: SuperHeroListState.Failure(result.exceptionOrNull()?.message)
            }
            else -> {
                when(val exception = result.exceptionOrNull()){
                    is HttpException -> SuperHeroListState.NetworkFailure(exception.code())
                    else -> SuperHeroListState.Failure(result.exceptionOrNull()?.message)
                }
            }
        }
    }

    override suspend fun login(): String {
        return remoteDataSource.login()
    }

    override suspend fun favorite(heroId: String){
        remoteDataSource.favorite(heroId)
        //Volver a hacer la llamada al remoto
        val remoteSuperHeros = remoteDataSource.getHeros()
        println("Despues de favorite: $remoteSuperHeros")
        localDataSource.insertHeros(remoteToLocalMapper.map(remoteSuperHeros))
        val localSuperHeros = localDataSource.getHeros()
        println("Local Despues de favorite: $localSuperHeros")
        val favoriteList = localDataSource.getFavoriteSuperheros(true)
        println("Listado de favoritos: $favoriteList")
    }

    override suspend fun getFavoritesList(): List<SuperHero>{
        val favoriteList = localDataSource.getFavoriteSuperheros(true)
        return localToPresentationMapper.map(favoriteList)
    }

    override suspend fun isFavorite(heroId: String) : Boolean{
        val favoriteList = localDataSource.getFavoriteSuperheros(true)
        var isFavorite: Boolean = false
        for(hero in favoriteList){
           if(hero.id == heroId){
               isFavorite = true
               break
           }
        }
        return isFavorite
    }

}