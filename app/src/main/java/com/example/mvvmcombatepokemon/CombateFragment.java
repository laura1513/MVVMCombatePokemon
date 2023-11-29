package com.example.mvvmcombatepokemon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmcombatepokemon.databinding.FragmentCombateBinding;

public class CombateFragment extends Fragment {

    private FragmentCombateBinding binding;
    boolean turn = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentCombateBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CombateViewModel combateViewModel = new ViewModelProvider(this).get(CombateViewModel.class);
        final Pokemon pokemon1 = combateViewModel.pokemon1;
        final Pokemon pokemon2 = combateViewModel.pokemon2;

        binding.HpBarP1.setMax(combateViewModel.vidaP1.getValue());
        binding.HpBarP1.setProgress(combateViewModel.vidaP1.getValue());
        binding.HpBarP2.setMax(combateViewModel.vidaP2.getValue());
        binding.HpBarP2.setProgress(combateViewModel.vidaP2.getValue());
        binding.lblNamePokemonP1.setText(pokemon1.getNombre());
        binding.lblAtkP1.setText(String.valueOf(pokemon1.getAtaque()));
        binding.lblAtkEspP1.setText(String.valueOf(pokemon1.getAtaqueEsp()));
        binding.lblHpP1.setText(String.valueOf(combateViewModel.vidaP1.getValue()));
        binding.lblNamePokemonP2.setText(pokemon2.getNombre());
        binding.lblAtkP2.setText(String.valueOf(pokemon2.getAtaque()));
        binding.lblAtkEspP2.setText(String.valueOf(pokemon2.getAtaqueEsp()));
        binding.lblHpP2.setText(String.valueOf(combateViewModel.vidaP2.getValue()));

        binding.btnAtkP1.setOnClickListener(l -> combateViewModel.combate(combateViewModel.vidaP2.getValue(), pokemon1.getAtaque(), pokemon2.getDefensa()));

        binding.btnAtkEspP1.setOnClickListener(l -> combateViewModel.combate(combateViewModel.vidaP2.getValue(), pokemon1.getAtaqueEsp(), pokemon2.getDefensaEsp()));

        binding.btnAtkP2.setOnClickListener(l -> combateViewModel.combate(combateViewModel.vidaP1.getValue(), pokemon2.getAtaque(), pokemon1.getDefensa()));

        binding.btnAtkEspP2.setOnClickListener(l -> combateViewModel.combate(combateViewModel.vidaP1.getValue(), pokemon2.getAtaqueEsp(), pokemon1.getDefensaEsp()));

        combateViewModel.turno.observe(getViewLifecycleOwner(), turno -> {
            turn = turno;
            if (turn) {
                binding.btnAtkP2.setEnabled(false);
                binding.btnAtkEspP2.setEnabled(false);
                binding.btnAtkP1.setEnabled(true);
                binding.btnAtkEspP1.setEnabled(true);
            } else {
                binding.btnAtkP2.setEnabled(true);
                binding.btnAtkEspP2.setEnabled(true);
                binding.btnAtkP1.setEnabled(false);
                binding.btnAtkEspP1.setEnabled(false);
            }
        });

        combateViewModel.vidaP1.observe(getViewLifecycleOwner(), vidaP1 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.HpBarP1.setProgress(vidaP1, true);
            } else {
                binding.HpBarP1.setProgress(vidaP1);
            }
            binding.lblHpP1.setText(String.valueOf(combateViewModel.vidaP1.getValue()));
        });
        combateViewModel.vidaP2.observe(getViewLifecycleOwner(), vidaP2 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.HpBarP2.setProgress(vidaP2, true);
            } else {
                binding.HpBarP2.setProgress(vidaP2);
            }
            binding.lblHpP2.setText(String.valueOf(combateViewModel.vidaP2.getValue()));
        });

        combateViewModel.ganador.observe(getViewLifecycleOwner(), ganador -> {
            StringBuilder message = new StringBuilder();

            message.append("Jugador " + ganador + " ha ganado con este pokemon: \n");
            message.append("Nombre: " + PokemonViewModel.listaPokemon.get(ganador - 1).getNombre() + "\n");
            message.append("Vida: " + PokemonViewModel.listaPokemon.get(ganador - 1).getHp() + "\n");
            message.append("Ataque: " + PokemonViewModel.listaPokemon.get(ganador - 1).getAtaque() + "\n");
            message.append("Defensa: " + PokemonViewModel.listaPokemon.get(ganador - 1).getDefensa() + "\n");
            message.append("Ataque Especial: " + PokemonViewModel.listaPokemon.get(ganador - 1).getAtaqueEsp() + "\n");
            message.append("Defensa Especial: " + PokemonViewModel.listaPokemon.get(ganador - 1).getDefensaEsp() + "\n");
            message.append("\n\n¿Quieres hacer otro combate con los mismos pokemons?");


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(message)
                    .setPositiveButton("Si", (dialogInterface, i) -> reset(combateViewModel))
                    .setNegativeButton("Exit", ((dialogInterface, i) -> getActivity().finish()));
            AlertDialog showResults = builder.create();
            showResults.setTitle("Resultado Combate");
            showResults.show();
        });
    }

    public void reset (CombateViewModel combateViewModel){
        combateViewModel.vidaP1.setValue(combateViewModel.pokemon1.getHp());
        combateViewModel.vidaP2.setValue(combateViewModel.pokemon2.getHp());
    }


}