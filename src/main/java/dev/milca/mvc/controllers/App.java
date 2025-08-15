package dev.milca.mvc.controllers;

import dev.milca.mvc.model.Moment;
import dev.milca.mvc.model.EmotionEnum;
import dev.milca.Repositories.MomentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public final class App {
    private final MomentRepository momentRepository;
    private final Scanner scanner;

    public App() {
        this.momentRepository = new MomentRepository();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addMoment();
                    break;
                case "2":
                    listMoments();
                    break;
                case "3":
                    System.out.println("Funcionalidad de eliminar momento pendiente.");
                    break;
                case "4":
                    System.out.println("Funcionalidad de filtrar pendiente.");
                    break;
                case "5":
                    System.out.println("Gracias por usar Mi Diario! Hasta luego!");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println(" MI DIARIO ");
        System.out.println("1. Añadir nuevo momento");
        System.out.println("2. Ver todos los momentos");
        System.out.println("3. Eliminar un momento");
        System.out.println("4. Filtrar por emoción");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    //Añadir nuevo momento
    private void addMoment() {
        System.out.println("AÑADIR NUEVO MOMENTO");

        String title = "";
        do {
            System.out.print("Título (no puede estar vacío): ");
            title = scanner.nextLine().trim();
        } while (title.isEmpty());

        //Capturar descripción
        String description = "";
        do {
            System.out.print("Description (no puede estar vacía): ");
            description = scanner.nextLine().trim();
        } while (description.isEmpty());

        //Capturar emoción
        EmotionEnum emotion = null;
        while (emotion == null) {
            System.out.print("Emoción (ALEGRIA, TRISTEZA, ENOJO, CALMA, MIEDO, ASCO): ");
            String emotionInput = scanner.nextLine().trim().toUpperCase();
            try {
                emotion = EmotionEnum.valueOf(emotionInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Emoción no válida. Por favor, elija una de la lista. ");
            }
        }

        //Capturar fecha
        LocalDate date = null;
        while (date == null) {
            System.out.print("Fecha del momento (YYYY-MM-DD): ");
            String dateString = scanner.nextLine();
            try {
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Por favor, use YYYY-MM-DD.");
            }
        }

        Moment newMoment = new Moment(0, title, description, emotion, date);
        momentRepository.addMoment(newMoment);

        System.out.println("El momento ha sido guardado correctamente.");
    }

    private void listMoments() {
        System.out.println("VER TODOS LOS MOMENTOS");
        List<Moment> allMoments = momentRepository.getMoments();

        if (allMoments.isEmpty()) {
            System.out.println("Actualmente no hay momentos guardados. ¡Empieza a escribir!");
        } else {
            for (Moment moment : allMoments) {
                System.out.println("--------------------");
                System.out.println("ID: " + moment.getId());
                System.out.println("Título: " + moment.getTitle());
                System.out.println("Emoción: " + moment.getEmotion());
                System.out.println("Fecha: " + moment.getDate());
                System.out.println("Resumen: " + moment.getDescription());
            }
            System.out.println("--------------------");
        }
    }
}


