package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleSwitch extends AppCompatActivity {

    private ToggleButton toggleButton;
    private Switch aSwitch;
    private TextView textView_toggle;
    private TextView textView_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_switch);

        toggleButton = findViewById(R.id.toggleButton);
        aSwitch = findViewById(R.id.switch1);
        textView_switch = findViewById(R.id.textView_swicht);
        textView_toggle = findViewById(R.id.textView_toggle);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    textView_toggle.setText("On");
                }else
                    textView_toggle.setText("Off");
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    textView_switch.setText("On");
                }else
                    textView_switch.setText("Off");
            }
        });

    }
}