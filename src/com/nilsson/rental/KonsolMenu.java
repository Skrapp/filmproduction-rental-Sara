package com.nilsson.rental;

import java.util.Scanner;

public class KonsolMenu {
    /*• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
    uthyrning. Summera intäkter*/
    private Scanner scanner;

    public KonsolMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void logIn(){
        System.out.println("Välkommen till Wigells filmmagasin. Välj ett alternativ i menyn nedan.");
        System.out.println("[1] Ange medlemsnummer\n" +
                "[2] Adminverktyg\n" +
                "[3] Bli en ny medlem!\n" +
                "[4] Avsluta programmet");
        switch (scanner.nextInt()){
            case 1:
                //membershipservice logInMember
                break;
            case 2:
                //Membershipservice  logInAdmin
                break;
            case 3:
                //Membershipservice addMember
                break;
            case 4:
                System.exit(1);
                break;
            default:
                System.out.println("Välj en giltig siffra från menyn");
        }
    }
}
