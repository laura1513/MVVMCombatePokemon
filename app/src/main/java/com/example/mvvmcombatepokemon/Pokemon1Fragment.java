package com.example.mvvmcombatepokemon;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mvvmcombatepokemon.databinding.FragmentPokemon1Binding;

public class Pokemon1Fragment extends Fragment {
    private @NonNull FragmentPokemon1Binding binding;
    Pokemon pokemon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentPokemon1Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        binding.botonValidar.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            int hp = 0;
            int ataque = 0;
            int defensa = 0;
            int ataqueEsp = 0;
            int defensaEsp = 0;

            //Nombre del pokemon
            nombre = binding.nombre1.getText().toString();

            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(binding.hp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.hp1.setError("Hay que introducir un número");
                err = true;
            }

            //Ataque del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataque = Integer.parseInt(binding.ataque1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.ataque1.setError("Hay que introducir un número");
                err = true;
            }
            //Defensa del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensa = Integer.parseInt(binding.defensa1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.defensa1.setError("Hay que introducir un número");
                err = true;
            }
            //Ataque especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                ataqueEsp = Integer.parseInt(binding.ataqueEsp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.ataqueEsp1.setError("Hay que introducir un número");
                err = true;
            }

            //Defensa especial del pokemon, salta la excepción en caso de no ser un numero
            try {
                defensaEsp = Integer.parseInt(binding.defEsp1.getText().toString());
            } catch (NumberFormatException ex) {
                binding.defEsp1.setError("Hay que introducir un número");
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

        binding.nombre1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.nombre1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.hp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.hp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.ataque1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.ataque1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.defensa1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.defensa1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.ataqueEsp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.ataqueEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.defEsp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.defEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                binding.botonGuardarPok1.setEnabled(false);
            }
        });

        binding.botonGuardarPok1.setOnClickListener(l -> {
            PokemonViewModel.listaPokemon.add(pokemon);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Se ha guardado el " + pokemon.getNombre());
            builder.setPositiveButton("Okay", ((dialogInterface, i) -> {
                dialogInterface.cancel();
            }));
            AlertDialog saved = builder.create();
            saved.setTitle("Guardado");
            saved.show();
        });
    }
}