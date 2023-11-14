package com.biblioteca.view;

import com.biblioteca.bean.BaseUser;
import com.biblioteca.bean.PremiumUser;
import com.biblioteca.bean.ResourcesLibrary;
import com.biblioteca.bean.User;
import com.biblioteca.controller.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static User currentUser;
    private static ArrayList<ResourcesLibrary> selectedResources = new ArrayList<>();

    public static void ShowMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginOrLogout(scanner);
                    break;
                case 3:
                    selectionMode(scanner);
                    break;
                case 4:
                    loanMode(scanner);
                    break;
                case 5:
                    profileMode(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("1. Registro");
        System.out.println("2. Ingresar/Salir");
        System.out.println("3. Modo Selección");
        System.out.println("4. Modo Préstamo");
        System.out.println("5. Modo Perfil");
        System.out.println("0. Salir");
        System.out.println("Seleccione una opción:");
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("Ingrese su nombre:");
        String name = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        System.out.println("Ingrese su usuario:");
        String username = scanner.nextLine();

        System.out.println("¿Desea ser usuario premium? (Sí/No):");
        boolean isPremium = scanner.nextLine().equalsIgnoreCase("Sí");

        currentUser = isPremium ? new PremiumUser(name, password, username, new ArrayList<>()) :
                new BaseUser(name, password, username, new ArrayList<>());

        System.out.println("Registro exitoso.");
    }

    private static void loginOrLogout(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Ingrese su usuario:");
            String username = scanner.nextLine();

            System.out.println("Ingrese su contraseña:");
            String password = scanner.nextLine();

            currentUser = UserManager.authenticateUser(username, password);

            if (currentUser != null) {
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } else {
            currentUser = null;
            System.out.println("Cierre de sesión exitoso.");
        }
    }

    private static void selectionMode(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Debe iniciar sesión para acceder a esta opción.");
            return;
        }

        while (true) {
            System.out.println("1. Agregar libro");
            System.out.println("2. Agregar revista");
            System.out.println("3. Vaciar lista");
            System.out.println("0. Volver al menú principal");
            System.out.println("Seleccione una opción:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    addMagazine(scanner);
                    break;
                case 3:
                    clearList();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Ingrese el nombre del libro:");
        String bookName = scanner.nextLine();

        System.out.println("Ingrese el ISBN del libro:");
        String bookISBN = scanner.nextLine();

        System.out.println("Ingrese el tipo de libro:");
        String bookType = scanner.nextLine();

        System.out.println("Ingrese el precio del libro:");
        double bookPrice = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea después de nextDouble

        ResourcesLibrary newBook = new ResourcesLibrary(bookName, bookISBN, bookType, bookPrice);

        // Verifica las restricciones según el tipo de usuario
        if (currentUser.isPremium() && selectedResources.size() < 5 || !currentUser.isPremium() && selectedResources.size() < 3) {
            selectedResources.add(newBook);
            System.out.println("Libro agregado exitosamente.");
        } else {
            System.out.println("No puede agregar más libros. Ha alcanzado el límite de préstamos.");
        }
    }

    private static void addMagazine(Scanner scanner) {
        System.out.println("Ingrese el nombre de la revista:");
        String magazineName = scanner.nextLine();

        System.out.println("Ingrese el ISBN de la revista:");
        String magazineISBN = scanner.nextLine();

        System.out.println("Ingrese el tipo de revista:");
        String magazineType = scanner.nextLine();

        System.out.println("Ingrese el precio de la revista:");
        double magazinePrice = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea después de nextDouble

        ResourcesLibrary newMagazine = new ResourcesLibrary(magazineName, magazineISBN, magazineType, magazinePrice);

        // Verifica las restricciones según el tipo de usuario
        selectedResources.add(newMagazine);
        System.out.println("Revista agregada exitosamente.");
    }

    private static void clearList() {
        selectedResources.clear();
        System.out.println("Lista vaciada exitosamente.");
    }

    private static void loanMode(Scanner scanner) {
        if (selectedResources.isEmpty()) {
            System.out.println("Debe seleccionar al menos un libro o revista antes de realizar un préstamo.");
            return;
        }

        System.out.println("Ingrese la cantidad de días para el préstamo:");
        int loanDays = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después de nextInt

        if (currentUser.isPremium() && loanDays > 50 || !currentUser.isPremium() && loanDays > 30) {
            System.out.println("El plazo de préstamo excede el límite permitido.");
            return;
        }

        System.out.println("Seleccione el horario de entrega (AM/PM):");
        String deliveryTime = scanner.nextLine();

        if (currentUser.isPremium()) {
            System.out.println("Ingrese la dirección de envío:");
            String deliveryAddress = scanner.nextLine();
            // Lógica adicional para el modo préstamo premium
        } else {
            System.out.println("Seleccione la sucursal para recoger el préstamo:");
            String pickupBranch = scanner.nextLine();
            // Lógica adicional para el modo préstamo no premium
        }

        // Lógica para imprimir listado de préstamo
    }

    private static void profileMode(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Debe iniciar sesión para acceder al modo perfil.");
            return;
        }

        System.out.println("1. Modificar tipo de cliente");
        System.out.println("2. Aplicar cupón de 15 días adicionales");
        System.out.println("3. Cambiar contraseña");
        System.out.println("0. Volver al menú principal");
        System.out.println("Seleccione una opción:");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después de nextInt

        switch (choice) {
            case 1:
                if (!currentUser.isPremium()) {
                    System.out.println("¿Desea cambiar a un plan premium? (Sí/No):");
                    boolean changeToPremium = scanner.nextLine().equalsIgnoreCase("Sí");
                    if (changeToPremium) {
                        currentUser.setPremium(true);
                        System.out.println("Cambio a plan premium exitoso.");
                    } else {
                        System.out.println("Permanece en el plan base.");
                    }
                } else {
                    System.out.println("Ya está en el plan premium.");
                }
                break;
            case 2:
                if (currentUser.isPremium()) {
                    System.out.println("Ingrese el cupón de 15 días adicionales:");
                    String couponCode = scanner.nextLine();
                    // Lógica para aplicar el cupón (agregar días adicionales al plazo de préstamo)
                    System.out.println("Cupón aplicado exitosamente.");
                } else {
                    System.out.println("Esta opción es solo para usuarios premium.");
                }
                break;
            case 3:
                System.out.println("Ingrese la nueva contraseña:");
                String newPassword = scanner.nextLine();
                currentUser.setPassword(newPassword);
                System.out.println("Contraseña cambiada exitosamente.");
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }
}
