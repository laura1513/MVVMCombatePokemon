package com.example.mvvmcombatepokemon;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Pokemon1Fragment extends Fragment {
    Button botonValidar;
    Button guardar;
    EditText hp1;
    EditText nombre1;
    EditText ataque1;
    EditText defensa1;
    EditText atEsp1;
    EditText defEsp1;
    Pokemon pokemon;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        botonValidar = view.findViewById(R.id.botonValidar1);
        guardar = view.findViewById(R.id.botonGuardarPok1);

        botonValidar.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            nombre1 = view.findViewById(R.id.etnombre1);
            int hp = 0;
            hp1 = view.findViewById(R.id.ethp1);
            int ataque = 0;
            ataque1 = view.findViewById(R.id.etataque1);
            int defensa = 0;
            defensa1 = view.findViewById(R.id.etdefensa1);
            int ataqueEsp = 0;
            atEsp1 = view.findViewById(R.id.etatEsp1);
            int defensaEsp = 0;
            defEsp1 = view.findViewById(R.id.etdefEsp1);

            //Nombre del pokemon
            nombre = nombre1.toString();
            //
            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(hp1.getText().toString());
            } catch (NumberFormatException ex) {
                hp1.setError("Hay que introducir un número");
                err = true;
            }

            //Ataque del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataque = Integer.parseInt(ataque1.toString());
            } catch (NumberFormatException ex) {
                ataque1.setError("Hay que introducir un número");
                err = true;
            }
            //Defensa del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensa = Integer.parseInt(defensa1.toString());
            } catch (NumberFormatException ex) {
                defensa1.setError("Hay que introducir un número");
                err = true;
            }
            //Ataque especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataqueEsp = Integer.parseInt(atEsp1.toString());
            } catch (NumberFormatException ex) {
                atEsp1.setError("Hay que introducir un número");
                err = true;
            }

            //Defensa especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensaEsp = Integer.parseInt(defEsp1.toString());
            } catch (NumberFormatException ex) {
                defEsp1.setError("Hay que introducir un número");
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
                navController.navigate(R.id.action_pokemon1Fragment_to_pokemon2Fragment);
            }
        });
    }
}