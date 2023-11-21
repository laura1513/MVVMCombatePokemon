package com.example.mvvmcombatepokemon;

public class Pokemon {
    private String nombre;
    private int hp;
    private int ataque;
    private int defensa;
    private int ataqueEsp;
    private int defensaEsp;

    public Pokemon(String nombre, int hp, int ataque, int defensa, int ataqueEsp, int defensaEsp) {
        this.nombre = nombre;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEsp = ataqueEsp;
        this.defensaEsp = defensaEsp;
    }

    public Pokemon() {
    }

    interface Callback {
        void cuandoHayaErrorEnNombre(String errNombre);
        void cuandoHayaErrorEnVida(String errHp);
        void cuandoHayaErrorEnAtaque(String errAtaque);
        void cuandoHayaErrorEnDefensa(String errDefensa);
        void cuandoHayaErrorEnAtaqueEspecial(String errAtaqueEsp);
        void cuandoHayaErrorEnDefensaEspecial(String errDefensaEsp);
        void cuandoTermineDeValidar(boolean validado, Pokemon pokemonV);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEsp() {
        return ataqueEsp;
    }

    public void setAtaqueEsp(int ataqueEsp) {
        this.ataqueEsp = ataqueEsp;
    }

    public int getDefensaEsp() {
        return defensaEsp;
    }

    public void setDefensaEsp(int defensaEsp) {
        this.defensaEsp = defensaEsp;
    }

    public void crearPokemon(String nombre, int hp, int ataque, int defensa, int ataqueEsp, int defensaEsp, Callback callback) {
        int min = 0;
        int max = 999;

        boolean error = false;

        if (nombre.isEmpty() || nombre.matches("^\\s*$")){
            callback.cuandoHayaErrorEnNombre("Tienen que haber caracteres");
            error = true;
        }

        if (comprobarLimites(hp, min, max)){
            callback.cuandoHayaErrorEnVida(hp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(ataque, min, max)){
            callback.cuandoHayaErrorEnAtaque(ataque + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(defensa, min, max)){
            callback.cuandoHayaErrorEnDefensa(defensa + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(ataqueEsp, min, max)){
            callback.cuandoHayaErrorEnAtaqueEspecial(ataqueEsp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(defensaEsp, min, max)){
            callback.cuandoHayaErrorEnDefensaEspecial(defensaEsp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (!error) {
            callback.cuandoTermineDeValidar(true, new Pokemon(nombre, hp, ataque, defensa, ataqueEsp, defensaEsp));
        }

    }

    private static boolean comprobarLimites(int num, int min, int max) {
        return num < min || num > max;
    }
}
