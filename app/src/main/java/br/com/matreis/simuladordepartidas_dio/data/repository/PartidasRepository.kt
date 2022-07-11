package br.com.matreis.simuladordepartidas_dio.data.repository

import br.com.matreis.simuladordepartidas_dio.data.RetrofitInstance
import br.com.matreis.simuladordepartidas_dio.domain.Partida
import retrofit2.Call
import retrofit2.Response

class PartidasRepository(private val retrofitInstance: RetrofitInstance) {


    suspend fun getPartidas() : Response<List<Partida>> {
        return retrofitInstance.partidasApi.getPartidas()
    }

}