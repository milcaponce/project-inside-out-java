package dev.milca.mvc.controllers;

import java.time.LocalDate;
import java.util.Scanner;

import dev.milca.Repositories.MomentRepository;
import dev.milca.mvc.model.EmotionEnum;
import dev.milca.mvc.model.Moment;

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
                    System.out.println("Funcionalidad de añadir momento pendiente.");
                    break;
                case "2":
                    System.out.println("Funcionalidad de listar momentos pendiente.");
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
}

