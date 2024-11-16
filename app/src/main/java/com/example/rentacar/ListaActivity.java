package com.example.rentacar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import modelo.Entrada;

public class ListaActivity extends AppCompatActivity {

    // Creamos un objeto java en la clase Recycleview
    RecyclerView rcvEntrada;
    // Generamos una referencia a Firestore
    private FirebaseFirestore firebaseFirestore;
    // Creamos objeto de la clase Arraylist
    private ArrayList<Entrada> entradaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Vinculamos los elementos de la interfaz
        vincularElementos();
        // Iniciar firestore
        iniciarFirestore();
        // Iniciar componenetes
        iniciarComponentes();

    }

    private void iniciarComponentes() {
        // Inicializamos el arraylist de objetos de la clase entrada (vacia inicialmente)
        entradaArrayList = new ArrayList<>();
        // Establecemos propiedades para el recycleview
        rcvEntrada.setHasFixedSize(true);

    }

    private void vincularElementos() {
        rcvEntrada = (RecyclerView) findViewById(R.id.rcv_entrada);
    }

    private void iniciarFirestore() {
        // Utilizamos objeto de la clase firebasefirestore para inicializar una instancia
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
}