package org.javabasics.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import org.javabasics.service.Service;
import org.javabasics.model.User;

public class Controller {

    private Service service;

    public Controller(){
        service = Service.getInstance();
    }

    public void start(){
        Scanner sc = new Scanner(System.in);

        Integer instructionNumber = 0;

        do{
            printInstructions();
            instructionNumber = readIntInput(sc);
            
            if(instructionNumber == null)
                continue;
            
            String result;
            
            switch(instructionNumber){
                case 1:{
                    result = service.showMagazine();
                    break;
                }
                case 2:{
                    System.out.println("Inserisci id rivista");
                    int magazineId = readIntInput(sc);
                    System.out.println("Inserisci id utente");
                    int userId = readIntInput(sc);
                    result = service.addSubscrition(magazineId, userId);
                    break;
                }
                case 3:{
                    System.out.println("Inserisci id abbonamento da disdire");
                    int idSub = readIntInput(sc);
                    result = service.removeSubscrition(idSub);
                    break;
                }
                case 4:{
                    User newUser = readUser(sc);
                    result = service.insertUser(newUser);
                    break;
                }
                case 5:{
                    result = service.exportFreeMagazine();
                    break;
                }
                case 6:{
                    result = service.showUser();
                    break;
                }
                case 7:{
                    result = service.showSubscription();
                    break;
                }
                case 0:{
                    result = "ARRIVEDERCI";
                    break;
                }
                default:{
                    result = "comando non valido";
                }
            }

            System.out.println(result);
            
        } while(instructionNumber != 0);
    }

    public void printInstructions(){
        System.out.println("inserisci il numero dell'operazione da eseguire");
        System.out.println("1: Visualizza tutte le riviste");
        System.out.println("2: Abbonati a una rivista");
        System.out.println("3: Disdici un abbonamento");
        System.out.println("4: Aggiungi un nuovo utente");
        System.out.println("5: esporta le riviste disponibili su file");
        System.out.println("6: Visualizza tutti gli utenti");
        System.out.println("7: Visualizza tutti gli abbonamenti");
        System.out.println("0: ESCI");
    }

    public User readUser(Scanner sc){

        System.out.println("Inserisci l'ID: ");
        int id = readIntInput(sc);
        
        System.out.println("Inserisci il nome: ");
        String nome = readStringInput(sc);
        
        System.out.println("Inserisci il cognome: ");
        String cognome = readStringInput(sc);
        
        System.out.println("Inserisci la data di nascita: ");
        LocalDate dataDiNascita = readDate(sc);
        
        System.out.println("Inserisci l'indirizzo: ");
        String indirizzo = readStringInput(sc);
        
        System.out.println("inserisci il documento ID");
        String documentoId = readStringInput(sc);
        
        return new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId);
    }

    public String readStringInput(Scanner sc){
        String input;
        do{
            try{
                input = sc.nextLine();
            }
            catch(IllegalArgumentException e){
                System.out.println("ERRORE: inserire una stringa");
                return null;
            }
        } while(input == null);
        return input;
    }

    public LocalDate readDate(Scanner sc){
        LocalDate date = null;
        do{ 
            try{
                String input = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(input, formatter);
            }
            catch(DateTimeParseException e){
                System.out.println("ERRORE: inserire una data del tipo dd/MM/yyyy");
            }
        } while(date == null);
        return date;
    }

    public Integer readIntInput(Scanner sc){
        Integer input = null;
        do{
            try{
                input = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("ERRORE: inserire un numero intero");
            }
        } while(input == null);
        return input;
    }

}
