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

    public void mainMenu(){
        while(true) {
            System.out.println("Välkommen till Wigells filmmagasin. Välj ett alternativ i menyn nedan.");
            System.out.println("""
                    [1] Hantera objekt för uthyrning
                    [2] Lägg till nytt objekt för uthyrning\
                    [3] Lägg till ny uthyrning
                    [4] Hantera befintliga uthyrningar
                    [5] Hantera medlemsregister
                    [6] Lägg till ny medlem
                    [7] Se månadens intäkter
                    [8] Avsluta programmet"""); //Skriver vad som har ändrats under dagen

            switch (scanner.nextInt()) {
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
                    membershipService.printMembers();
                    //Ska kunna filtrera enligt level och sortera enligt namn, och id
                    System.out.println("För att hantera en medlem skriv dess id följt av 'enter'.");
                    System.out.println("För  att sortera skriv \"s:\" följt av \"namn\" eller \"id\", " +
                            "för att filtrera enligt level skriv \"f:level\".");
                    /*if(scanner.next().trim().contains("f"))*/
                    clearScanner();
                    if (scanner.hasNextInt()) {
                        //Try/catch om member är null
                        Member memberToManage = membershipService.findMember(scanner.next());
                        manageMember(memberToManage);
                    } else {
                        System.out.println("Du skrev inte in integer");
                    }
               /* Member memberToRemove = membershipService.findMember(scanner.next());
                System.out.println(memberToRemove);
                //try/Catch om objekt är null
                membershipService.removeMember(memberToRemove);*/
                    break;
                case 6:
                    //Frågar om info
                    Member member = createNewMember();
                    // skickar vidare till MembershipService
                    membershipService.addMember(member);
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
    }

    private void manageMember(Member memberToManage) {
        while(true) {
            System.out.println(memberToManage);
            System.out.println("""
                    För att ändra namn skriv "n:" följt av det nya namnet, sedan 'enter'. \
                    För att ändra level skriv "l:" följt av "standard", "premium" eller "student", sedan 'enter'. \
                    För att ta bort skriv "ta bort", sedan 'enter'.\s
                    
                    För att gå tillbaka till huvudmenyn tryck endast 'enter'.""");
            clearScanner();
            String choice = scanner.nextLine().trim();
            System.out.println(choice);

            //Om användaren endast tryckt enter
            if (choice.isEmpty()) {
                break;
            }

            if(choice.toLowerCase().startsWith("ta bort")){
                removeMember(memberToManage);

            }

            choice = choice.concat(" ");
            if (choice.toLowerCase().contains("n:")) {
                //separerar
                System.out.println(choice.indexOf(" ", choice.indexOf("n:")));
                String newName = choice.substring(choice.indexOf("n:")+2, choice.indexOf(" ", choice.indexOf("n:")));
                changeMemberName(memberToManage, newName);
            }

            if(choice.toLowerCase().contains("l:")) {
                System.out.println(choice.indexOf(" ", choice.indexOf("l:")));
                String newLevel = choice.substring(choice.indexOf("l:")+2, choice.indexOf(" ", choice.indexOf("l:")));
                ChangeMemberStatus(memberToManage, newLevel);
            }
        }
    }

    //TODO Ska det vara separata metoder eller kan allt flyttas in i manageMember()?
    private void removeMember(Member memberToManage) {
        System.out.println("Vill du verkligen ta bort " + memberToManage.getId() +": "+ memberToManage.getName() + " och all dess historik? " +
                "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

        String answer = scanner.next().trim().toLowerCase();
        switch (answer) {
            case "ja" -> {
                membershipService.removeMember(memberToManage);
                System.out.println("Medlem borttagen.");
            }
            case "nej" -> System.out.println("Medlem ej borttagen.");
        }
    }

    private void ChangeMemberStatus(Member memberToManage, String newLevel) {
        if(newLevel.equals("standard")| newLevel.equals("premium")| newLevel.equals("student")){
            System.out.println("Vill du ändra level från " + memberToManage.getStatus() + " till " + newLevel + "? " +
                    "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

            String answer = scanner.next().trim().toLowerCase();
            if (answer.equals("ja")) {
                membershipService.changeMemberStatus(memberToManage, newLevel);
                System.out.println("Level ändrat till " + newLevel + ".");
            } else if (answer.equals("nej")) {
                System.out.println("Level ej ändrat.");
            }
        } else{
            System.out.println(newLevel + " är inte en valbar level, välj antingen \"standard\", \"premium\" eller \"student\"");
        }
    }

    private void changeMemberName(Member memberToManage, String newName) {
        System.out.println("Vill du ändra namnet från " + memberToManage.getName() + " till " + newName + "? " +
                "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

        String answer = scanner.next().trim().toLowerCase();
        if (answer.equals("ja")) {
            membershipService.changeMemberName(memberToManage, newName);
        } else if (answer.equals("nej")) {
            System.out.println("Namn ej ändrat.");
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
                    break;
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

    public void clearScanner(){
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }
    }
}
