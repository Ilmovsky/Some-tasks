package com.alex.problem.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Communicator {
    private final Scanner scanner;
    private final PrintStream out;

    public Communicator(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public int askInt(String message) {
        out.println(message);
        return scanner.nextInt();
    }

    public String askString(String message) {
        out.println(message);
        return scanner.next();
    }
}
