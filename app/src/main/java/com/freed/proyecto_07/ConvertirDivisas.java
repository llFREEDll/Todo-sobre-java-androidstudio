package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConvertirDivisas extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button button_evaluar;
    private EditText editText_decimal;
    private TextView textView_resltado;
    private double resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertir_divisas);

        radioGroup = findViewById(R.id.radioGroup);
        button_evaluar = findViewById(R.id.button_convertir);
        editText_decimal = findViewById(R.id.editTextNumberDecimal);
        textView_resltado = findViewById(R.id.textView_resultado);

        button_evaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.radioButton_PesosDolar){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) / 22.73;
                    textView_resltado.setText(String.valueOf(resultado));
                }else if (id == R.id.radioButton_DolarPesos){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) * 22.73;
                    textView_resltado.setText(String.valueOf(resultado));
                }else if (id == R.id.radioButton_PesosEuros){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) / 25.79;
                    textView_resltado.setText(String.valueOf(resultado));
                }else if (id == R.id.radioButton_EurosPesos){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) * 25.79;
                    textView_resltado.setText(String.valueOf(resultado));
                }else if (id == R.id.radioButton_EurosDolares){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) * 1.13;
                    textView_resltado.setText(String.valueOf(resultado));
                }else if (id == R.id.radioButton_DolaresEuros){
                    resultado = Double.parseDouble(editText_decimal.getText().toString()) * 0.88;
                    textView_resltado.setText(String.valueOf(resultado));
                }


            }
        });
    }
}