package com.nilsson.rental;

import com.nilsson.rental.dao.*;
import com.nilsson.rental.entity.*;
import com.nilsson.rental.pricepolicy.*;

import java.util.Scanner;

public class Main {

/*Du har fått en provanställning på Wigellkoncernen och som första uppdrag ska du bygga
en konsolbaserad Java-applikation som modellerar en medlemsklubb med uthyrning av
utrustning. (t.ex. fordon, verktyg, sportprylar eller liknande)

Klasser som ska finnas i applikationen:
• Member (id, namn, status/level, historik)
• Item (abstract)  Subklasser som ex. Vehicle, Tool, SportGear eller liknande
beroende på ditt val av applikation. Var och en av klasserna ska innehålla sina
unika attribut och metoder enligt det vi pratat om.
• Rental (kopplar member och item för en viss tidsperiod)
• PricePolicy (interface) + konkreta strategier, ex. standard, student, premium.
• Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)
• RentalService och MembershipService ska innehålla affärslogiken
• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
uthyrning. Summera intäkter

applikationen används i klubben, där man i en meny kan komma åt och hantera medlemmar och inventariet av produkter,
samt att skapa ny och hantera befintliga uthyrningar.
*/
    public static void main(String[] args) {

        Member m1 = new Member("Lova", new Premium());
        Member m2 = new Member("Clara", new Standard());

        MemberRegistry memberRegistry = new MemberRegistry();
        memberRegistry.addMember(m1);
        memberRegistry.addMember(m2);

        KonsolMenu konsolMenu = new KonsolMenu(new Scanner(System.in));
        konsolMenu.getMembershipService().getMemberRegistry().addMember(m1);
        konsolMenu.getMembershipService().getMemberRegistry().addMember(m2);

        konsolMenu.mainMenu();

        System.out.println(konsolMenu.getMembershipService().getMemberRegistry().getMemberList());

    }
}