package br.com.matreis.simuladordepartidas_dio.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.matreis.simuladordepartidas_dio.databinding.AdapterMatchBinding
import br.com.matreis.simuladordepartidas_dio.domain.Partida
import com.bumptech.glide.Glide

class MatchesAdapter(
) : RecyclerView.Adapter<MatchesAdapter.MyMatchesViewHolder>() {

    private var matches = ArrayList<Partida>()

    var listenerClick : (partida : Partida) -> Unit = {}

    inner class MyMatchesViewHolder(private val binding: AdapterMatchBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(match : Partida){

            binding.apply {
                val visitor = match.visitante
                val home = match.mandante

                if(visitor != null && home != null){
                    tvAwayTeam.text = visitor.nome
                    tvHomeTeam.text = visitor.nome
                    Glide.with(binding.root.context).load(visitor.imagem).into(imgAwayTeam)
                    Glide.with(binding.root.context).load(home.imagem).into(imgHomeTeam)

                    visitor.pontuacao?.let {
                        tvAwayTeamScore.text = it.toString()
                    }

                    home.pontuacao?.let {
                        tvHomeTeamScore.text = it.toString()
                    }
                }

                binding.root.setOnClickListener {
                    listenerClick(match)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchesViewHolder {
        val binding = AdapterMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyMatchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMatchesViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount(): Int = matches.size

    fun getMatches() : List<Partida> = matches

    fun setMatches(matches : ArrayList<Partida>) {
        this.matches = matches
    }

}