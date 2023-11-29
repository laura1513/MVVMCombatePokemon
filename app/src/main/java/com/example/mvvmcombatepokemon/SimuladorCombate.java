package com.example.mvvmcombatepokemon;

public class SimuladorCombate {


    interface Callback {

        void cambiarTurno();
        void cuandoPokemonAtaca(int vidaRestante);
    }


    public void atacar(int hp, int atk, int def, Callback callback) {

        callback.cuandoPokemonAtaca(hp -((atk <= def) ? 1 : atk - def));

        callback.cambiarTurno();

    }
}
