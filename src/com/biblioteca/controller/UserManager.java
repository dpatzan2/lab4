package com.biblioteca.controller;

import com.biblioteca.bean.BaseUser;
import com.biblioteca.bean.PremiumUser;
import com.biblioteca.bean.ResourcesLibrary;
import com.biblioteca.bean.User;

import java.util.ArrayList;

public class UserManager {
    private static ArrayList<User> users = new ArrayList<>();

    public static void registerUser(String name, String password, String user, ArrayList<ResourcesLibrary> loan, boolean isPremium, String plan) {
        // Crea una instancia de BaseUser o PremiumUser según el plan
        User newUser = isPremium ? new PremiumUser(name, password, user, loan) : new BaseUser(name, password, user, loan);
        users.add(newUser);
        // Guarda la información en un archivo CSV
        CSVController.exportUsers();
    }

    public static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Usuario no encontrado o contraseña incorrecta
    }


    // Supongamos que tienes un método para agregar usuarios
    public static void addUser(User user) {
        users.add(user);
    }

    // Método para obtener la lista de usuarios
    public static ArrayList<User> getUsers() {
        return users;
    }
}
