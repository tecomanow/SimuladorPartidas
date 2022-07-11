package br.com.matreis.simuladordepartidas_dio.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import br.com.matreis.simuladordepartidas_dio.R;
import br.com.matreis.simuladordepartidas_dio.databinding.ActivityDetailBinding;
import br.com.matreis.simuladordepartidas_dio.domain.Local;
import br.com.matreis.simuladordepartidas_dio.domain.Partida;
import br.com.matreis.simuladordepartidas_dio.domain.Time;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private Partida match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        match = getIntent().getParcelableExtra("match");

        if(match != null){
            setUpViews();
        }
    }

    private void setUpViews() {
        Local place = match.getLocal();
        Time visitante = match.getVisitante();
        Time mandante = match.getMandante();

        if(place != null && visitante != null && mandante != null){
            Glide.with(this).load(place.getImagem()).into(binding.ivPlace);
            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle(place.getNome());
            }

            binding.tvDescription.setText(match.getDescricao());

            Glide.with(this).load(mandante.getImagem()).into(binding.ivHomeTeam);
            binding.tvHomeTeamName.setText(mandante.getNome());
            binding.rbHomeTeamStars.setRating(Float.valueOf(mandante.getForca()));
            if(mandante.getPontuacao() != null){
                binding.tvHomeTeamScore.setText(String.valueOf(mandante.getPontuacao()));
            }

            Glide.with(this).load(visitante.getImagem()).into(binding.ivAwayTeam);
            binding.tvAwayTeamName.setText(visitante.getNome());
            binding.rbAwayTeamStars.setRating(Float.valueOf(visitante.getForca()));
            if(visitante.getPontuacao() != null){
                binding.tvAwayTeamScore.setText(String.valueOf(visitante.getPontuacao()));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}