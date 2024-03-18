package org.example;

public class Profile {

    public Profile(String nameProfile, int amountEspresso, int amountCappuccino) {
        setNameProfile(nameProfile);
        setAmountEspresso(amountEspresso);
        setAmountCappuccino(amountCappuccino);
    }

    private String nameProfile = "";
    private int amountEspresso = 0;
    private int amountCappuccino = 0;

    public String getNameProfile() {
        return nameProfile;
    }
    public int getAmountEspresso() {
        return amountEspresso;
    }
    public int getAmountCappuccino() {
        return amountCappuccino;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }
    public void setAmountEspresso(int amountEspresso) {
        this.amountEspresso = amountEspresso;
    }
    public void setAmountCappuccino(int amountCappuccino) {
        this.amountCappuccino = amountCappuccino;
    }

    public void fdf(String nameProfile, int amountEspresso, int amountCappuccino) {

    }
}
