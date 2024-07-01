package com.example.exalt.services;

import com.example.exalt.entities.Pass;

import java.util.Comparator;

public class VIPComparator implements Comparator<Pass> {
    @Override
    public int compare(Pass p1, Pass p2) {
        return Boolean.compare(p2.isVIP(), p1.isVIP());
    }
}
