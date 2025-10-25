package com.nilsson.rental;

import com.nilsson.rental.entity.Member;
import com.nilsson.rental.pricepolicy.Premium;
import com.nilsson.rental.pricepolicy.PricePolicy;
import com.nilsson.rental.pricepolicy.Standard;
import com.nilsson.rental.pricepolicy.Student;
import com.nilsson.rental.service.MembershipService;
import com.nilsson.rental.service.RentalService;

import java.util.Scanner;

public class KonsolMenu {
    /*• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
    uthyrning. Summera intäkter*/
    private Scanner scanner;
    private MembershipService membershipService;
    private RentalService rentalService;

    public KonsolMenu(Scanner scanner) {
        this.scanner = scanner;
        membershipService = new MembershipService();
        rentalService = new RentalService();
    }

    public KonsolMenu(Scanner scanner, MembershipService membershipService, RentalService rentalService) {
        this.scanner = scanner;
        this.membershipService = membershipService;
        this.rentalService = rentalService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public MembershipService getMembershipService() {
        return membershipService;
    }

    public void setMembershipService(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    public RentalService getRentalService() {
        return rentalService;
    }

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
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

    public void mainMenu(){
        System.out.println("Välkommen till Wigells filmmagasin. Välj ett alternativ i menyn nedan.");
        System.out.println("[1] Hantera objekt för uthyrning\n" +
                "[2] Lägg till nytt objekt för uthyrning" +
                "[3] Lägg till ny uthyrning\n" +
                "[4] Hantera befintliga uthyrningar\n" +
                "[5] Hantera medlemsregister\n" +
                "[6] Lägg till ny medlem\n" +
                "[7] Se månadens intäkter\n" +
                "[8] Avsluta programmet"); //Skriver vad som har ändrats under dagen

        switch (scanner.nextInt()){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                //Print alla medlemmar
                break;
            case 6:
                //Frågar om info
                Member member = createNewMember();
                membershipService.addMember(member);
                // skickar vidare till Memebership service
                break;
            case 7:
                break;
            case 8:
                //Print daglig händelse
                System.exit(1);
                break;
            default:
                System.out.println("Välj en giltig siffra från menyn.");
        }
    }

    public Member createNewMember(){
        System.out.println("Skriv namn");
        String name = scanner.next();

        System.out.println("Vilken level har medlemmen?");
        PricePolicy pricePolicy = null;
        boolean choosing = true;
        do {
            switch (scanner.nextInt()) {
                case 1:
                    pricePolicy = new Standard();
                    choosing = false;
                case 2:
                    pricePolicy = new Premium();
                    choosing = false;
                    break;
                case 3:
                    pricePolicy = new Student();
                    choosing = false;
                    break;
                default:
                    System.out.println("Ange ett giltigt värde.");
            }
        } while (choosing);

        return new Member(name, pricePolicy);
    }
}
