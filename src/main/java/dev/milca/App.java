package dev.milca;

import dev.milca.mvc.controllers.HomeController;
import dev.milca.Repositories.MomentRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MomentRepository momentRepository = new MomentRepository();
        Scanner scanner = new Scanner(System.in);
        HomeController homeController = new HomeController(momentRepository, scanner);

        int option = 0;
        do {
            try {
                homeController.displayMenu();
                System.out.print("Elige una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); 

                homeController.processOption(option);
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }

        } while (option != 5);
        scanner.close();
    }
}
