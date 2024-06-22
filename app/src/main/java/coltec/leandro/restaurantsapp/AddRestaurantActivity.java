package coltec.leandro.restaurantsapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText editRestaurantName;
    private EditText editRestaurantAdress;
    private EditText editRestaurantNota;
    private EditText editRestaurantTelephone;
    private Spinner editRestaurantCategory;
    private Button btnAddRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        Toolbar toolbar = findViewById(R.id.toolbar_add_restaurant);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita o botão de voltar
        getSupportActionBar().setTitle("Adicionar Restaurante"); // Define o título da Toolbar

        editRestaurantName = findViewById(R.id.edit_restaurant_name);
        editRestaurantAdress = findViewById(R.id.edit_restaurant_adress);
        editRestaurantCategory = findViewById(R.id.spinner_category);
        editRestaurantNota = findViewById(R.id.edit_restaurant_nota);
        editRestaurantTelephone = findViewById(R.id.edit_restaurant_telephone);
        btnAddRestaurant = findViewById(R.id.btn_add_restaurant);

        Spinner spinnerCategory = findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnAddRestaurant.setOnClickListener(v -> {
            String name = editRestaurantName.getText().toString();
            String adress = editRestaurantAdress.getText().toString();
            String category = editRestaurantCategory.getSelectedItem().toString();
            String notaString = editRestaurantNota.getText().toString();
            String telephone = editRestaurantTelephone.getText().toString();

            // Validação de campo sem nada
            if (name.isEmpty() || adress.isEmpty() || category.isEmpty() || notaString.isEmpty() || telephone.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Valida categoria
            if (category.equals("Chinesa") || category.equals("Arabe") || category.equals("Churrasco") || category.equals("Tailandesa")) {

                double nota;
                try {
                    nota = Double.parseDouble(notaString);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Nota inválida", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cria o novo restaurante
                Restaurante restaurante = new Restaurante(name, nota, adress, telephone, category);

                // Adiciona o restaurante ao repositorio
                RestauranteRepositoryImpl repository = RestauranteRepositoryImpl.getRepository();
                repository.addRestaurante(restaurante);

                //Notifica o usuário e fecha
                Toast.makeText(this, "Restaurante adicionado", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Categoria inválida", Toast.LENGTH_SHORT).show();
            }
            });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Fecha a Activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}