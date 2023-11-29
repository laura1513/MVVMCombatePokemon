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

public class Pokemon1Fragment extends Fragment {
    private @NonNull FragmentPokemon1Binding binding;
    private Pokemon pokemon = null;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentPokemon1Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        navController = Navigation.findNavController(view);

        binding.botonValidar1.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            int hp = 0;
            int ataque = 0;
            int defensa = 0;
            int ataqueEsp = 0;
            int defensaEsp = 0;

            //Nombre del pokemon
            nombre = binding.etnombre1.getText().toString();
            //
            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(binding.ethp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.ethp1.setError("Hay que introducir un número");
                err = true;
            }

            //Ataque del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataque = Integer.parseInt(binding.etataque1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etataque1.setError("Hay que introducir un número");
                err = true;
            }
            //Defensa del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensa = Integer.parseInt(binding.etdefensa1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etdefensa1.setError("Hay que introducir un número");
                err = true;
            }
            //Ataque especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataqueEsp = Integer.parseInt(binding.etatEsp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etatEsp1.setError("Hay que introducir un número");
                err = true;
            }

            //Defensa especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensaEsp = Integer.parseInt(binding.etdefEsp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.etdefEsp1.setError("Hay que introducir un número");
                err = true;
            }

            if (!err) {
                pokemonViewModel.crearPokemon(nombre, hp, ataque, defensa, ataqueEsp, defensaEsp);
            }
        });

        pokemonViewModel.pValidado.observe(getViewLifecycleOwner(), validado -> {
            if (validado) {
                binding.botonGuardarPok1.setEnabled(true);
            }
        });

        pokemonViewModel.pokemon.observe(getViewLifecycleOwner(), pokemonV -> pokemon = pokemonV);

        pokemonViewModel.errorNombre.observe(getViewLifecycleOwner(), nombre -> {
            if (nombre != null) {
                binding.etnombre1.setError(nombre);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.etnombre1.setError(null);
            }
        });

        pokemonViewModel.errorHp.observe(getViewLifecycleOwner(), vida -> {
            if (vida != null) {
                binding.ethp1.setError(vida);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.ethp1.setError(null);
            }
        });

        pokemonViewModel.errorAtaque.observe(getViewLifecycleOwner(), atk -> {
            if (atk != null) {
                binding.etataque1.setError(atk);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.etataque1.setError(null);
            }
        });

        pokemonViewModel.errorDefensa.observe(getViewLifecycleOwner(), def -> {
            if (def != null) {
                binding.etdefensa1.setError(def);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.etdefensa1.setError(null);
            }
        });

        pokemonViewModel.errorAtaqueEsp.observe(getViewLifecycleOwner(), atkEsp -> {
            if (atkEsp != null) {
                binding.etatEsp1.setError(atkEsp);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.etatEsp1.setError(null);
            }
        });

        pokemonViewModel.errorDefensaEsp.observe(getViewLifecycleOwner(), defEsp -> {
            if (defEsp != null) {
                binding.etdefEsp1.setError(defEsp);
                binding.botonGuardarPok1.setEnabled(false);
            } else {
                binding.etdefEsp1.setError(null);
            }
        });
        binding.etnombre1.addTextChangedListener(new TextChangedListener<EditText>(binding.etnombre1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.ethp1.addTextChangedListener(new TextChangedListener<EditText>(binding.ethp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.etataque1.addTextChangedListener(new TextChangedListener<EditText>(binding.etataque1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.etdefensa1.addTextChangedListener(new TextChangedListener<EditText>(binding.etdefensa1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.etatEsp1.addTextChangedListener(new TextChangedListener<EditText>(binding.etatEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.etdefEsp1.addTextChangedListener(new TextChangedListener<EditText>(binding.etdefEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.botonGuardarPok1.setOnClickListener(l -> {
            PokemonViewModel.listaPokemon.add(pokemon);
            navController.navigate(R.id.action_pokemon1Fragment_to_pokemon2Fragment);
        });
    }
}