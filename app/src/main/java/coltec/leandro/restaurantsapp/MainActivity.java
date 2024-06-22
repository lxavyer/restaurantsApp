package coltec.leandro.restaurantsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Restaurante> restaurantes; // Lista original de restaurantes
    List<Restaurante> currentRestaurantes; // Lista atual para exibição
    RestauranteRecyclerAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fabAddRestaurant;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Restaurantes");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        fabAddRestaurant = findViewById(R.id.fab_add_restaurant);
        fabAddRestaurant.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
            startActivity(intent);
        });

        //Obtem a lista de restaurantes do repositorio
        RestauranteRepositoryImpl repository = RestauranteRepositoryImpl.getRepository();
        restaurantes = repository.getRestauranteList();
        currentRestaurantes = new ArrayList<>(restaurantes); // Inicializa com a lista completa

        // faz a ligacao entre a recyclerview e o adapter
        recyclerView = findViewById(R.id.recyclerRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // cria o adapter e atribui ao RecyclerView
        adapter = new RestauranteRecyclerAdapter(currentRestaurantes, onClickRestaurante(this));
        recyclerView.setAdapter(adapter);

        setupNavigationDrawer();
    }

    private RestauranteRecyclerAdapter.RestauranteOnClickListener onClickRestaurante(Context context) {
        return (view, position) -> {
            // Obtem a lista de restaurantes do repositorio
            RestauranteRepositoryImpl repository = RestauranteRepositoryImpl.getRepository();
            List<Restaurante> restaurantes = repository.getRestauranteList();
            // Restaurante selecionado
            Restaurante restaurante = restaurantes.get(position);
            // Adiciona o restaurante como favorito
            restaurante.isFavorite = true;
        };
    }

    private void setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            RestauranteRepositoryImpl repositoryCategory = RestauranteRepositoryImpl.getRepository();

            if (itemId == R.id.Chinesa) {
                updateCurrentRestaurantes(repositoryCategory.getRestaurantCategory("Chinesa"));
            } else if (itemId == R.id.Arabe) {
                updateCurrentRestaurantes(repositoryCategory.getRestaurantCategory("Arabe"));
            } else if (itemId == R.id.Churrasco) {
                updateCurrentRestaurantes(repositoryCategory.getRestaurantCategory("Churrasco"));
            } else if (itemId == R.id.Tailandesa) {
                updateCurrentRestaurantes(repositoryCategory.getRestaurantCategory("Tailandesa"));
            } else if (itemId == R.id.nav_favorites) {
                updateCurrentRestaurantes(repositoryCategory.getFavoriteRestaurants());
            } else {
                updateCurrentRestaurantes(restaurantes); // Mostrar todos os restaurantes
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void updateCurrentRestaurantes(List<Restaurante> newRestaurantes) {
        currentRestaurantes.clear();
        currentRestaurantes.addAll(newRestaurantes);
        adapter.notifyDataSetChanged(); // Notifica o Adapter da mudança
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open_drawer) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.action_sort_asc) {
            Collections.sort(currentRestaurantes, (r1, r2) -> r1.name.compareToIgnoreCase(r2.name));
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_sort_desc) {
            Collections.sort(currentRestaurantes, (r1, r2) -> r2.name.compareToIgnoreCase(r1.name));
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() { // entra depois que ele volta da activity de adicionar
        super.onResume();
        // atualiza a lista de restaurantes
        updateCurrentRestaurantes(RestauranteRepositoryImpl.getRepository().getRestauranteList());
    }
}
