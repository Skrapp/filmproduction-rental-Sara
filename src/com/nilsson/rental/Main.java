package com.nilsson.rental;

import com.nilsson.rental.entity.*;
import com.nilsson.rental.entity.items.*;
import com.nilsson.rental.entity.pricepolicy.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

        //Mockup data
        Member m1 = new Member("Lova", new Premium());
        Member m2 = new Member("Clara", new Standard());
        Member m3 = new Member("Simon", new Student());
        Member m4 = new Member("Örjan", new Premium());
        Member m5 = new Member("Gurra", new Standard());
        Member m6 = new Member("Jocke", new Student());

        Accessory tripod1 = new Accessory(true,
                100,
                "U-Vlog Lite Extendable Tripod 2109",
                "Ulanzi",
                "Kompakt och portabel resestativ",
                Camera.class);
        Accessory tripod2 = new Accessory(true,
                200,
                "KH26PC Video Tripod Kit",
                "Benro",
                "Stabilt och högt videostativ för video- och systemkamera",
                Camera.class);
        Accessory tripod3 = new Accessory(true,
                200,
                "KH26PC Video Tripod Kit",
                "Benro",
                "Stabilt och högt videostativ för video- och systemkamera",
                Camera.class);
        Accessory battery1 = new Accessory(true,
                50,
                "LP-E6NH 2130mAh Canon batteri",
                "Jupio",
                "Kamerabatteri med extra kapacitet, LP-E6NH till Canon",
                Camera.class);
        Accessory battery2 = new Accessory(true,
                50,
                "LP-E6NH 2130mAh Canon batteri",
                "Jupio",
                "Kamerabatteri med extra kapacitet, LP-E6NH till Canon",
                Camera.class);
        Accessory battery3 = new Accessory(true,
                100,
                "LP-E6NH Duo charger Canon",
                "Jupio",
                "Dubbelladdare till LP-E6NH batteri",
                Camera.class);
        Accessory rainprotector1 = new Accessory(true,
                100,
                "Regnskydd Medium DSLR (70-200/2.8)",
                "Think Tank",
                "Regnskydd till systemkamera med monterat 70–200mm f/2.8",
                Camera.class);
        Accessory soundSyncroniser = new Accessory(true,
                150,
                "Sync E multikit",
                "Tentacle",
                "Enkel synkronisering av bild och ljud för din nästa multicam-video",
                Sound.class);
        Camera camera1 = new Camera(true,
                500,
                "XA70",
                "Canon",
                "Professionell och smidig 4K UHD-videokamera med exakt fokusering",
                true, false, 4000, "XLR");
        Camera camera2 = new Camera(true,
                500,
                "XA70",
                "Canon",
                "Professionell och smidig 4K UHD-videokamera med exakt fokusering",
                true, false, 4000, "XLR");
        Camera camera3 = new Camera(true,
                400,
                "Q8n-4K Handy Video Recorder",
                "Zoom",
                "Allt-i-ett videokamera med 4K-upplösning och stereomikrofon",
                true, true, 4000, "XLR");
        Camera camera4 = new Camera(true,
                500,
                "Legria HF G70",
                "Canon",
                "Handhållen 4K-videokamera med 5-axlig bildstabilisering",
                true, true, 4000, "3,5mm");
        Light light1 = new Light(true,
                150,
                "LT002 7\" RGB LED Video Light B01002",
                "Ulanzi",
                "Kraftfull RGB-lampa med olika ljuseffekter för mobil och kamera",
                true, true, 800,
                14, 7.8, 1.6);
        Light light2 = new Light(true,
                300,
                "SL100D LED Kit",
                "Godox",
                "Modernt LED-paket för foto och video",
                false, false, 32100,
                12.7, 20.5, 24.3);
        Light light3 = new Light(true,
                300,
                "SL100D LED Kit",
                "Godox",
                "Modernt LED-paket för foto och video",
                false, false, 32100,
                12.7, 20.5, 24.3);
        Light light4 = new Light(true,
                300,
                "SL100D LED Kit",
                "Godox",
                "Modernt LED-paket för foto och video",
                false, false, 32100,
                12.7, 20.5, 24.3);
        Memory memory1 = new Memory(true,
                100,
                "SDXC Pro 1800X 128GB 280MB/S UHS-II U3 V60",
                "Lexar",
                "Snabbt minneskort för video och bildserier, 128GB",
                128);
        Memory memory2 = new Memory(true,
                100,
                "SL500 2000/1800MB/s 2TB",
                "Lexar",
                "Liten och kompakt SSD med 2TB lagringsutrymme",
                2000);
        Microphone microphone1 = new Microphone(true,
                200,
                "Mic Mini (2 TX + 1 RX + Charging Case)",
                "DJI",
                "Trådlös mikrofon i ultralätt format för professionell ljudinspelning",
                "3,5mm",
                true);
        Microphone microphone2 = new Microphone(true,
                200,
                "Mic Mini (2 TX + 1 RX + Charging Case)",
                "DJI",
                "Trådlös mikrofon i ultralätt format för professionell ljudinspelning",
                "3,5mm",
                true);
        Microphone microphone3 = new Microphone(true,
                200,
                "VideoMic NTG",
                "Røde",
                "Riktad shotgun-mikrofon med kameramontering",
                "3,5mm",
                false);
        Sound soundcard1 = new Sound(true,
                200,
                "engineering TX-6 mixer Black",
                "teenage",
                "Portabel mixer, ljudkort och sequencer med inbyggda effekter");
        Headphone headphone1 = new Headphone(true,
                100,
                "HD 600",
                "Sennheiser",
                "Öppna studiohörlurar med renodlat stereoljud",
                "3,5mm");
        Headphone headphone2 = new Headphone(true,
                100,
                "MDR-7506/1",
                "Sony",
                "Komfortabla och funktionella hörlurar för professionellt bruk",
                "3,5mm");
        Headphone headphone3 = new Headphone(true,
                100,
                "HD 650",
                "Sennheiser",
                "Öppna studiohörlurar med mjuk diskant och varm bas",
                "3,5mm");

        KonsolMenu konsolMenu = new KonsolMenu(new BufferedReader(new InputStreamReader(System.in)));
        konsolMenu.getMembershipService().addMember(m1);
        konsolMenu.getMembershipService().addMember(m2);
        konsolMenu.getMembershipService().addMember(m3);
        konsolMenu.getMembershipService().addMember(m4);
        konsolMenu.getMembershipService().addMember(m5);
        konsolMenu.getMembershipService().addMember(m6);

        konsolMenu.mainMenu();

    }
}