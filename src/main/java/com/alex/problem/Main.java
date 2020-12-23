package com.alex.problem;

import com.alex.problem.di.DaggerAppComponent;
import com.alex.problem.presentation.View;

public class Main {

    public static void main(String[] args) {
        View view = new View(DaggerAppComponent.builder().build() );
        view.start();
    }
}
