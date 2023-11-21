package com.example.mvvmcombatepokemon;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeList {
    private List<Pokemon> list;

    ThreadSafeList() {
        list = new ArrayList<>();
    }

    synchronized void add(Pokemon p) {
        list.add(p);
    }

    Pokemon get(int index) {
        return list.get(index);
    }

    int size() {
        return list.size();
    }
}
