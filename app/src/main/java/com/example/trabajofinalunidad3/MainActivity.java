package com.example.trabajofinalunidad3;

import android.os.Bundle;
import android.os.Handler;
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
                new Comida("Albondigas", R.drawable.albondigas1, "Albóndigas caseras con salsa de tomate", 10.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Bistec", R.drawable.bistec1, "Bistec de ternera a la parrilla", 15.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Boquerones", R.drawable.boquerones1, "Boquerones frescos marinados", 8.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Caracoles", R.drawable.caracoles1, "Caracoles en salsa de hierbas", 12.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Centollo", R.drawable.centollo1, "Centollo gallego fresco", 25.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Croquetas", R.drawable.croquetas1, "Croquetas de jamón ibérico", 7.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Curry", R.drawable.curry1, "Curry de pollo con arroz basmati", 13.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Ensalada", R.drawable.ensalada1, "Ensalada fresca con vinagreta", 6.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Fabada", R.drawable.fabada1, "Fabada asturiana tradicional", 14.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Frituras de Pescado", R.drawable.frituraspescado1, "Fritura variada de pescado fresco", 12.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Hamburguesa", R.drawable.hamburguesa1, "Hamburguesa completa con queso", 9.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Huevos Rotos", R.drawable.huevos1, "Huevos estrellados con patatas", 7.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Lasagna", R.drawable.lasagna1, "Lasagna clásica de carne y queso", 11.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Macarrones", R.drawable.macarrones1, "Macarrones con salsa boloñesa", 8.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Milanesa", R.drawable.milanesa1, "Milanesas de pollo empanadas", 12.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Navajas", R.drawable.navajas1, "Navajas a la plancha con limón", 17.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Paella", R.drawable.paella1, "Paella valenciana tradicional", 18.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Pizza", R.drawable.pizza1, "Pizza margarita al horno", 13.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Pollo", R.drawable.pollo1, "Pollo asado con especias", 10.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.CARNE),
                new Comida("Pulpo", R.drawable.pulpo1, "Pulpo a la gallega con pimentón", 20.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Sardinas", R.drawable.sardinas1, "Sardinas asadas con ajo", 9.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Spaghetti", R.drawable.spagueti1, "Spaghetti a la carbonara", 11.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Sushi", R.drawable.sushi1, "Sushi variado fresco", 22.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Tacos", R.drawable.tacos1, "Tacos mexicanos con guacamole", 9.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.OTROS),
                new Comida("Tortilla", R.drawable.tortilla1, "Tortilla de patatas tradicional", 6.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Pisto", R.drawable.pisto1, "Guiso de verduras con tomate al estilo manchego", 8.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Gazpacho", R.drawable.gazpacho1, "Sopa fría de tomate y verduras típica andaluza", 6.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Escalivada", R.drawable.escalivada1, "Verduras asadas al estilo catalán", 7.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Merluza", R.drawable.merluza1, "Merluza cocida con patatas y pimentón", 18.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Calamares", R.drawable.calamares1, "Anillas de calamar rebozadas y fritas", 12.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Bacalao ", R.drawable.bacalao1, "Bacalao con emulsión de aceite y ajo al estilo vasco", 19.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.PESCADO_Y_MARISCOS),
                new Comida("Salmorejo", R.drawable.salmorejo1, "Crema fría de tomate con jamón y huevo", 7.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Espinacas", R.drawable.espinacas1, "Espinacas salteadas con pasas y piñones", 9.0, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),
                new Comida("Lentejas", R.drawable.lentejas1, "Lentejas estofadas con verduras y chorizo", 9.5, getRandomOferta(diez, veinte, dosxuno), Comida.TipoComida.VEGETALES),

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
                lastPosition = layoutManager.findLastVisibleItemPosition();

                isAutoScrollEnabled = true; // Habilitar autoscroll
                handler.postDelayed(runnable, speedScroll); // Reiniciar el autoscroll
            } else {
                // Guardar la posición actual cuando se desactiva el autoscroll
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvOfertas.getLayoutManager();
                lastPosition = layoutManager.findLastVisibleItemPosition();

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
