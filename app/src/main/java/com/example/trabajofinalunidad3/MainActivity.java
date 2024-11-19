package com.example.trabajofinalunidad3;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean isAutoScrollEnabled = true; // Variable para controlar el estado del autoscroll
    private int lastPosition = 0; // Variable para almacenar la última posición del RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear conjunto de datos
        ArrayList<Comida> ComidaArrayList = new ArrayList<>(Arrays.asList(new Comida[]{
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, 1),

                // Agrega más items si es necesario
        }));

        // Crear el adaptador
        ComidaAdapter ComidaAdapter = new ComidaAdapter(ComidaArrayList);

        // Instanciar el RecyclerView
        RecyclerView rvComidas = findViewById(R.id.rv_Comidas);
        RecyclerView rvOfertas = findViewById(R.id.rv_Ofertas);

        // Configurar LayoutManager para cada RecyclerView
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvComidas.setLayoutManager(verticalLayoutManager);
        rvOfertas.setLayoutManager(horizontalLayoutManager);

        // Asignar el adaptador al RecyclerView
        rvComidas.setAdapter(ComidaAdapter);
        rvOfertas.setAdapter(ComidaAdapter);

        // Código para el autoscroll
        final int speedScroll = 3000; // Tiempo de desplazamiento entre cada scroll (en milisegundos)
        runnable = new Runnable() {
            @Override
            public void run() {
                if (lastPosition >= ComidaArrayList.size()) {
                    lastPosition = 0; // Reinicia a la primera posición si llegas al final
                }
                rvOfertas.smoothScrollToPosition(lastPosition++);

                if (isAutoScrollEnabled) {
                    handler.postDelayed(this, speedScroll); // Continuar el autoscroll
                }
            }
        };

        // Iniciar el autoscroll si está habilitado por defecto
        if (isAutoScrollEnabled) {
            handler.postDelayed(runnable, speedScroll);
        }

        // Encontrar el switch por su ID
        Switch autoScrollSwitch = findViewById(R.id.switch1);
        autoScrollSwitch.setChecked(true); // Asegúrate de que esté encendido por defecto

        // Establecer el listener para el switch
        autoScrollSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Capturar la posición actual del RecyclerView
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvOfertas.getLayoutManager();
                lastPosition = layoutManager.findFirstVisibleItemPosition();

                isAutoScrollEnabled = true; // Habilitar autoscroll
                handler.postDelayed(runnable, speedScroll); // Reiniciar el autoscroll
            } else {
                // Guardar la posición actual cuando se desactiva el autoscroll
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvOfertas.getLayoutManager();
                lastPosition = layoutManager.findFirstVisibleItemPosition();

                isAutoScrollEnabled = false; // Deshabilitar autoscroll
                handler.removeCallbacks(runnable); // Detener el autoscroll
            }
        });
    }
}
