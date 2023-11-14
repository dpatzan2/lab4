package com.biblioteca.bean;

import java.util.ArrayList;

public class InfoPrestamo {
    private User user;
    private ArrayList<ResourcesLibrary> loanedResources;
    private int loanDays;
    private String deliveryTime;
    private String pickupBranch;
    private String deliveryAddress;
    private boolean isPremium;

    public InfoPrestamo(User user, ArrayList<ResourcesLibrary> loanedResources, int loanDays, String deliveryTime, String pickupBranch, String deliveryAddress, boolean isPremium) {
        this.user = user;
        this.loanedResources = loanedResources;
        this.loanDays = loanDays;
        this.deliveryTime = deliveryTime;
        this.pickupBranch = pickupBranch;
        this.deliveryAddress = deliveryAddress;
        this.isPremium = isPremium;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<ResourcesLibrary> getLoanedResources() {
        return loanedResources;
    }

    public int getLoanDays() {
        return loanDays;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getPickupBranch() {
        return pickupBranch;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
