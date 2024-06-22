package coltec.leandro.restaurantsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestauranteRecyclerAdapter extends RecyclerView.Adapter<RestauranteRecyclerAdapter.RestauranteViewHolder> {

    private final List<Restaurante> restaurantes;
    private RestauranteOnClickListener restauranteOnClickListener;

    public interface  RestauranteOnClickListener {
        public void onClickRestaurante(View view, int position);
    }
    // Constructor
    public RestauranteRecyclerAdapter(List<Restaurante> restaurantes, RestauranteOnClickListener restauranteOnClickListener) {
        this.restaurantes = restaurantes;
        this.restauranteOnClickListener = restauranteOnClickListener;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // inflando a view e criando a viewHolder
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_restaurant, viewGroup, false);
        // depois de inflar, crio o viewholder
        // o viewholder contem a referencia para todas as views do layout
        RestauranteViewHolder holder = new RestauranteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {
        // tratamento de click no item
        Restaurante currentRestaurante = restaurantes.get(position);
        holder.lblRestaurantName.setText(currentRestaurante.name);
        holder.lblRestaurantAdress.setText(currentRestaurante.adress);
        holder.lblRestaurantNota.setText(currentRestaurante.nota.toString());
        holder.lblRestaurantNumber.setText(currentRestaurante.telephone);
        holder.lblRestaurantCategory.setText(currentRestaurante.category);

        holder.imgRestaurantFavorite.setImageResource(
                currentRestaurante.isFavorite ? R.drawable.icon_star : R.drawable.icon_borderstar);
        holder.imgRestaurantFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRestaurante.isFavorite = !currentRestaurante.isFavorite;
                notifyItemChanged(position);
            }
        });

        // listener endereco

        holder.lblRestaurantAdress.setOnClickListener(view -> {
            String endereco = currentRestaurante.adress;
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(endereco));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            view.getContext().startActivity(mapIntent);
        });

        // listener telefone

        holder.lblRestaurantNumber.setOnClickListener(view -> {
            String telefone = currentRestaurante.telephone;
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + telefone);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            view.getContext().startActivity(intent);
        });

       if(restauranteOnClickListener != null) {
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View itemView) {
                   restauranteOnClickListener.onClickRestaurante(itemView, position);
               }
           });
       }
    }

    @Override
    public int getItemCount() { return restaurantes.size();}

    public static class RestauranteViewHolder extends RecyclerView.ViewHolder {
        TextView lblRestaurantName;
        TextView lblRestaurantAdress;
        TextView lblRestaurantNota;
        TextView lblRestaurantNumber;
        TextView lblRestaurantCategory;
        ImageView imgRestaurantFavorite;

        RestauranteViewHolder(View itemView) {
            super(itemView);
            // pegando o id dos itens no xml
            lblRestaurantName = itemView.findViewById(R.id.lbl_restaurant_name);
            lblRestaurantAdress = itemView.findViewById(R.id.lbl_restaurant_adress);
            lblRestaurantNota = itemView.findViewById(R.id.lbl_restaurant_nota);
            lblRestaurantNumber = itemView.findViewById(R.id.lbl_restaurant_number);
            lblRestaurantCategory = itemView.findViewById(R.id.lbl_restaurant_category);
            imgRestaurantFavorite = itemView.findViewById(R.id.img_restaurant_favorite);
        }
    }

    public void updateRestaurantes(List<Restaurante> novaLista) {
        this.restaurantes.clear();
        this.restaurantes.addAll(novaLista);
    }
}
