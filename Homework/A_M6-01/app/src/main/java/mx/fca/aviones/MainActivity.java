package mx.fca.aviones;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        // ... (tu inicialización de vistas y listeners existentes) ...
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        tvStepNumber = findViewById(R.id.tvStepNumber);
        tvCollisionCount = findViewById(R.id.tvCollisionCount);
        gridRecyclerView = findViewById(R.id.gridRecyclerView);

        planoActual = Planificador.crearRutaInicial();

        int initialGridCols = planoActual.col;
        int initialGridRows = planoActual.row;

        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, initialGridCols));
        gridAdapter = new GridAdapter(initialGridCols, initialGridRows, planoActual);
        gridRecyclerView.setAdapter(gridAdapter);

        btnNext.setOnClickListener(v -> {
            planoActual = planoActual.next();
            Log.d("MainActivity", "Clicked Next. New Plano Step: " + planoActual.noPaso);
            updateUI();
        });

        btnPrevious.setOnClickListener(v -> {
            planoActual = planoActual.prev();
            Log.d("MainActivity", "Clicked Previous. New Plano Step: " + planoActual.noPaso);
            updateUI();
        });

        updateUI();
    }

    // --- Métodos para el menú de cerrar sesión ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú; esto añade elementos a la barra de acciones si están presentes.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Maneja los clics en los elementos de la barra de acciones.
        // El ID de los elementos de menú se define en menu_main.xml
        if (item.getItemId() == R.id.action_logout) { // Usamos R.id.action_logout
            performLogout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void performLogout() {
        // Lógica para cerrar sesión:
        // 1. Opcional: Borrar credenciales o tokens guardados (ej. SharedPreferences)
        // Por ahora, solo navegamos de vuelta a la LoginActivity

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        // Flags para limpiar la pila de actividades:
        // FLAG_ACTIVITY_NEW_TASK: Inicia la actividad en una nueva tarea.
        // FLAG_ACTIVITY_CLEAR_TASK: Borra todas las actividades existentes en la tarea.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finaliza MainActivity para que el usuario no pueda volver con el botón atrás
    }

    // ... (tu método updateUI existente) ...
    /**
     * Actualiza todos los elementos de la interfaz de usuario.
     */
    private void updateUI() {
        tvStepNumber.setText(getString(R.string.step_format, planoActual.noPaso));
        tvCollisionCount.setText(getString(R.string.collisions_format, planoActual.getNumeroColisiones()));

        int currentGridCols = planoActual.col;
        int currentGridRows = planoActual.row;

        Log.d("MainActivity", "Updating UI. Grid Dims: " + currentGridCols + "x" + currentGridRows + ". Step: " + planoActual.noPaso);

        GridLayoutManager layoutManager = (GridLayoutManager) gridRecyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getSpanCount() != currentGridCols) {
            Log.d("MainActivity", "Recreating GridLayoutManager. Old Span: " + (layoutManager != null ? layoutManager.getSpanCount() : "null") + ", New Span: " + currentGridCols);
            gridRecyclerView.setLayoutManager(new GridLayoutManager(this, currentGridCols));
        }

        gridAdapter.updateGridDimensions(currentGridCols, currentGridRows);
        gridAdapter.updatePlano(planoActual);
    }
}