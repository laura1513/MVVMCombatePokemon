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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmcombatepokemon.databinding.FragmentPokemon1Binding;

import org.w3c.dom.Text;

public class Pokemon1Fragment extends Fragment {
    private @NonNull FragmentPokemon1Binding binding;

    Button botonValidar;
    Button guardar;
    TextView hp1;
    TextView nombre1;
    TextView ataque1;
    TextView defensa1;
    TextView atEsp1;
    TextView defEsp1;
    Pokemon pokemon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonViewModel pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        botonValidar = view.findViewById(R.id.botonValidar);
        guardar = view.findViewById(R.id.botonGuardarPok1);
        botonValidar.setOnClickListener(v -> {
            boolean err = false;

            String nombre;
            nombre1 = view.findViewById(R.id.nombre1);
            int hp = 0;
            hp1 = view.findViewById(R.id.hp1);
            int ataque = 0;
            ataque1 = view.findViewById(R.id.ataque1);
            int defensa = 0;
            defensa1 = view.findViewById(R.id.defensa1);
            int ataqueEsp = 0;
            atEsp1 = view.findViewById(R.id.ataqueEsp1);
            int defensaEsp = 0;
            defEsp1 = view.findViewById(R.id.defEsp1);

            //Nombre del pokemon
            nombre = nombre1.toString();

            //HP del pokemon, salta la excepción en caso de no ser un numero
            try {
                hp = Integer.parseInt(hp1.toString());
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

        nombre1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.nombre1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        hp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.hp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        ataque1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.ataque1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        defensa1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.defensa1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        atEsp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.ataqueEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        defEsp1.addTextChangedListener(new TextChangedListener<EditText>((EditText) binding.defEsp1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                guardar.setEnabled(false);
            }
        });

        guardar.setOnClickListener(l -> {
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