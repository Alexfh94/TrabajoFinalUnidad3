package com.example.trabajofinalunidad3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements ComidaAdapter.OnClickComida, OfertaAdapter.OnClickOferta {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<Comida> comidaArrayList;
    private ArrayList<Comida> ofertaArrayList;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private boolean isAutoScrollEnabled = true;
    private int lastPosition = 0;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            comidaArrayList = getArguments().getParcelableArrayList("comidaList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setupRecyclerViews(view, comidaArrayList);
        return view;
    }

    private void setupRecyclerViews(View view, ArrayList<Comida> comidasArrayList) {
        try {
            ofertaArrayList = new ArrayList<>();
            comidaArrayList = new ArrayList<>();

            for (Comida comida : comidasArrayList) {
                if (comida.getImgOferta() != 0) {
                    ofertaArrayList.add(comida);
                } else {
                    comidaArrayList.add(comida);
                }
            }

            ComidaAdapter comidaAdapter = new ComidaAdapter(comidaArrayList, MainFragment.this, getContext());
            OfertaAdapter ofertaAdapter = new OfertaAdapter(ofertaArrayList, MainFragment.this, getContext());

            RecyclerView rvComidas = view.findViewById(R.id.rv_Comidas);
            RecyclerView rvOfertas = view.findViewById(R.id.rv_Ofertas);

            rvComidas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvOfertas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            rvComidas.setAdapter(comidaAdapter);
            rvOfertas.setAdapter(ofertaAdapter);

            setupSpinner(view, comidasArrayList, comidaArrayList, comidaAdapter);
            setupAutoScroll(view, rvOfertas, ofertaArrayList, ofertaAdapter);
        } catch (Exception e) {
            Log.e(getString(R.string.TAGM), getString(R.string.error_configurando_los_recyclerviews), e);
        }
    }

    private void setupSpinner(View view, ArrayList<Comida> comidasArrayList, ArrayList<Comida> comidaArrayList, ComidaAdapter comidaAdapter) {
        try {
            Spinner spinnerTipoComida = view.findViewById(R.id.spinner_tipo_comida);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipo_comida_opciones, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTipoComida.setAdapter(adapter);

            spinnerTipoComida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    filtroComidas(comidasArrayList, comidaArrayList, selectedItem);
                    comidaAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.w(getString(R.string.TAGM), getString(R.string.ninguna_opci_n_seleccionada_en_el_spinner));
                }
            });
        } catch (Exception e) {
            Log.e(getString(R.string.TAGM), getString(R.string.error_configurando_el_spinner), e);
        }
    }

    private void filtroComidas(ArrayList<Comida> comidasArrayList, ArrayList<Comida> comidaArrayList, String selectedItem) {
        comidaArrayList.clear();
        for (Comida comida : comidasArrayList) {
            if ((selectedItem.equals(getString(R.string.todos)) || comida.getTipo().toString().equalsIgnoreCase(selectedItem)) && comida.getImgOferta() == 0) {
                comidaArrayList.add(comida);
            }
        }
    }

    private void setupAutoScroll(View view, RecyclerView rvOfertas, ArrayList<Comida> ofertaArrayList, OfertaAdapter ofertaAdapter) {
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

        Switch autoScrollSwitch = view.findViewById(R.id.switch1);
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

    @Override
    public void onClickCarrito(View view, int position) {
        Comida comida = comidaArrayList.get(position);
        String mensaje = comida.getTitulo() + getString(R.string.a_adido_al_carrito);
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCardComida(View view, int position) {
        Comida comida = comidaArrayList.get(position);
        String mensaje = comida.getTitulo() + "\n " + comida.getPrecio() + getString(R.string.EUR);
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCardOferta(View view, int position) {
        Comida comida = ofertaArrayList.get(position);
        String mensaje = comida.getTitulo() + " " + comida.getPrecio() + getString(R.string.EUR) + getString(R.string.a_adido_al_carrito);
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
