package com.example.trabajofinalunidad3;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean isAutoScrollEnabled = true; // Variable para controlar el estado del autoscroll
    private int lastPosition = 0; // Variable para almacenar la última posición del RecyclerView
    private Random random = new Random(); // Generador de números aleatorios

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int diez = R.drawable.ofertadiez;
        int veinte = R.drawable.ofertaventicinco;
        int dosxuno = R.drawable.oferta2x1;

        // Crear conjunto de datos
        ArrayList<Comida> comidasArrayList = new ArrayList<>(Arrays.asList(new Comida[]{

                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Ensalada", R.drawable.ensalada1, "Test descripción", 15, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
                new Comida("Pizza", R.drawable.pizza1, "Test descripción", 13, R.drawable.boton_carrito, getRandomOferta(diez, veinte, dosxuno)),
        }));

        ArrayList<Comida> ofertaArrayList = new ArrayList<>();
        ArrayList<Comida> comidaArrayList = new ArrayList<>();
        for (int i = 0; i < comidasArrayList.size(); i++) {
            if(comidasArrayList.get(i).getImgOferta()!= 0){

                ofertaArrayList.add(comidasArrayList.get(i));

            }
            else{
                comidaArrayList.add(comidasArrayList.get(i));
            }
        }





        // Crear el adaptador
        ComidaAdapter ComidaAdapter = new ComidaAdapter(comidaArrayList);
        OfertaAdapter OfertaAdapter = new OfertaAdapter(ofertaArrayList);

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
        rvOfertas.setAdapter(OfertaAdapter);

        // Código para el autoscroll
        final int speedScroll = 3000; // Tiempo de desplazamiento entre cada scroll (en milisegundos)
        runnable = new Runnable() {
            @Override
            public void run() {

                // Obtener el elemento actual y agregarlo al final de la lista
                Comida comidaActual = ofertaArrayList.get(lastPosition);
                ofertaArrayList.add(comidaActual);

                // Notificar al adaptador que un nuevo elemento fue insertado
                OfertaAdapter.notifyItemInserted(ofertaArrayList.size() - 1);

                if(lastPosition==0 && ofertaArrayList.size()>3){
                    lastPosition = 3;
                }
                // Realizar el desplazamiento suave al último elemento añadido
                rvOfertas.smoothScrollToPosition(lastPosition++);

                // Reiniciar el ciclo del autoscroll si está habilitado
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

    // Método para obtener una oferta aleatoria con peso
    private int getRandomOferta(int diez, int veinte, int dosxuno) {
        if (random.nextInt(2) == 0) { // 50% de probabilidad para 0
            return 0;
        } else {
            // Selecciona aleatoriamente una de las tres ofertas
            int[] ofertas = {diez, veinte, dosxuno};
            return ofertas[random.nextInt(ofertas.length)];
        }
    }
}
