package coltec.leandro.restaurantsapp;


import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> getRestaurantCategory(String category);
    List<Restaurante> getFavoriteRestaurants();

    void addRestaurante(Restaurante restaurante);

}
