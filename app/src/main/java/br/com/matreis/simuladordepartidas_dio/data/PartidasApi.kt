package br.com.matreis.simuladordepartidas_dio.data

import br.com.matreis.simuladordepartidas_dio.domain.Partida
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PartidasApi {

    @GET("matches.json")
    suspend fun getPartidas(): Response<List<Partida>>

}