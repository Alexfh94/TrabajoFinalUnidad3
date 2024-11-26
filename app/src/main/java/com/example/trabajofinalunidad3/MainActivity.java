package com.example.trabajofinalunidad3;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean isAutoScrollEnabled = true;
    private int lastPosition = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Comida> comidasArrayList = initializeData();
        setupImageButtons();
        setupRecyclerViews(comidasArrayList);
    }

    // Inicializa los datos y genera la lista de comidas
    private ArrayList<Comida> initializeData() {
        ArrayList<Comida> comidasArrayList = new ArrayList<>(Arrays.asList(
                new Comida("Albondigas", R.drawable.albondigas1, "Albóndigas caseras con salsa de tomate", 10.5, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Bistec", R.drawable.bistec1, "Bistec de ternera a la parrilla", 15.0, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Boquerones", R.drawable.boquerones1, "Boquerones frescos marinados", 8.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Caracoles", R.drawable.caracoles1, "Caracoles en salsa de hierbas", 12.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Centollo", R.drawable.centollo1, "Centollo gallego fresco", 25.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Croquetas", R.drawable.croquetas1, "Croquetas de jamón ibérico", 7.5, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Curry", R.drawable.curry1, "Curry de pollo con arroz basmati", 13.0, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Ensalada", R.drawable.ensalada1, "Ensalada fresca con vinagreta", 6.0, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Fabada", R.drawable.fabada1, "Fabada asturiana tradicional", 14.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Frituras de Pescado", R.drawable.frituraspescado1, "Fritura variada de pescado fresco", 12.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Hamburguesa", R.drawable.hamburguesa1, "Hamburguesa completa con queso", 9.5, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Huevos Rotos", R.drawable.huevos1, "Huevos estrellados con patatas", 7.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Lasagna", R.drawable.lasagna1, "Lasagna clásica de carne y queso", 11.0, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Macarrones", R.drawable.macarrones1, "Macarrones con salsa boloñesa", 8.5, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Milanesa", R.drawable.milanesa1, "Milanesas de pollo empanadas", 12.5, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Navajas", R.drawable.navajas1, "Navajas a la plancha con limón", 17.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Paella", R.drawable.paella1, "Paella valenciana tradicional", 18.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Pizza", R.drawable.pizza1, "Pizza margarita al horno", 13.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Pollo", R.drawable.pollo1, "Pollo asado con especias", 10.0, getRandomOferta(), Comida.TipoComida.CARNE),
                new Comida("Pulpo", R.drawable.pulpo1, "Pulpo a la gallega con pimentón", 20.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Sardinas", R.drawable.sardinas1, "Sardinas asadas con ajo", 9.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Spaghetti", R.drawable.spagueti1, "Spaghetti a la carbonara", 11.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Sushi", R.drawable.sushi1, "Sushi variado fresco", 22.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Tacos", R.drawable.tacos1, "Tacos mexicanos con guacamole", 9.0, getRandomOferta(), Comida.TipoComida.OTROS),
                new Comida("Tortilla", R.drawable.tortilla1, "Tortilla de patatas tradicional", 6.5, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Pisto", R.drawable.pisto1, "Guiso de verduras con tomate al estilo manchego", 8.0, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Gazpacho", R.drawable.gazpacho1, "Sopa fría de tomate y verduras típica andaluza", 6.5, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Escalivada", R.drawable.escalivada1, "Verduras asadas al estilo catalán", 7.5, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Merluza", R.drawable.merluza1, "Merluza cocida con patatas y pimentón", 18.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Calamares", R.drawable.calamares1, "Anillas de calamar rebozadas y fritas", 12.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Bacalao ", R.drawable.bacalao1, "Bacalao con emulsión de aceite y ajo al estilo vasco", 19.0, getRandomOferta(), Comida.TipoComida.PESCADO),
                new Comida("Salmorejo", R.drawable.salmorejo1, "Crema fría de tomate con jamón y huevo", 7.0, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Espinacas", R.drawable.espinacas1, "Espinacas salteadas con pasas y piñones", 9.0, getRandomOferta(), Comida.TipoComida.VEGETALES),
                new Comida("Lentejas", R.drawable.lentejas1, "Lentejas estofadas con verduras y chorizo", 9.5, getRandomOferta(), Comida.TipoComida.VEGETALES)

                ));
        return comidasArrayList;
    }

    // Configura los botones de imagen con toasts
    private void setupImageButtons() {
        try {
            ImageButton imgButton1 = findViewById(R.id.profileImg);
            ImageButton imgButton2 = findViewById(R.id.recentImg);
            ImageButton imgButton3 = findViewById(R.id.settingsImg);
            ImageButton imgButton4 = findViewById(R.id.cartImg);

            View.OnClickListener showToastListener = v ->
                    Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

            imgButton1.setOnClickListener(showToastListener);
            imgButton2.setOnClickListener(showToastListener);
            imgButton3.setOnClickListener(showToastListener);
            imgButton4.setOnClickListener(showToastListener);
        } catch (Exception e) {
            Log.e("MainActivity", "Error configurando los ImageButtons", e);
        }
    }

    // Configura los RecyclerViews y el spinner
    private void setupRecyclerViews(ArrayList<Comida> comidasArrayList) {
        try {
            ArrayList<Comida> ofertaArrayList = new ArrayList<>();
            ArrayList<Comida> comidaArrayList = new ArrayList<>();

            for (Comida comida : comidasArrayList) {
                if (comida.getImgOferta() != 0) {
                    ofertaArrayList.add(comida);
                } else {
                    comidaArrayList.add(comida);
                }
            }

            ComidaAdapter comidaAdapter = new ComidaAdapter(comidaArrayList);
            OfertaAdapter ofertaAdapter = new OfertaAdapter(ofertaArrayList);

            RecyclerView rvComidas = findViewById(R.id.rv_Comidas);
            RecyclerView rvOfertas = findViewById(R.id.rv_Ofertas);

            rvComidas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rvOfertas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            rvComidas.setAdapter(comidaAdapter);
            rvOfertas.setAdapter(ofertaAdapter);

            setupSpinner(comidasArrayList, comidaArrayList, comidaAdapter);
            setupAutoScroll(rvOfertas, ofertaArrayList, ofertaAdapter);
        } catch (Exception e) {
            Log.e("MainActivity", "Error configurando los RecyclerViews", e);
        }
    }

    // Configura el Spinner para filtrar tipos de comida
    private void setupSpinner(ArrayList<Comida> comidasArrayList, ArrayList<Comida> comidaArrayList, ComidaAdapter comidaAdapter) {
        try {
            Spinner spinnerTipoComida = findViewById(R.id.spinner_tipo_comida);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_comida_opciones, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTipoComida.setAdapter(adapter);

            spinnerTipoComida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    filterComidas(comidasArrayList, comidaArrayList, selectedItem);
                    comidaAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.w("MainActivity", "Ninguna opción seleccionada en el spinner");
                }
            });
        } catch (Exception e) {
            Log.e("MainActivity", "Error configurando el Spinner", e);
        }
    }

    // Filtra las comidas según el tipo seleccionado
    private void filterComidas(ArrayList<Comida> comidasArrayList, ArrayList<Comida> comidaArrayList, String selectedItem) {
        comidaArrayList.clear();
        for (Comida comida : comidasArrayList) {
            if ((selectedItem.equals("Todos") || comida.getTipo().toString().equalsIgnoreCase(selectedItem) )&& comida.getImgOferta()==0 ) {
                comidaArrayList.add(comida);
            }
        }
    }

    // Configura el autoscroll del RecyclerView de ofertas
    private void setupAutoScroll(RecyclerView rvOfertas, ArrayList<Comida> ofertaArrayList, OfertaAdapter ofertaAdapter) {
        final int speedScroll = 3000;
        runnable = () -> {
            Comida comidaActual = ofertaArrayList.get(lastPosition);
            ofertaArrayList.add(comidaActual);
            ofertaAdapter.notifyItemInserted(ofertaArrayList.size() - 1);

            rvOfertas.smoothScrollToPosition(lastPosition++);
            if (isAutoScrollEnabled) {
                handler.postDelayed(runnable, speedScroll);
            }
        };

        Switch autoScrollSwitch = findViewById(R.id.switch1);
        autoScrollSwitch.setChecked(true);
        autoScrollSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isAutoScrollEnabled = isChecked;
            if (isChecked) {
                handler.postDelayed(runnable, speedScroll);
            } else {
                handler.removeCallbacks(runnable);
            }
        });

        if (isAutoScrollEnabled) {
            handler.postDelayed(runnable, speedScroll);
        }
    }

    // Genera una oferta aleatoria
    private int getRandomOferta() {
        int[] ofertas = {1, 2, 3};
        return random.nextInt(2) == 0 ? 0 : ofertas[random.nextInt(ofertas.length)];
    }
}
