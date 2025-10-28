package com.nilsson.rental;

import com.nilsson.rental.entity.Member;
import com.nilsson.rental.entity.MemberIdComparator;
import com.nilsson.rental.entity.MemberNameComparator;
import com.nilsson.rental.entity.pricepolicy.Premium;
import com.nilsson.rental.entity.pricepolicy.PricePolicy;
import com.nilsson.rental.entity.pricepolicy.Standard;
import com.nilsson.rental.entity.pricepolicy.Student;
import com.nilsson.rental.service.MembershipService;
import com.nilsson.rental.service.RentalService;

import java.io.*;
import java.util.Comparator;

public class KonsolMenu {
    /*• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
    uthyrning. Summera intäkter*/
    BufferedReader reader;
    private MembershipService membershipService;
    private RentalService rentalService;

    public KonsolMenu(BufferedReader reader) {
        this.reader = reader;
        membershipService = new MembershipService();
        rentalService = new RentalService();
    }

    public KonsolMenu(BufferedReader reader, MembershipService membershipService, RentalService rentalService) {
        this.reader = reader;
        this.membershipService = membershipService;
        this.rentalService = rentalService;
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

    //Huvudmenyn för program
    public void mainMenu(){
        while(true) {
            System.out.println("Välkommen till Wigells filmmagasin. Välj ett alternativ i menyn nedan.");
            System.out.println("""
                    [1] Hantera objekt för uthyrning
                    [2] Lägg till nytt objekt för uthyrning
                    [3] Lägg till ny uthyrning
                    [4] Hantera befintliga uthyrningar
                    [5] Hantera medlemsregister
                    [6] Lägg till ny medlem
                    [7] Se månadens intäkter
                    [8] Avsluta programmet"""); //Skriver vad som har ändrats under dagen

            String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (input) {
                case "1":
                    //Hantera inventeringen av objekt
                    break;
                case "2":
                    //Skapa ett nytt objekt
                    break;
                case "3":
                    //Skapa en ny uthyrning
                    break;
                case "4":
                    //Hantera alla uthyrningar
                    break;
                case "5":
                    //Hantera alla medlemmar
                    ManageAllMembers();
                    break;
                case "6":
                    //Skapa ny medlem
                    Member member = createNewMember();
                    //TODO felhantering om det är felaktig info
                    membershipService.addMember(member);
                    break;
                case "7":
                    //Se månadens intäkter
                    break;
                case "8":
                    System.out.println("Tack för idag!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Välj en giltig siffra från menyn.");
            }
        }
    }

    //Hantera alla medlemmar, kan välja en medlem att gå in och ändra, kan sortera och filtrera listan
    private void ManageAllMembers() {
        //memberComparator bestämmer hur members ska skrivas ut
        //pricePolicyFilter filtrerar enligt vald PricePolicy klass (PricePolicy.class inkluderas alla medlemmar som har en level)
        Comparator<Member> memberComparator = membershipService.getMemberRegistry().getDefaultComparator();
        Class<? extends PricePolicy> pricePolicyFilter = PricePolicy.class;
        String searchName = "";

        while (true) {
            //Print alla medlemmar
            membershipService.printMembers(memberComparator, pricePolicyFilter, searchName);

            //Ska kunna filtrera enligt level och sortera enligt namn, och id
            System.out.println("""
                    För att hantera en medlem skriv dess id följt av 'enter'.
                    
                    För att söka enligt namn skriv "s:" följt av det du vill söka efter sedan 'enter'
                    För  att sortera skriv "o:" följt av "namn" eller "id" sedan 'enter'.
                    För att filtrera enligt level skriv "f:" följt av antingen "alla", "standard", "premium" eller "student" sedan 'enter'.
                    Du kan separera flera anrop med mellanslag.
                    
                    För att gå tillbaka till huvudmenyn tryck endast 'enter'.""");

            String input;
            try {
                input = reader.readLine().trim();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Om användaren endast trycker enter
            if (input.isEmpty()) {
                break;
            }

            if (Character.isDigit(input.charAt(0))){
                try {
                    Member memberToManage = membershipService.findMemberById(input);
                    manageMember(memberToManage);
                    break;
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            if(input.contains("s:")){
                searchName = getSectionFor(input, "s:");
                System.out.println("Filtrerar enligt " + searchName);
            }
            if(input.contains("f:")){
                String level = getSectionFor(input.toLowerCase(), "f:");
                if(level.equals("standard")| level.equals("premium")| level.equals("student")| level.equals("alla")){
                    System.out.println("Filtrera enligt " + level);
                    switch (level){
                        case "alla" -> pricePolicyFilter = PricePolicy.class;
                        case "standard" -> pricePolicyFilter = Standard.class;
                        case "premium" -> pricePolicyFilter = Premium.class;
                        case "student" -> pricePolicyFilter = Student.class;
                    }
                }else{
                    System.out.println(level + " är inte en valbar level, välj \"alla\", \"standard\", \"premium\" eller \"student\"");
                }
            }
            if(input.contains("o:")){
                String sortBy = getSectionFor(input.toLowerCase(), "o:");
                if(sortBy.equals("namn")){
                    System.out.println("Sorterar enligt namn");
                    memberComparator = new MemberNameComparator();
                }else if(sortBy.equals("id")){
                    System.out.println("Sorterar enligt id");
                    memberComparator = new MemberIdComparator();
                }
            }
        }
    }

    //Hantera vald medlems data
    private void manageMember(Member memberToManage) {
        while(true) {
            System.out.println(memberToManage);
            System.out.println("""
                    För att ändra namn skriv "n:" följt av det nya namnet, sedan 'enter'. \
                    För att ändra level skriv "l:" följt av "standard", "premium" eller "student", sedan 'enter'. \
                    För att ta bort skriv "ta bort", sedan 'enter'.\s
                    För att gå tillbaka till huvudmenyn tryck endast 'enter'.""");

            String input;
            try {
                input = reader.readLine().trim();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(input);

            //Om användaren endast tryckt enter, gå ur while-metod
            if (input.isEmpty()) {
                break;
            }

            //Tar bort medlem från register
            if(input.toLowerCase().startsWith("ta bort")){
                removeMember(memberToManage);
                //Om medlem blivit borttagen från registret så går man ur menyn
                if(!membershipService.getMemberRegistry().getMemberSet().contains(memberToManage)){
                    break;
                }
            }
            else if (input.toLowerCase().startsWith("n:")) {
                //separerar
                String newName = input.substring(input.indexOf("n:") + 2);

                //String newName = getSectionAfter(input, "n:");
                changeMemberName(memberToManage, newName);
            }
            else if(input.toLowerCase().startsWith("l:")) {
                String newLevel = input.substring(input.indexOf("l:") + 2);
                changeMemberLevel(memberToManage, newLevel);
            }
        }
    }



    //Tar bort medlem från medlemsregister
    private void removeMember(Member memberToManage) {
        System.out.println("Vill du verkligen ta bort " + memberToManage.getId() +": "+ memberToManage.getName() + " och all dess historik? " +
                "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

        try {
            String input = reader.readLine().trim().toLowerCase();

            switch (input) {
                case "ja" -> {
                    membershipService.removeMember(memberToManage);
                    System.out.println("Medlem borttagen.");
                }
                case "nej" -> System.out.println("Medlem ej borttagen.");
            }
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    //Ändrar medlemmens level
    private void changeMemberLevel(Member memberToManage, String level) {
        if(level.equals("standard")| level.equals("premium")| level.equals("student")){
            System.out.println("Vill du ändra level från " + memberToManage.getLevel() + " till " + level + "? " +
                    "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

            try {
                String input = reader.readLine().trim().toLowerCase();

                if (input.equals("ja")) {
                    membershipService.changeMemberLevel(memberToManage, level);
                    System.out.println("Level ändrat till " + level + ".");
                } else if (input.equals("nej")) {
                    System.out.println("Level ej ändrat.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            System.out.println(level + " är inte en valbar level, välj antingen \"standard\", \"premium\" eller \"student\"");
        }
    }

    //Ändrar medlemmens namn
    private void changeMemberName(Member memberToManage, String newName) {
        System.out.println("Vill du ändra namnet från " + memberToManage.getName() + " till " + newName + "? " +
                "Svara med \"ja\" eller \"nej\" följt av 'enter'.");

        try {
            String input = reader.readLine().trim().toLowerCase();

            if (input.equals("ja")) {
                membershipService.changeMemberName(memberToManage, newName);
            } else if (input.equals("nej")) {
                System.out.println("Namn ej ändrat.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Skapa en ny medlem till registret
    public Member createNewMember(){
        System.out.println("Skriv namn");
        String name;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Vilken level har medlemmen?");
        PricePolicy pricePolicy =null;
        boolean choosing = true;
        do {
            System.out.println("""
                    [1] Standard
                    [2] Premium
                    [3] Student""");
            try {
                int input = Integer.parseInt(reader.readLine());
                switch (input) {
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e){
                System.out.println("Ange en siffra.");
            }
        } while (choosing);

        return new Member(name, pricePolicy);
    }

    //Separerar strängen så att endast det svar som gäller given prefix skickas tillbaka
    private String getSectionFor(String entireString, String prefix) {
        entireString = entireString.concat(" ");
        return entireString.substring(entireString.indexOf(prefix) + prefix.length(), entireString.indexOf(" ", entireString.indexOf(prefix)));
    }
}
