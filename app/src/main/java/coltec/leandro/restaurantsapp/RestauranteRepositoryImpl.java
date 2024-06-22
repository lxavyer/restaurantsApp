package coltec.leandro.restaurantsapp;

import java.util.List;
import java.util.ArrayList;

public class RestauranteRepositoryImpl implements RestauranteRepository {

        private static RestauranteRepositoryImpl repository;
        private static List<Restaurante> restaurantesList;

        private RestauranteRepositoryImpl() { // iniciando o repositorio
            restaurantesList = new ArrayList<>();
            initRestauranteList();

        }

        public void initRestauranteList() {

            // adicionando restaurantes de cada categoria para começarem preenchidos
            restaurantesList.add(new  Restaurante("Dragon Center", 4.5,"R. Fernandes Tourinho, 455 - Savassi, Belo Horizonte - MG, 30112-000",
                    "(31) 3287-3688", "Chinesa"));
            restaurantesList.add(new  Restaurante("Restaurante Casa da China", 4.3,"Av. Barbacena, 1009 - Santo Agostinho, Belo Horizonte - MG, 30190-131",
                    "(31) 3337-1999", "Chinesa"));
            restaurantesList.add(new  Restaurante("China in Box - Savassi", 4.0," Av. do Contorno, 7258 - Lourdes, Belo Horizonte - MG, 30110-035",
                    "(31) 3292-3007", "Chinesa"));
            restaurantesList.add(new  Restaurante("Restaurante A Grande Muralha", 4.5,"R. Santa Catarina, 781 - Lourdes, Belo Horizonte - MG, 30170-080",
                    "(31) 3291-5060", "Chinesa"));

            restaurantesList.add(new  Restaurante("Fogão vermelho - corredor D2", 4.5," Av. Olegário Maciel, 742 Mercado Novo 2º Piso - Final do Corredor D2 - Centro, Belo Horizonte - MG, 30180-916",
                    "(31) 4002-8922", "Arabe"));
            restaurantesList.add(new  Restaurante("Baity Delícias Árabes", 4.9,"Av. Augusto de Lima, 744 - Corredor do artesanato- Loja 161K - Centro, MG, 30190-922",
                    "(31) 98844-3200", "Arabe"));
            restaurantesList.add(new  Restaurante("Restaurante Vila Árabe", 4.9,"R. Pernambuco, 781 - Savassi, Belo Horizonte - MG, 30130-151",
                    "(31) 3262-1600", "Arabe"));
            restaurantesList.add(new  Restaurante("Armazém do Árabe Restaurante Árabe", 4.9,"R. Luz, 230 - Serra, Belo Horizonte - MG, 30220-080",
                    "(31) 3223-1410", "Arabe"));


            restaurantesList.add(new  Restaurante("Baby Beef - Cristiano Machado", 4.3,"Av. Cristiano Machado, 4000 - União, Belo Horizonte - MG, 31160-342",
                    "(31) 3426-1100", "Churrasco"));
            restaurantesList.add(new  Restaurante("Ponto da Picanha", 4.5,"Av. Brasil, 1256 - Funcionários, Belo Horizonte - MG, 30140-001",
                    "(31) 3273-7751", "Churrasco"));
            restaurantesList.add(new  Restaurante("Adega Steakhouse", 4.2,"Av. do Contorno, 8831 - Gutierrez, Belo Horizonte - MG, 30110-059",
                    "(31) 3292-6333", "Churrasco"));
            restaurantesList.add(new  Restaurante("Porcão BH", 4.2,"Av. Raja Gabáglia, 2671 - São Bento, Belo Horizonte - MG, 30350-563",
                    "(31) 3293-8787", "Churrasco"));


            restaurantesList.add(new  Restaurante("Ancho", 4.3,"R. Curitiba, 2164 - Lourdes, Belo Horizonte - MG, 30170-120",
                    "(31) 99671-0607", "Tailandesa"));
            restaurantesList.add(new  Restaurante("Cozinha de Fogo Wals - BH Shopping", 4.3,"Jardim Suspenso, BH Shopping - BR-356, 3049 - 4º Piso - Belvedere, Belo Horizonte - MG, 30320-900",
                    "(31) 99762-6073", "Tailandesa"));
            restaurantesList.add(new  Restaurante("Restaurante Chen Chang Kee Noodle House - Galeria BH", 4.7," R. Curitiba, 130 - Centro, Belo Horizonte - MG, 30170-120",
                    "(31) 97534-7710", "Tailandesa"));
            restaurantesList.add(new  Restaurante("Yan Shan Zay", 4.8,"Av. Getúlio Vargas, 1220 - Funcionários, Belo Horizonte - MG, 30112-020",
                    "(31) 99110-8953", "Tailandesa"));
        }

        public static RestauranteRepositoryImpl getRepository() {
            if(repository == null) {
                repository = new RestauranteRepositoryImpl();
            }
            return repository;
        }

        public List<Restaurante> getRestauranteList() {
            return restaurantesList;
        }

        @Override // metodo para realizar o filtro de categorias
        public List<Restaurante> getRestaurantCategory(String category) {
            List<Restaurante> restaurantesFilter = new ArrayList<>();
            for(Restaurante restaurante: restaurantesList) {
                if(restaurante.category.equals(category)) {
                    restaurantesFilter.add(restaurante);
                }
            }
            return restaurantesFilter;
        }

        @Override // metodo para filtrar os favoritos
        public List<Restaurante> getFavoriteRestaurants() {
            List<Restaurante> favoritos = new ArrayList<>();
            for(Restaurante restaurante: restaurantesList) {
                if(restaurante.isFavorite) {
                    favoritos.add(restaurante);
                }
            }
            return favoritos;
        }

        @Override // metodo para adicionar restaurantes
        public void addRestaurante(Restaurante restaurante) {
            restaurantesList.add(restaurante);
        }



}



