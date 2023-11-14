package com.biblioteca.controller;

import com.biblioteca.bean.InfoPrestamo;
import com.biblioteca.bean.ResourcesLibrary;
import com.biblioteca.bean.User;

import java.util.ArrayList;

public class InfoPrestamoManager {
    private static ArrayList<InfoPrestamo> loans = new ArrayList<InfoPrestamo>();

    public static void processLoan(User user, ArrayList<ResourcesLibrary> selectedResources, int loanDays, String deliveryTime, String pickupBranch, String deliveryAddress, boolean premium) {
        // Verifica restricciones según el tipo de usuario (premium o base)
        // Procesa el préstamo y registra la información
        InfoPrestamo newLoan = new InfoPrestamo(user, selectedResources, loanDays, deliveryTime, pickupBranch, deliveryAddress, premium);
        loans.add(newLoan);
        // Guarda la información en un archivo CSV
        CSVController.exportLoans(loans);
    }

    public static void addLoan(InfoPrestamo loan) {
        loans.add(loan);
    }

    // Método para obtener la lista de préstamos
    public static ArrayList<InfoPrestamo> getLoans() {
        return loans;
    }

}
