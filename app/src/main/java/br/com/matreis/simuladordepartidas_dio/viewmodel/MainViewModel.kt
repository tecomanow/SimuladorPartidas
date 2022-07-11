package br.com.matreis.simuladordepartidas_dio.viewmodel

import android.util.Log
import androidx.lifecycle.*
import br.com.matreis.simuladordepartidas_dio.data.repository.PartidasRepository

import br.com.matreis.simuladordepartidas_dio.domain.Partida
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: PartidasRepository
) : ViewModel() {

    init {
        getPartidas()
    }

    private val paridasData = MutableLiveData<List<Partida>>()

    val partidasObserve : LiveData<List<Partida>>
        get() = paridasData

    fun getPartidas(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.getPartidas().let {
                if(it.isSuccessful){
                    paridasData.postValue(it.body())
                }else{
                    Log.d("TESTE", "FAIL, ${it.message()}")
                }
            }
        }
    }

    class MainViewModelFactory(
        private val repository: PartidasRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(MainViewModel::class.java)){

                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T

            }

            return super.create(modelClass)
        }
    }



}