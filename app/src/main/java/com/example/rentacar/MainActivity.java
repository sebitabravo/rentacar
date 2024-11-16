package com.example.rentacar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Entrada;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Variable de referencia a firestore
    private FirebaseFirestore firebaseFirestore;
    // Variables de elementos de iterfaz
    EditText edtFecha;
    EditText edtPatente;
    EditText edtComentarios;
    Spinner spnEstado;
    // Botones
    Button btnListar;
    Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciar firestore
        iniciarFirestore();
        // Vinculamos los elementos de la interfaz
        vincularElementos();
        // Habilitamos listener para los botones
        habilitarListener();
    }

    private void habilitarListener() {
        btnRegistrar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
    }

    private void vincularElementos() {
        edtFecha = (EditText) findViewById(R.id.txt_fecha);
        edtPatente = (EditText) findViewById(R.id.txt_patente);
        edtComentarios = (EditText) findViewById(R.id.txt_comentario);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar);
        btnListar = (Button) findViewById(R.id.btn_listar);


        spnEstado = (Spinner) findViewById(R.id.spn_estado);
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spn_estado,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spnEstado.setAdapter(adapter);
    }

    private void iniciarFirestore() {
        // Utilizamos objeto de la clase firebasefirestore para inicializar una instancia
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View view) {
        // Determinamos que tipo de operacion queremos efectuar
        // En caso de querer registrar una nueva entrada
        if (view.getId() == R.id.btn_registrar){
            // Obtenemos los valores ingresados
            String fechaIngreso = edtFecha.getText().toString();
            Date fechaRegistro = null;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                fechaRegistro = formato.parse(fechaIngreso);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Capturamos los demas valores
            String patente = edtPatente.getText().toString();
            String comentario = edtComentarios.getText().toString();
            String estado = spnEstado.getSelectedItem().toString();
            // Enviamos los datos a un metodo para incorporar a firestore
            agregarEntradaFirestore(fechaRegistro, patente, comentario, estado);


        }
        // En el caso de requerir visualizar todas las entradas
        if (view.getId() == R.id.btn_listar){
            // Por hacer: Accedemos a un nuevo activity para listar las entradas en un recyclerview.
        }
    }

    private void agregarEntradaFirestore(Date fechaRegistro, String patente, String comentario, String estado) {
        //Establecer la coleccion con la cual vamos a interactuar
        CollectionReference coleccionEntradas = firebaseFirestore.collection("Entrada");
        // Objeto de la clase Entrada
        Entrada entrada = new Entrada(patente, comentario, estado, fechaRegistro);
        // Agregamos el objeto a la coleccion
        coleccionEntradas.add(entrada).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // Mostramos un mensaje en un elemento de tipo Toast
                Toast.makeText(MainActivity.this, "Devolucion registrada correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Proceso con errores", Toast.LENGTH_SHORT).show();
            }
        });
    }
}