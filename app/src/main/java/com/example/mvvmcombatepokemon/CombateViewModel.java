package com.example.mvvmcombatepokemon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CombateViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> turno = new MutableLiveData<>(true);
    public MutableLiveData<Integer> vidaP1;
    public MutableLiveData<Integer> vidaP2;
    public MutableLiveData<Integer> ganador = new MutableLiveData<>();
    private Executor executor;
    private SimuladorCombate simulador;
    public Pokemon pokemon1;
    public Pokemon pokemon2;


    public CombateViewModel(@NonNull Application application) {
        super(application);
        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorCombate();
        pokemon1 = PokemonViewModel.listaPokemon.get(0);
        pokemon2 = PokemonViewModel.listaPokemon.get(1);
        vidaP1 = new MutableLiveData<>(pokemon1.getHp());
        vidaP2 = new MutableLiveData<>(pokemon2.getHp());

    }

    public void combate(int hp, int atk, int def) {
        if (turno.getValue()){
            executor.execute(() -> simulador.atacar(hp, atk, def, new SimuladorCombate.Callback() {
                @Override
                public void cambiarTurno() {
                    turno.postValue(false);
                    if (vidaP2.getValue() <= 0) {
                        ganador.postValue(1);
                    }
                }

                @Override
                public void cuandoPokemonAtaca(int vidaRestante) {
                    vidaP2.postValue(vidaRestante);
                }
            }));
        } else {
            executor.execute(() -> simulador.atacar(hp, atk, def, new SimuladorCombate.Callback() {
                @Override
                public void cambiarTurno() {
                    turno.postValue(true);
                    if (vidaP1.getValue() <= 0) {
                        ganador.postValue(2);
                    }
                }

                @Override
                public void cuandoPokemonAtaca(int vidaRestante) {
                    vidaP1.postValue(vidaRestante);
                }
            }));
        }

    }
}