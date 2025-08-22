package dev.milca.mvc.controllers;

import dev.milca.mvc.model.Moment;
import dev.milca.mvc.model.EmotionEnum;
import dev.milca.mvc.model.MomentTypeEnum;
import dev.milca.Repositories.MomentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class HomeController {

    private final MomentRepository momentRepository;
    private final Scanner scanner;

    public HomeController(MomentRepository momentRepository, Scanner scanner) {
        this.momentRepository = momentRepository;
        this.scanner = scanner;
    }


    public void displayMenu() {
        System.out.println("\n--- Mi Diario ---");
        System.out.println("1. Agregar un momento");
        System.out.println("2. Listar todos los momentos");
        System.out.println("3. Filtrar momentos por emoción");
        System.out.println("4. Filtrar momentos por tipo (bueno/malo)"); // Nueva opción
        System.out.println("5. Eliminar un momento");
        System.out.println("6. Salir");
    }


    public void processOption(int option) {
        switch (option) {
            case 1:
                this.addMoment();
                break;
            case 2:
                this.listAllMoments();
                break;
            case 3:
                this.filterMomentsByEmotion();
                break;
            case 4:
                this.filterMomentsByType(); // Llamar al nuevo método
                break;
            case 5:
                this.deleteMoment();
                break;
            case 6:
                System.out.println("Saliendo del diario. ¡Hasta la próxima!");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elige una opción del 1 al 6.");
                break;
        }
    }


    private void addMoment() {
        System.out.println("Ingresa el título del momento:");
        String title = scanner.nextLine();
        
        System.out.println("Ingresa la descripción del momento:");
        String description = scanner.nextLine();
        
        System.out.println("¿Qué emoción te provocó? (ALEGRIA, TRISTEZA, IRA, MIEDO, ASCO)");
        EmotionEnum emotion = EmotionEnum.valueOf(scanner.nextLine().toUpperCase());
        
        // Pedir al usuario que especifique el tipo de momento
        System.out.println("¿Es este un momento bueno o malo? (BUENO/MALO)");
        MomentTypeEnum momentType = MomentTypeEnum.valueOf(scanner.nextLine().toUpperCase());
        
        System.out.println("Ingresa la fecha del momento (YYYY-MM-DD):");
        LocalDate momentDate = LocalDate.parse(scanner.nextLine());
        
        Moment newMoment = new Moment(title, description, emotion, momentType, momentDate);
        momentRepository.addMoment(newMoment);
        System.out.println("¡Momento agregado con éxito!");
    }


    private void listAllMoments() {
        List<Moment> moments = momentRepository.getMoments();
        if (moments.isEmpty()) {
            System.out.println("El diario está vacío. ¡Agrega tu primer momento!");
        } else {
            System.out.println("\n--- Momentos del Diario ---");
            for (Moment moment : moments) {
                System.out.println(moment);
            }
        }
    }


    private void deleteMoment() {
        System.out.println("Ingresa el ID del momento a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        boolean wasDeleted = momentRepository.deleteMomentById(id);
        if (wasDeleted) {
            System.out.println("Momento eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún momento con ese ID.");
        }
    }
    

    private void filterMomentsByEmotion() {
        System.out.println("Ingresa la emoción por la que deseas filtrar (ALEGRIA, TRISTEZA, IRA, MIEDO, ASCO):");
        String emotion = scanner.nextLine();
        List<Moment> filteredMoments = momentRepository.filterMomentsByEmotion(emotion);
        if (filteredMoments.isEmpty()) {
            System.out.println("No se encontraron momentos con esa emoción.");
        } else {
            System.out.println("\n--- Momentos filtrados por " + emotion + " ---");
            for (Moment moment : filteredMoments) {
                System.out.println(moment);
            }
        }
    }
    

    private void filterMomentsByType() {
        System.out.println("¿Qué tipo de momento quieres ver? (BUENO/MALO)");
        String type = scanner.nextLine().toUpperCase();
        try {
            MomentTypeEnum momentType = MomentTypeEnum.valueOf(type);
            List<Moment> filteredMoments = momentRepository.filterMomentsByType(momentType);
            if (filteredMoments.isEmpty()) {
                System.out.println("No se encontraron momentos de ese tipo.");
            } else {
                System.out.println("\n--- Momentos filtrados por tipo " + momentType + " ---");
                for (Moment moment : filteredMoments) {
                    System.out.println(moment);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de momento no válido. Por favor, elige BUENO o MALO.");
        }
    }
}