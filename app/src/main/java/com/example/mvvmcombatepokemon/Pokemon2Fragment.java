package com.example.mvvmcombatepokemon;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Pokemon2Fragment extends Fragment {
    Button botonValidar;
    Button guardar;
    EditText hp2;
    EditText nombre2;
    EditText ataque2;
    EditText defensa2;
    EditText atEsp2;
    EditText defEsp2;
    Pokemon pokemon;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        botonValidar = view.findViewById(R.id.botonValidar2);
        guardar = view.findViewById(R.id.botonGuardarPok1);
        botonValidar.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            nombre2 = view.findViewById(R.id.etnombre2);
            int hp = 0;
            hp2 = view.findViewById(R.id.ethp2);
            int ataque = 0;
            ataque2 = view.findViewById(R.id.etataque2);
            int defensa = 0;
            defensa2 = view.findViewById(R.id.etdefensa2);
            int ataqueEsp = 0;
            atEsp2 = view.findViewById(R.id.etatEsp2);
            int defensaEsp = 0;
            defEsp2 = view.findViewById(R.id.etdefEsp2);

            //Nombre del pokemon
            nombre = nombre2.toString();

            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(hp2.toString());
            } catch (NumberFormatException ex) {
                hp2.setError("Hay que introducir un número");
                err = true;
            }

            //Ataque del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataque = Integer.parseInt(ataque2.toString());
            } catch (NumberFormatException ex) {
                ataque2.setError("Hay que introducir un número");
                err = true;
            }
            //Defensa del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensa = Integer.parseInt(defensa2.toString());
            } catch (NumberFormatException ex) {
                defensa2.setError("Hay que introducir un número");
                err = true;
            }
            //Ataque especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataqueEsp = Integer.parseInt(atEsp2.toString());
            } catch (NumberFormatException ex) {
                atEsp2.setError("Hay que introducir un número");
                err = true;
            }

            //Defensa especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensaEsp = Integer.parseInt(defEsp2.toString());
            } catch (NumberFormatException ex) {
                defEsp2.setError("Hay que introducir un número");
                err = true;
            }

            if (!err) {
                pokemonViewModel.crearPokemon(nombre, hp, ataque, defensa, ataqueEsp, defensaEsp);
            }
        });

        pokemonViewModel.pValidado.observe(getViewLifecycleOwner(), validado -> {
            if (validado) {
                guardar.setEnabled(true);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navController.navigate(R.id.action_inicioFragment_to_pokemon1Fragment);
            }
        });
    }
}