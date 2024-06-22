package coltec.leandro.restaurantsapp;

public class Restaurante {
    public String name;
    public Double nota;
    public String adress;
    public String telephone;
    public String category;
    public Boolean isFavorite;

    public Restaurante(String name, Double nota, String adress, String telephone,String category) {
        this.name = name;
        this.nota = nota;
        this.adress = adress;
        this.telephone = telephone;
        this.category = category;
        this.isFavorite = false;
    }

}
