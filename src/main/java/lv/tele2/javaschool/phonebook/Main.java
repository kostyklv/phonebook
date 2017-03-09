package lv.tele2.javaschool.phonebook;

import asg.cliche.ShellFactory;
import asg.cliche.example.Example;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            ShellFactory.createConsoleShell("example", null, new PhoneBook())
                    .commandLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}