package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class CalculadoraCB extends AppCompatActivity {

    private CheckBox checkBox_sumar;
    private CheckBox checkBox_restar;
    private CheckBox checkBox_multiplicar;
    private CheckBox checkBox_dividir;
    private EditText editText_n1;
    private EditText editText_n2;
    private Button button_evaluar;
    private TextView textView_resultado;
    private double n1, n2 , resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_c_b);

        editText_n1 = findViewById(R.id.editTextPhone_n1);
        editText_n2 = findViewById(R.id.editTextPhone_n2);
        checkBox_dividir = findViewById(R.id.checkBox_dividir);
        checkBox_sumar = findViewById(R.id.checkBox_sumar);
        checkBox_restar = findViewById(R.id.checkBox_restar);
        checkBox_multiplicar = findViewById(R.id.checkBox_multiplicar);
        button_evaluar = findViewById(R.id.button_evaluar);
        textView_resultado = findViewById(R.id.textView_resultado);

        button_evaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_n1.getText().toString().equals("") && !editText_n2.getText().toString().equals("")){
                    n1 = Double.parseDouble(editText_n1.getText().toString());
                    n2 = Double.parseDouble(editText_n2.getText().toString());
                    if (checkBox_dividir.isChecked() && !checkBox_multiplicar.isChecked() && !checkBox_sumar.isChecked() && !checkBox_restar.isChecked()){
                        if (n1 == 0 && n2 == 0){
                            Snackbar.make(button_evaluar,"ERROR!", Snackbar.LENGTH_SHORT).show();
                            textView_resultado.setText(String.valueOf("Error!"));
                        }
                        else if (n1 > 0 && n2 == 0)
                            textView_resultado.setText("Infinity");
                        else if (n1 < 0 && n2 == 0)
                            textView_resultado.setText("- Infinity");
                        else{
                            resultado = n1 / n2;
                            textView_resultado.setText(String.valueOf(resultado));
                        }

                    }else if (!checkBox_dividir.isChecked() && checkBox_multiplicar.isChecked() && !checkBox_sumar.isChecked() && !checkBox_restar.isChecked()){
                        resultado = n1 * n2;
                        textView_resultado.setText(String.valueOf(resultado));

                    }else if (!checkBox_dividir.isChecked() && !checkBox_multiplicar.isChecked() && checkBox_sumar.isChecked() && !checkBox_restar.isChecked()){
                        resultado = n1 + n2;
                        textView_resultado.setText(String.valueOf(resultado));

                    }else if (!checkBox_dividir.isChecked() && !checkBox_multiplicar.isChecked() && !checkBox_sumar.isChecked() && checkBox_restar.isChecked()){

                        Snackbar.make(button_evaluar,"entra", Snackbar.LENGTH_SHORT).show();
                        resultado = n1 - n2;
                        textView_resultado.setText(String.valueOf(resultado));

                    } else if (!checkBox_dividir.isChecked() && !checkBox_multiplicar.isChecked() && !checkBox_sumar.isChecked() && !checkBox_restar.isChecked())

                        Snackbar.make(button_evaluar,"Selecciona una operacion", Snackbar.LENGTH_SHORT).show();

                    else

                        Snackbar.make(button_evaluar,"Selecciona solo una operacion", Snackbar.LENGTH_SHORT).show();
                }else
                    Snackbar.make(button_evaluar,"Ingresa dos numeros", Snackbar.LENGTH_SHORT).show();

            }
        });


    }
}