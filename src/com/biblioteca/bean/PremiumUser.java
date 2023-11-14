package com.biblioteca.bean;

import java.util.ArrayList;

public class PremiumUser extends User {


    private static final boolean isPremium = false;

    public PremiumUser(String name, String password, String user, ArrayList<ResourcesLibrary> loan) {
        super(name, password, user, loan, isPremium);

    }

    @Override
    public void login() {

    }

    @Override
    public void register() {

    }

    @Override
    public void changePassword() {
        
    }
}
