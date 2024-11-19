package com.example.trabajofinalunidad3;

import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear conjunto de datos
        ArrayList<Comida> ComidaArrayList = new ArrayList<>(Arrays.asList(new Comida[]{

                new Comida("Ensalada", R.drawable.ensalada1, "Test descripccion", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripccion", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripccion", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripccion", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripccion", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripccion", 13, R.drawable.boton_carrito, 1),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripccion", 15, R.drawable.boton_carrito, 1),
                new Comida("Pizza", R.drawable.pizza1, "Test descripccion", 13, R.drawable.boton_carrito, 1),


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


        // código del auto-scroll
        final int speedScroll = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count == ComidaArrayList.size()) {
                    count = 0; // Reinicia a la primera posición si llegas al final
                    rvOfertas.smoothScrollToPosition(count);
                } else {
                    rvOfertas.smoothScrollToPosition(count++);
                }
                handler.postDelayed(this, speedScroll);
            }
        };

        // Inicia el auto-scroll
        handler.postDelayed(runnable, speedScroll);
    }
}