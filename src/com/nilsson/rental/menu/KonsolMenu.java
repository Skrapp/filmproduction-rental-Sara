package com.nilsson.rental.menu;

import com.nilsson.rental.dao.Rental;
import com.nilsson.rental.entity.Member;
import com.nilsson.rental.entity.MemberIdComparator;
import com.nilsson.rental.entity.MemberNameComparator;
import com.nilsson.rental.entity.items.*;
import com.nilsson.rental.entity.pricepolicy.Premium;
import com.nilsson.rental.entity.pricepolicy.PricePolicy;
import com.nilsson.rental.entity.pricepolicy.Standard;
import com.nilsson.rental.entity.pricepolicy.Student;
import com.nilsson.rental.service.MembershipService;
import com.nilsson.rental.service.RentalService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class KonsolMenu {
    /*• Konsolmeny: lägg till/sök/ändra medlemmar. Lista/filtrera items. Boka/avsluta
    uthyrning. Summera intäkter*/
    //TODO separera till olika menu-klasser
    //TODO Koppla alla händelser som inte behöver beräknad till memberRegistry istället för att gå genom mellanhanden memberService
    private BufferedReader reader;
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


    /**
     * Ser till att det användaren skickar inte är tom, repeterar tills rätt format är inskikckat
     * @param askFromUser vad användaren ska svara på
     * @return en sträng med användarens svar
     * @throws IOException
     */
    public String getInputNotEmpty(String askFromUser) throws IOException{
        while (true) {
            System.out.println(askFromUser + ":");
            String input = reader.readLine().trim();
            if (input.isEmpty()) {
                System.out.println(askFromUser + " kan inte vara tom.");
                continue;
            }
            return input;
        }
    }

    /**
     * Ser till att det användaren skickar inte är tom och är kompitabel som double, repeterar tills rätt format är inskikckat
     * @param askFromUser vad användaren ska svara på
     * @return en double av användarens svar
     * @throws IOException
     */
    public double getInputDouble(String askFromUser) throws IOException{
        while (true) {
            String input = getInputNotEmpty(askFromUser);
            double inputAsDouble = 0;
            try {
                inputAsDouble = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ange ett tal");
                continue;
            }

            return inputAsDouble;
        }
    }

    /**
     * Ser till att det användaren skickar inte är tom och är kompitabel som int, repeterar tills rätt format är inskickkat
     * @param askFromUser vad användaren ska svara på
     * @return en int som finns i menyn
     * @throws IOException
     */
    public int getInputInt(String askFromUser) throws IOException {
        while (true){
            System.out.println(askFromUser + ". Svara med ett heltal.");
            try {
                return Integer.parseInt(reader.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Skriv ett heltal");
            }

        }
    }

    /**
     * Används för menyval. Ser till att det användaren skickar inte är tom och är kompitabel som int och inom angivna gränser, repeterar tills rätt format är inskikckat
     * @param askFromUser vad användaren ska svara på
     * @param maximum högsta möjliga val
     * @param minimum lägsta möjliga val
     * @return en int som finns i menyn
     * @throws IOException
     */
    public int getInputIntMenu(String askFromUser, int maximum, int minimum) throws IOException {
        while (true){
            System.out.println(askFromUser + ". Svara med tal från menyn.");
            try {
                int input = Integer.parseInt(reader.readLine().trim());
                if (input <= maximum || input >= minimum){
                    return input;
                }else {
                    System.out.println("Skriv ett av talen från menyn.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Skriv ett heltal");
            }
        }
    }

    /**
     * Ser till att det användaren skickar in antingen är ja eller nej och returnerar boolean.
     * @param askFromUser vad användaren ska svara på
     * @return true om användare skriver ja, false om användaren skriver nej
     * @throws IOException
     */
    public boolean getInputYesOrNo(String askFromUser) throws IOException{
        while (true) {
            System.out.println(askFromUser + "? Svara med 'Ja' eller 'nej'.");
            String input = reader.readLine().trim();
            if (input.equalsIgnoreCase("ja")) {
                return true;
            }else if(input.equalsIgnoreCase("nej")){
                return false;
            } else {
                System.out.println("Svara med antingen 'Ja' eller 'Nej'.");
            }
        }
    }

    /**
     * Huvudmeny, användare väljer ett menyval som leder vidare
     */
    public void mainMenu(){
        while(true) {
            System.out.println("\nVälkommen till Wigells filmmagasin.");
            System.out.println("""
                    [1] Hantera produkter för uthyrning
                    [2] Lägg till nytt produkter för uthyrning
                    [3] Lägg till ny uthyrning
                    [4] Se befintliga uthyrningar
                    [5] Avsluta/ändra uthyrning
                    [6] Hantera medlemsregister
                    [7] Lägg till ny medlem
                    [8] Se månadens intäkter
                    [0] Avsluta programmet"""); //Skriver vad som har ändrats under dagen

            int input;
            try {
                input = getInputIntMenu("Välj menyalternativ", 8, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (input) {
                case 1 ->{
                    //Hantera inventeringen av objekt
                    printItems();
                    Item itemToChange = chooseItem("ändra");
                    if(itemToChange == null){
                        continue;
                    }
                    changeItem(itemToChange);
                }
                case 2 -> {
                    //Skapa ett nytt objekt
                    Item newItem = createNewItem();
                    if (newItem == null) {
                        continue;
                    }
                    rentalService.addItem(newItem);
                    System.out.println("Skapad: " + newItem);
                }
                case 3 -> {
                    //Skapa en ny uthyrning
                    renting();
                }
                case 4 -> {
                    //Skriv ut alla uthyrningar
                    for(Member member : membershipService.getMemberRegistry().getMemberSet()){
                        System.out.println(member);
                        member.printRentalHistory();
                        System.out.println("----------------------------------------------------------");
                        System.out.println();
                    }
                }
                case 5 ->{
                    returnItem();
                }
                case 6 -> {
                    //Hantera alla medlemmar
                    Member memberToManage = chooseMember("hantera");
                    manageMember(memberToManage);
                }
                case 7 -> {
                    //Skapa ny medlem
                    Member member = createNewMember();
                    //TODO felhantering om det är felaktig info
                    membershipService.addMember(member);
                }
                case 8 ->{
                //Se alla intäkter under dagen
                    System.out.println("Inkomst från medlemskap: " + membershipService.getIncome());
                    System.out.println("Inkomst från hyror: " + rentalService.getIncome());
                    double totalIncome = rentalService.getIncome() + membershipService.getIncome();
                    System.out.println("Total inkomst: " + totalIncome);
                }
                case 0->{
                    System.out.println("Tack för idag!");
                    System.exit(1);
                }
                default-> System.out.println("Välj en giltig siffra från menyn.");
            }
        }
    }

    /**
     * Huvudmetod för att lämna tillbaka
     */
    private void returnItem() {
        Member rentingMember = chooseMember("lämna tillbaka produkt");
        if(rentingMember == null){
            return;
        }
        if(rentingMember.getRentalHistory().isEmpty()){
            System.out.println(rentingMember.getName() + " har inga tidigare uthyrningar.");
        }

        Rental rental = chooseRental(rentingMember);
        if(rental == null){
            return;
        }
        changeRental(rental);
    }

    /**
     * Användaren kan välja att avsluta en uthyrning
     * @param rental uthyrningen som ska avslutas
     */
    private void changeRental(Rental rental) {
        System.out.println(rental);
        System.out.println("För att avsluta uthyrningen skriv \"avsluta\" följt av 'enter'.\n" +
                "För att gå tillbaka tryck endast 'enter'.");

        try {
            String input = reader.readLine().trim();

            if(input.isEmpty()){
                return;
            }

            if(input.equalsIgnoreCase("avsluta") ){
                if(!rental.isReturned()) {
                    rental.setReturned(true);
                    System.out.println(rental.getItem().getName() + " har blivit återlämnad.");
                } else {
                    System.out.println("Produkten är redan återlämnad.");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Användaren väljer en av medlemmens uthyrningar
     * @param rentingMember medlem som har uthyrningar
     * @return
     */
    private Rental chooseRental(Member rentingMember) {
        rentingMember.printRentalHistory();

        System.out.println("För att hantera en uthyrning skriv dess id följt av 'enter'.\n" +
                "För att gå tillbaka tryck endast 'enter'.");
        try {
            String input = reader.readLine().trim();

            if(input.isEmpty()){
                return null;
            }
            for (Rental rental : rentingMember.getRentalHistory()){
                if(rental.getId().equals(input)){
                    return rental;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Huvudmetod för att hyra
     */
    public void renting() {
        Member rentingMember = chooseMember("välja medlem som ska hyra");
        if(rentingMember == null){
            return;
        }
        printItems();
        Item item = chooseItem("hyra");
        if(item == null){
            return;
        }
        Item itemToRent = getItemInStock(item.getName());
        if(itemToRent == null){
            System.out.println(item.getName() + " finns inte inne.");
            return;
        }
        Rental newRental = createRental(itemToRent, rentingMember);
        itemToRent.setInStock(addRentalToMember(newRental, rentingMember));
    }

    /**
     * Om produkten finns inne hämtas Item, annars null
     * @param name söker efter Item enligt namn
     * @return Om produkten finns inne hämtas Item, annars null
     */
    private Item getItemInStock(String name) {
        List<Item> items = rentalService.getAllItemsByName(name);
        for (Item item : items){
            if(item.isInStock()){
                return item;
            }
        }
        return null;
    }

    /**
     * Användaren får godkänna om uthyrningen ser bra ut, svarar användare ja skickas rental till member
     * @param newRental Uthyrningen som det är frågan om
     * @param rentingMember Medlemmen som kommer hyra produkten
     * @return Returnerar true om uthyrningen genomförts, annars false
     */
    private boolean addRentalToMember(Rental newRental, Member rentingMember) {
        System.out.println(newRental);
        System.out.println("Kostnad för medlem: " + rentingMember.getLevel().applyDiscount(newRental.getTotalCost()));
        System.out.println("Medlem som ska hyra: " + rentingMember);
        try {
            if(getInputYesOrNo("Godkänns transaktion")) {
                rentalService.addRentalToMember(rentingMember, newRental);
                System.out.println("Uthyrning genomförd");
                return true;
            } else {
                System.out.println("Ingen uthyrning genomförd");
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Skapar en rental utifrån användarens input
     * @param itemToRent Vad som ska hyras ut
     * @param rentingMember Vem som ska hyra
     * @return
     */
    private Rental createRental(Item itemToRent, Member rentingMember) {
        try {
            int days = getInputInt("Antal dagar");
            return new Rental(LocalDateTime.now(), LocalDateTime.now().plusDays(days), itemToRent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Användare väljer kategori, kategorin skrivs ut
     */
    private void printItems() {
        System.out.println("""
                [1] Alla produkter
                [2] Kameror
                [3] Ljus
                [4] Mikrofoner
                [5] Accessoarer
                [0] Tillbaka""");
        boolean choosing = true;
        do{
            try {
                int input = getInputIntMenu("Välj kategori", 5, 0);
                switch (input) {
                    case 1: rentalService.printEntireInventory();
                        choosing = false;
                    break;
                    case 2: rentalService.printCategory(Camera.class);
                        choosing = false;
                    break;
                    case 3: rentalService.printCategory(Light.class);
                        choosing = false;
                    break;
                    case 4: rentalService.printCategory(Microphone.class);
                        choosing = false;
                    break;
                    case 5: rentalService.printCategory(Accessory.class);
                        choosing = false;
                    break;
                    case 0: choosing = false;
                    break;
                    default:
                        System.out.println("Skriv in ett giltigt alternativ");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (choosing);

    }

    /**
     * Användaren väljer utifrån namn en produkt.
     * TODO bör använda annat än namn ifall två produkter heter samma sak
     */
    private Item chooseItem(String whatToDoWithItem) {
        while (true) {
            System.out.println("Skriv in namnet på produkten du vill " + whatToDoWithItem + " följt av 'enter'." +
                    "Om du vill gå tillbaka tryck endast 'enter'.");

            try {
                String name = reader.readLine().trim();

                if (name.isEmpty()) {
                    return null;
                }

                Item itemToManage = rentalService.getSingleItemByName(name);
                if (itemToManage == null) {
                    System.out.println("Det finns ingen produkt med namnet " + name);
                    continue;
                }

                return itemToManage;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Användare väljer genom kommandon hur produkten ska ändras.
     * @param itemToManage Vald produkt som ska ändras
     */
    private void changeItem(Item itemToManage) {
        while (true) {
            System.out.println(itemToManage);
            printChangeItemMenu(itemToManage);

            try {
                String input = reader.readLine().trim();

                if (input.isEmpty()) {
                    return;
                }

                if(!input.contains(":") || input.length() < 3){
                    System.out.println("Felande kommando. T.ex. skriv \"n:Hero 4\" för att ändra namnet till Hero 4.");
                    continue;
                }

                String command = input.substring(0, 2);
                String newChange = input.substring(2);

                if (!makeChangesToItem(itemToManage, command, newChange)){
                    System.out.println("Felande kommando. T.ex. skriv \"n:Hero 4\" för att ändra namnet till Hero 4.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Skriver ut hur man skriver kommandon för att ändra produkten.
     * Beroende på vad det är för typ av kategori finns olika kommandon
     * @param itemToChange Vald produkt som ska ändras
     */
    private void printChangeItemMenu(Item itemToChange){
        StringBuilder menu = new StringBuilder();
        menu.append("\nFör att ändra namn skriv \"n:\" följt av det nya namnet.\n");
        menu.append("För att ändra märke skriv \"m:\" följt av det nya märket.");
        menu.append("För att ändra beskrivning skriv \"b:\" följt av den nya beskrivningen.\n");
        menu.append("För att ändra kostnad/dygn skriv \"k:\" följt av nytt pris.\n");

        switch(itemToChange) {
            case Accessory _ -> {
                menu.append("För att ändra vad det är för accessoar till skriv \"a:\" följt av \"Kamera\", \"Ljus\" eller \"Mikrofon\".\n");
            }
            case Camera _ -> {
                menu.append("För att ändra om produkten har autofokus skriv \"a:\" följt av \"ja\" eller \"nej\".\n");
                menu.append("För att ändra ljudingång skriv \"l:\" följt av typ av ljudingång.\n");
            }
            case Light _ -> {
                menu.append("För att ändra om produkten har inbyggt batteri skriv \"i:\" följt av \"ja\" eller \"nej\".\n");
                menu.append("För att ändra om produkten kan fästas på kamera skriv \"f:\" följt av \"ja\" eller \"nej\".\n");
            }
            case Microphone _ -> {
                menu.append("För att ändra om produkten är trådlös skriv \"t:\" följt av \"ja\" eller \"nej\".\n");
                menu.append("För att ändra ljudutgång skriv \"l:\" följt av ny typ av ljudutgång.\n");
            }
            default -> {
                System.out.println("Klassen finns inte, kontakta support!\n" +
                        "Klicka enter för att gå vidare");
                return;
            }
        }

        menu.append("Avsluta kommandot med 'enter'.\n");
        menu.append("Om du vill gå tillbaka tryck endast 'enter'.\n");

        System.out.println(menu);
    }

    /**
     * Tolkar användarens kommando och skickar vidare till respektive metod där ändringen sker.
     * Beroende på kategori finns fler cases.
     * @param itemToManage Vald produkt som ska ändras
     * @param command det användaren skriver in som ska ändra (så som n: eller l:)
     * @param newChange den nya ändringen
     * @return om objekten förändrades på korrekt sätt returneras true, annars false
     */
    private boolean makeChangesToItem(Item itemToManage, String command, String newChange) {
        switch (command) {
            case "n:" -> {
                return changeItemName(itemToManage, newChange);
            }
            case "m:" -> {
                return changeItemBrand(itemToManage, newChange);
            }
            case "b:" -> {
                return changeItemDescription(itemToManage, newChange);
            }
            case "k:"-> {
                return changeItemDailyRate(itemToManage, newChange);
            }
        }
        switch (itemToManage){
            case Accessory a -> {
                return makeChangesToAccessory(a, command, newChange);
            }
            case Camera c -> {
                return makeChangesToCamera(c, command, newChange);
            }
            case Light l -> {
                return makeChangesToLight(l, command, newChange);
            }
            case Microphone m -> {
                return makeChangesToMicrophone(m, command, newChange);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Tolkar användarens kommando och skickar vidare till respektive metod där ändringen sker.
     * @param itemToManage Vald produkt som ska ändras
     * @param command det användaren skriver in som ska ändra (så som n: eller l:)
     * @param newChange den nya ändringen
     * @return om objekten förändrades på korrekt sätt returneras true, annars false
     */
    private boolean makeChangesToMicrophone(Microphone itemToManage, String command, String newChange) {
        switch (command){
            case "t:" ->{
                return changeMicrophoneWireless(itemToManage, newChange);
            }
            case "l:"->{
                return changeMicrophoneSoundOutput(itemToManage, newChange);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Tolkar användarens kommando och skickar vidare till respektive metod där ändringen sker.
     * @param itemToManage Vald produkt som ska ändras
     * @param command det användaren skriver in som ska ändra (så som n: eller l:)
     * @param newChange den nya ändringen
     * @return om objekten förändrades på korrekt sätt returneras true, annars false
     */
    private boolean makeChangesToLight(Light itemToManage, String command, String newChange) {
        switch (command){
            case "i:" ->{
                return changeLightBuiltInBattery(itemToManage, newChange);
            }
            case "f:"->{
                return changeLightCameraMountable(itemToManage, newChange);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Tolkar användarens kommando och skickar vidare till respektive metod där ändringen sker.
     * @param itemToManage Vald produkt som ska ändras
     * @param command det användaren skriver in som ska ändra (så som n: eller l:)
     * @param newChange den nya ändringen
     * @return om objekten förändrades på korrekt sätt returneras true, annars false
     */
    private boolean makeChangesToCamera(Camera itemToManage, String command, String newChange) {
        switch (command){
            case "a:" ->{
                return changeCameraAutoFocus(itemToManage, newChange);
            }
            case "l:"->{
                return changeCameraSoundInput(itemToManage, newChange);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Tolkar användarens kommando och skickar vidare till respektive metod där ändringen sker.
     * @param itemToManage Vald produkt som ska ändras
     * @param command det användaren skriver in som ska ändra (så som n: eller l:)
     * @param newChange den nya ändringen
     * @return om objekten förändrades på korrekt sätt returneras true, annars false
     */
    private boolean makeChangesToAccessory(Accessory itemToManage, String command, String newChange) {
        switch (command){
            case "a:" -> {
                return changeAccessoryFor(itemToManage, newChange);

            }
            default -> {
                return false;
            }
        }
    }

    private boolean changeMicrophoneWireless(Microphone itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        if(newChange.equalsIgnoreCase("ja")){
            for(Item item : itemsWithName) {
                Microphone microphone = (Microphone) item;
                microphone.setWireless(true);
            }
        }else if(newChange.equalsIgnoreCase("nej")){
            for(Item item : itemsWithName){
                Microphone microphone = (Microphone) item;
                microphone.setWireless(false);
            }
        }else {
            return false;
        }

        System.out.println("Ändrar trådlös.");
        return true;
    }

    private boolean changeMicrophoneSoundOutput(Microphone itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        for(Item item : itemsWithName) {
            Microphone microphone = (Microphone) item;
            microphone.setSoundOutput(newChange);
        }
        System.out.println("Ändrar ljudutgång.");
        return true;
    }

    private boolean changeLightBuiltInBattery(Light itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        if(newChange.equalsIgnoreCase("ja")){
            for(Item item : itemsWithName) {
                Light light = (Light) item;
                light.setBuiltInBattery(true);
            }
        }else if(newChange.equalsIgnoreCase("nej")){
            for(Item item : itemsWithName){
                Light light = (Light) item;
                light.setBuiltInBattery(false);
            }
        }else {
            return false;
        }

        System.out.println("Ändrar inbyggt batteri.");
        return true;
    }

    private boolean changeLightCameraMountable(Light itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        if(newChange.equalsIgnoreCase("ja")){
            for(Item item : itemsWithName) {
                Light light = (Light) item;
                light.setCameraMountable(true);
            }
        }else if(newChange.equalsIgnoreCase("nej")){
            for(Item item : itemsWithName){
                Light light = (Light) item;
                light.setCameraMountable(false);
            }
        }else {
            return false;
        }

        System.out.println("Ändrar kamerafästning.");
        return true;
    }

    private boolean changeCameraAutoFocus(Camera itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        if(newChange.equalsIgnoreCase("ja")){
            for(Item item : itemsWithName) {
                Camera camera = (Camera) item;
                camera.setAutoFocus(true);
            }
        }else if(newChange.equalsIgnoreCase("nej")){
            for(Item item : itemsWithName){
                Camera camera = (Camera) item;
                camera.setAutoFocus(false);
            }
        }else {
            return false;
        }

        System.out.println("Ändrar autofokus.");
        return true;
    }

    private boolean changeCameraSoundInput(Camera itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        for(Item item : itemsWithName) {
            Camera camera = (Camera) item;
            camera.setSoundInput(newChange);
        }
        System.out.println("Ändrar ljudingång.");
        return true;
    }

    private boolean changeAccessoryFor(Accessory itemToManage, String newChange) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        Class<? extends Item> newClass;
        if(newChange.equalsIgnoreCase("kamera")){
            newClass = Camera.class;

        }else if(newChange.equalsIgnoreCase("ljus")){
            newClass = Light.class;
        }else if(newChange.equalsIgnoreCase("mikrofon")){
            newClass = Light.class;
        }else {
            System.out.println("Klass finns inte, se till att du stavat rätt annars kontakta support!");
            return false;
        }
        for(Item item : itemsWithName) {
            Accessory accessory = (Accessory) item;
            accessory.setAccessoryForType(newClass);
        }

        System.out.println("Ändrar accessoar för.");
        return true;
    }

    private boolean changeItemDailyRate(Item itemToManage, String newDailyRateString) {
        try {
            double newDailyRate = Double.parseDouble(newDailyRateString);

            List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());

            for (Item item : itemsWithName) {
                item.setDailyRate(newDailyRate);
            }
            System.out.println("Ändrar märke.");
            return true;
        }catch (NumberFormatException e){
            System.out.println("Fel värde, skriv in ett tal. Använd '.' som decimaltecken.");
            return false;
        }
    }


    private boolean changeItemDescription(Item itemToManage, String newDescription) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        for(Item item : itemsWithName) {
            item.setDescription(newDescription);
        }
        System.out.println("Ändrar beskrivning.");
        return true;
    }

    private boolean changeItemBrand(Item itemToManage, String newBrand) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        for(Item item : itemsWithName) {
            item.setBrand(newBrand);
        }
        System.out.println("Ändrar märke.");
        return true;
    }

    private boolean changeItemName(Item itemToManage, String newName) {
        List<Item> itemsWithName = rentalService.getAllItemsByName(itemToManage.getName());
        for(Item item : itemsWithName) {
            item.setName(newName);
        }
        System.out.println("Ändrar namn.");
        return true;
    }

    /**
     * Huvudmetod för att skapa ny produkt.
     * Användare väljer kategori och baserat på det skickas vidare till andra metoder
     * @return den nyskapde produkten
     */
    private Item createNewItem() {
        boolean choosing = true;
        do {
            System.out.println("""
                    [1] Kameror
                    [2] Ljus
                    [3] Mikrofoner
                    [4] Accessoarer
                    [0] Tillbaka""");
            System.out.println("Vilken kategori tillhör objektet?");
            try {
                int input = getInputIntMenu("Välj kategori produkten ska tillhöra", 4, 0);
                switch (input) {
                    case 1 -> {
                        System.out.println("Skapar ny kamera.");
                        return createNewCamera();
                    }
                    case 2 -> {
                        System.out.println("Skapar nytt ljus.");
                        return createNewLight();
                    }
                    case 3 -> {
                        System.out.println("Skapar ny mikrofon.");
                        return createNewMicrophone();
                    }
                    case 4 -> {
                        System.out.println("Skapar ny accessoar.");
                        return createNewAccessory();
                    }
                    case 0 -> choosing = false;
                    default -> System.out.println("Skriv in ett giltigt alternativ");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while (choosing);
        return null;
    }


    private Accessory createNewAccessory() {
        try {
            String name = getInputNotEmpty("Namn");
            //TODO om namnet är lika föreslå att göra kopia på existerande objekt

            String brand = getInputNotEmpty("Märke");

            String description = getInputNotEmpty("Beskrivning av produkt");

            double dailyRate = getInputDouble("Kostnad/dygn");

            Class<? extends Item> accessoryFor;
            System.out.println("""
                    [1] Kameror
                    [2] Ljus
                    [3] Mikrofoner
                    """);
            switch (getInputIntMenu("Accessoar för",3,1)) {
                case 1 -> accessoryFor = Camera.class;
                case 2 -> accessoryFor = Light.class;
                case 3 -> accessoryFor = Microphone.class;
                default ->
                        throw new IllegalStateException("Unexpected value!");
            }

            return new Accessory(true, dailyRate, name, brand, description, accessoryFor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Microphone createNewMicrophone() {
        try {
            String name = getInputNotEmpty("Namn");
            //TODO om namnet är lika föreslå att göra kopia på existerande objekt

            String brand = getInputNotEmpty("Märke");

            String description = getInputNotEmpty("Beskrivning av produkt");

            double dailyRate = getInputDouble("Kostnad/dygn");

            boolean wireless = getInputYesOrNo("Trådlös");

            String SoundOutput = getInputNotEmpty("Ljudutgång");

            return new Microphone(true, dailyRate, name, brand, description, wireless, SoundOutput);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Light createNewLight() {
        try {
            String name = getInputNotEmpty("Namn");
            //TODO om namnet är lika föreslå att göra kopia på existerande objekt

            String brand = getInputNotEmpty("Märke");

            String description = getInputNotEmpty("Beskrivning av produkt");

            double dailyRate = getInputDouble("Kostnad/dygn");

            boolean builtInBattery = getInputYesOrNo("Trådlös");

            boolean cameraMountable = getInputYesOrNo("Kamerafästning");

            return new Light(true, dailyRate, name, brand, description, builtInBattery, cameraMountable);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Camera createNewCamera() {
        try {
            String name = getInputNotEmpty("Namn");
            //TODO om namnet är lika föreslå att göra kopia på existerande objekt

            String brand = getInputNotEmpty("Märke");

            String description = getInputNotEmpty("Beskrivning av produkt");

            double dailyRate = getInputDouble("Kostnad/dygn");

            boolean autoFocus = getInputYesOrNo("Autofokus");

            String soundInput = getInputNotEmpty("Ljudingång");

            return new Camera(true, dailyRate, name, brand, description, autoFocus, soundInput);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Hantera alla medlemmar, kan välja en medlem att gå in och ändra, kan sortera och filtrera listan
     */
    private Member chooseMember(String whatToDoWithMember) {
        //memberComparator bestämmer hur members ska skrivas ut
        //pricePolicyFilter filtrerar enligt vald PricePolicy klass (PricePolicy.class inkluderas alla medlemmar som har en level)
        Comparator<Member> memberComparator = membershipService.getMemberRegistry().getDefaultComparator();
        Class<? extends PricePolicy> pricePolicyFilter = PricePolicy.class;
        String searchName = "";

        while (true) {
            //Print alla medlemmar
            membershipService.printMembers(memberComparator, pricePolicyFilter, searchName);

            //Ska kunna filtrera enligt level och sortera enligt namn, och id
            System.out.println("För att " + whatToDoWithMember + " en medlem skriv dess id följt av 'enter'.");
            System.out.println("""
                    
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
                return null;
            }

            if (Character.isDigit(input.charAt(0))){
                try {
                    Member memberToManage = membershipService.findMemberById(input);
                    return memberToManage;
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            //Sortering och filtrering
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

                changeMemberName(memberToManage, newName);
            }
            else if(input.toLowerCase().startsWith("l:")) {
                String newLevel = input.substring(input.indexOf("l:") + 2);
                changeMemberLevel(memberToManage, newLevel);
            }
        }
    }


    /**
     * Tar bort medlem från medlemsregister
     */
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

            try {
                boolean input = getInputYesOrNo("Vill du ändra level från " + memberToManage.getLevel() + " till " + level);

                if (input) {
                    membershipService.changeMemberLevel(memberToManage, level);
                    System.out.println("Level ändrat till " + level + ".");
                } else {
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
               try {
            boolean input = getInputYesOrNo("Vill du ändra namnet från " + memberToManage.getName() + " till " + newName);

            if (input) {
                membershipService.changeMemberName(memberToManage, newName);
            } else {
                System.out.println("Namn ej ändrat.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Skapa en ny medlem till registret
    public Member createNewMember(){
        String name;
        try {
            name = getInputNotEmpty("Namn");
            
            System.out.println("Vilken level har medlemmen?");
            PricePolicy pricePolicy =null;
            boolean choosing = true;
            do {
                System.out.println("""
                        [1] Standard
                        [2] Premium
                        [3] Student""");
                int input = getInputIntMenu("Vilken level har medlemmen", 3, 1);
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
            } while (choosing);

            return new Member(name, pricePolicy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Separerar strängen så att endast det svar som gäller given prefix skickas tillbaka
    private String getSectionFor(String entireString, String prefix) {
        entireString = entireString.concat(" ");
        return entireString.substring(entireString.indexOf(prefix) + prefix.length(), entireString.indexOf(" ", entireString.indexOf(prefix)));
    }
}
