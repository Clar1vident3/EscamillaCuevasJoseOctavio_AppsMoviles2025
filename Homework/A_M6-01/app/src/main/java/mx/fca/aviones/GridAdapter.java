package mx.fca.aviones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.CellViewHolder> {

    private int gridWidth;
    private int gridHeight;
    private Plano currentPlano;

    public GridAdapter(int gridWidth, int gridHeight, Plano initialPlano) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.currentPlano = initialPlano;
    }

    public void updatePlano(Plano newPlano) {
        this.currentPlano = newPlano;
        notifyDataSetChanged();
    }

    public void updateGridDimensions(int newWidth, int newHeight) {
        this.gridWidth = newWidth;
        this.gridHeight = newHeight;
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell_item, parent, false);


        int cellSize = parent.getWidth() / gridWidth;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = cellSize; // Establecer la altura igual al ancho para que sea cuadrada
        view.setLayoutParams(layoutParams);
        // ----------------------------------------------------

        return new CellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {
        int x = position % gridWidth;
        int y = position / gridWidth;

        // Resetear la celda a su estado predeterminado
        holder.imgCellContent.setImageDrawable(null);
        holder.tvCellText.setVisibility(View.GONE);
        holder.tvCellText.setText("");
        holder.imgCellContent.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));

        // Verificar si hay una colisión en esta celda
        Colision colisionEnCelda = null;
        for (Colision colision : currentPlano.colisiones) {
            if (colision.getX() == x && colision.getY() == y) {
                colisionEnCelda = colision;
                break;
            }
        }

        Avion avionEnCelda = null;
        if (colisionEnCelda == null) { // Solo buscamos avión si no hay colisión
            for (Avion avion : currentPlano.aviones) {
                if (avion.getX() == x && avion.getY() == y) {
                    avionEnCelda = avion;
                    break;
                }
            }
        }


        if (colisionEnCelda != null) {
            holder.imgCellContent.setImageResource(colisionEnCelda.getImage());
            holder.imgCellContent.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_red_light));
            holder.tvCellText.setText("COLL");
            holder.tvCellText.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
            holder.tvCellText.setVisibility(View.VISIBLE);
        } else if (avionEnCelda != null) {
            holder.imgCellContent.setImageResource(avionEnCelda.getImage());
            holder.tvCellText.setText(avionEnCelda.getId());
            holder.tvCellText.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black));
            holder.tvCellText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return gridWidth * gridHeight;
    }

    static class CellViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCellContent;
        TextView tvCellText;

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCellContent = itemView.findViewById(R.id.imgCellContent);
            tvCellText = itemView.findViewById(R.id.tvCellText);
        }
    }
}