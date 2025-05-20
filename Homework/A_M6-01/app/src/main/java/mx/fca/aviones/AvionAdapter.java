package mx.fca.aviones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AvionAdapter extends RecyclerView.Adapter<AvionHolder> {

    ArrayList<Avion> aviones;

    public AvionAdapter(ArrayList<Avion> aviones) {
        this.aviones = aviones;
    }

    @NonNull
    @Override
    public AvionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.avion_view, parent, false);
        return new AvionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvionHolder holder, int position) {
        Avion avion = aviones.get(position);
        holder.imgAvion.setImageResource(avion.getImage());
    }

    @Override
    public int getItemCount() {
        return aviones.size();
    }
}
