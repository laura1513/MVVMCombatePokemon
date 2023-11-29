package com.example.mvvmcombatepokemon;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmcombatepokemon.databinding.FragmentPokemon1Binding;
import com.example.mvvmcombatepokemon.databinding.FragmentPokemon2Binding;

public class Pokemon2Fragment extends Fragment {
    private @NonNull FragmentPokemon2Binding binding;
    private Pokemon pokemon = null;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentPokemon2Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        navController = Navigation.findNavController(view);

        binding.botonValidar2.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            int hp = 0;
            int ataque = 0;
            int defensa = 0;
            int ataqueEsp = 0;
            int defensaEsp = 0;

            //Nombre del pokemon
            nombre = binding.etnombre2.getText().toString();
            //
            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(binding.ethp2.getText().toString());
            } catch (NumberFormatException ex) {
                binding.ethp2.setError("Hay que introducir un número");
                err = true;
            }

            //Ataque del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataque = Integer.parseInt(binding.etataque2.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etataque2.setError("Hay que introducir un número");
                err = true;
            }
            //Defensa del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensa = Integer.parseInt(binding.etdefensa2.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etdefensa2.setError("Hay que introducir un número");
                err = true;
            }
            //Ataque especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataqueEsp = Integer.parseInt(binding.etatEsp2.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etatEsp2.setError("Hay que introducir un número");
                err = true;
            }

            //Defensa especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensaEsp = Integer.parseInt(binding.etdefEsp2.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etdefEsp2.setError("Hay que introducir un número");
                err = true;
            }

            if (!err) {
                pokemonViewModel.crearPokemon(nombre, hp, ataque, defensa, ataqueEsp, defensaEsp);
            }
        });

        pokemonViewModel.pValidado.observe(getViewLifecycleOwner(), validado -> {
            if (validado) {
                binding.botonGuardarPok2.setEnabled(true);
            }
        });

        pokemonViewModel.pokemon.observe(getViewLifecycleOwner(), pokemonV -> pokemon = pokemonV);

        pokemonViewModel.errorNombre.observe(getViewLifecycleOwner(), nombre -> {
            if (nombre != null) {
                binding.etnombre2.setError(nombre);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.etnombre2.setError(null);
            }
        });

        pokemonViewModel.errorHp.observe(getViewLifecycleOwner(), vida -> {
            if (vida != null) {
                binding.ethp2.setError(vida);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.ethp2.setError(null);
            }
        });

        pokemonViewModel.errorAtaque.observe(getViewLifecycleOwner(), atk -> {
            if (atk != null) {
                binding.etataque2.setError(atk);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.etataque2.setError(null);
            }
        });

        pokemonViewModel.errorDefensa.observe(getViewLifecycleOwner(), def -> {
            if (def != null) {
                binding.etdefensa2.setError(def);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.etdefensa2.setError(null);
            }
        });

        pokemonViewModel.errorAtaqueEsp.observe(getViewLifecycleOwner(), atkEsp -> {
            if (atkEsp != null) {
                binding.etatEsp2.setError(atkEsp);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.etatEsp2.setError(null);
            }
        });

        pokemonViewModel.errorDefensaEsp.observe(getViewLifecycleOwner(), defEsp -> {
            if (defEsp != null) {
                binding.etdefEsp2.setError(defEsp);
                binding.botonGuardarPok2.setEnabled(false);
            } else {
                binding.etdefEsp2.setError(null);
            }
        });
        binding.etnombre2.addTextChangedListener(new TextChangedListener<EditText>(binding.etnombre2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.ethp2.addTextChangedListener(new TextChangedListener<EditText>(binding.ethp2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.etataque2.addTextChangedListener(new TextChangedListener<EditText>(binding.etataque2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.etdefensa2.addTextChangedListener(new TextChangedListener<EditText>(binding.etdefensa2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.etatEsp2.addTextChangedListener(new TextChangedListener<EditText>(binding.etatEsp2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.etdefEsp2.addTextChangedListener(new TextChangedListener<EditText>(binding.etdefEsp2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok2.setEnabled(false);
            }
        });

        binding.botonGuardarPok2.setOnClickListener(l -> {
            PokemonViewModel.listaPokemon.add(pokemon);
            navController.navigate(R.id.action_pokemon2Fragment_to_combateFragment);
        });
    }
}