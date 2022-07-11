package br.com.matreis.simuladordepartidas_dio.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.matreis.simuladordepartidas_dio.data.RetrofitInstance
import br.com.matreis.simuladordepartidas_dio.data.repository.PartidasRepository
import br.com.matreis.simuladordepartidas_dio.databinding.ActivityMainBinding
import br.com.matreis.simuladordepartidas_dio.domain.Partida
import br.com.matreis.simuladordepartidas_dio.ui.adapter.MatchesAdapter
import br.com.matreis.simuladordepartidas_dio.viewmodel.MainViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private val matchesAdapter by lazy { MatchesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(
            PartidasRepository(RetrofitInstance())
        ))[MainViewModel::class.java]

        setUpRecyclerView()
        setUpObservers()
        setUpListeners()
    }

    fun setUpListeners(){
        binding.btnSimulate.setOnClickListener { v ->
            v.animate().rotationBy(360F).setDuration(500).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    simlateMatch()
                }
            })
        }
    }

    private fun simlateMatch() {
        val random = Random()
        matchesAdapter.getMatches().forEach { partida ->
            partida.visitante?.pontuacao = random.nextInt(partida.visitante?.forca!! + 1)
            partida.mandante?.pontuacao = random.nextInt(partida.mandante?.forca!! + 1)
        }
        matchesAdapter.notifyDataSetChanged()
    }

    private fun setUpObservers() {
        mainViewModel.partidasObserve.observe(this) { matches ->
            configureMatchesList(matches)
        }
    }

    private fun configureMatchesList(matches: List<Partida>?) {
        matches?.let {
            matchesAdapter.setMatches(it as ArrayList<Partida>)
            matchesAdapter.notifyDataSetChanged()
        }
    }

    fun setUpRecyclerView(){
        binding.apply {
            rvMatches.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            rvMatches.hasFixedSize()
            rvMatches.adapter = matchesAdapter

            matchesAdapter.listenerClick = {
                val i = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra("match", it)
                startActivity(i)
            }
        }
    }
}