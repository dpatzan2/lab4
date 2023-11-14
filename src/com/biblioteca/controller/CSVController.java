package com.biblioteca.controller;

import com.biblioteca.bean.InfoPrestamo;
import com.biblioteca.bean.User;
import com.biblioteca.inter.ArchiveInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVController implements ArchiveInterface {
    private static final String USERS_CSV_FILE = "users.csv";
    private static final String LOANS_CSV_FILE = "loans.csv";

    @Override
    public void read() {
        // Implementa la lógica de lectura si es necesario
    }

    @Override
    public void export() {
        exportUsers();
        exportLoans(InfoPrestamoManager.getLoans());
    }

    public static void exportUsers() {
        try (FileWriter writer = new FileWriter(USERS_CSV_FILE)) {
            // Escribir encabezados al archivo CSV
            writer.append("Name,Password,UserID,IsPremium\n");

            // Obtener la lista de usuarios de alguna parte de tu aplicación
            ArrayList<User> users = UserManager.getUsers();

            // Escribir cada usuario al archivo CSV
            for (User user : users) {
                writer.append(user.getName()).append(",");
                writer.append(user.getPassword()).append(",");
                writer.append(user.getID()).append(",");
                writer.append(String.valueOf(user.isPremium())).append("\n");
            }

            System.out.println("Los usuarios han sido exportados exitosamente al archivo " + USERS_CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error al exportar usuarios al archivo CSV: " + e.getMessage());
        }
    }

    public static void exportLoans(ArrayList<InfoPrestamo> loans) {
        try (FileWriter writer = new FileWriter(LOANS_CSV_FILE)) {
            // Escribir encabezados al archivo CSV
            writer.append("Username,ResourceName,LoanDays,DeliveryTime,PickupBranch,DeliveryAddress,IsPremium\n");

            // Escribir cada préstamo al archivo CSV
            for (InfoPrestamo loan : loans) {
                writer.append(loan.getUser().getID()).append(",");
                writer.append(loan.getLoanedResources().get(0).getName()).append(","); // Suponiendo un solo recurso por préstamo
                writer.append(String.valueOf(loan.getLoanDays())).append(",");
                writer.append(loan.getDeliveryTime()).append(",");
                writer.append(loan.getPickupBranch()).append(",");
                writer.append(loan.getDeliveryAddress()).append(",");
                writer.append(String.valueOf(loan.isPremium())).append("\n");
            }

            System.out.println("Los préstamos han sido exportados exitosamente al archivo " + LOANS_CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error al exportar préstamos al archivo CSV: " + e.getMessage());
        }
    }
}
