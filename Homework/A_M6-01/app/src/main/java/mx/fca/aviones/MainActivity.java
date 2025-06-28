package mx.fca.aviones;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private Button btnPrevious;
    private TextView tvStepNumber;
    private TextView tvCollisionCount;

    private RecyclerView gridRecyclerView;
    private Plano planoActual;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        tvStepNumber = findViewById(R.id.tvStepNumber);
        tvCollisionCount = findViewById(R.id.tvCollisionCount);
        gridRecyclerView = findViewById(R.id.gridRecyclerView);

        // Inicializar el plano inicial
        planoActual = Planificador.crearRutaInicial();


        int initialGridCols = planoActual.col;
        int initialGridRows = planoActual.row;

        // Configurar el GridLayoutManager con el número de columnas del grid
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, initialGridCols));

        // Inicializar el adaptador con las dimensiones correctas y el plano inicial
        gridAdapter = new GridAdapter(initialGridCols, initialGridRows, planoActual);
        gridRecyclerView.setAdapter(gridAdapter);

        // Establecer listeners para los botones
        btnNext.setOnClickListener(v -> {
            planoActual = planoActual.next();
            Log.d("MainActivity", "Clicked Next. New Plano Step: " + planoActual.noPaso); // Log para depuración
            updateUI();
        });

        btnPrevious.setOnClickListener(v -> {
            planoActual = planoActual.prev();
            Log.d("MainActivity", "Clicked Previous. New Plano Step: " + planoActual.noPaso); // Log para depuración
            updateUI();
        });

        // Actualizar la UI por primera vez
        updateUI();
    }

    private void updateUI() {
        tvStepNumber.setText(getString(R.string.step_format, planoActual.noPaso));
        tvCollisionCount.setText(getString(R.string.collisions_format, planoActual.getNumeroColisiones()));


        int currentGridCols = planoActual.col;
        int currentGridRows = planoActual.row;

        Log.d("MainActivity", "Updating UI. Grid Dims: " + currentGridCols + "x" + currentGridRows + ". Step: " + planoActual.noPaso); // Log para depuración


        GridLayoutManager layoutManager = (GridLayoutManager) gridRecyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getSpanCount() != currentGridCols) {
            Log.d("MainActivity", "Recreating GridLayoutManager. Old Span: " + (layoutManager != null ? layoutManager.getSpanCount() : "null") + ", New Span: " + currentGridCols); // Log para depuración
            gridRecyclerView.setLayoutManager(new GridLayoutManager(this, currentGridCols));
        }


        gridAdapter.updateGridDimensions(currentGridCols, currentGridRows);
        gridAdapter.updatePlano(planoActual); // Esto llamará a notifyDataSetChanged()
    }
}