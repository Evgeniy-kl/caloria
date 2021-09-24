package com.example.caloria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
    private String[] SpinnerItems = {"Малоподвижный образ жизни",
                                    "Обычная физнагрузка",
                                    "Интенсивная физнагрузка"};
    public String Gender;
    public double BMR;
    public double AMR;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView selection = (TextView) findViewById(R.id.editTextNumberYears);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SpinnerItems);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String) parent.getItemAtPosition(position);
                if (item.equals("Малоподвижный образ жизни"))
                {
                    AMR = 1.2;
                }
                if (item.equals("Обычная физнагрузка"))
                {
                    AMR = 1.55;
                }
                if (item.equals("Интенсивная физнагрузка"))
                {
                    AMR = 1.9;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.selectionFemale:
                if (checked){
                    Gender= "Male";
                }
                break;
            case R.id.selectionMale:
                if (checked){
                    Gender= "Female";
                }
                break;
        }
    }


    public void onMyButtonClick(View view) {

        try {
            int height;
            TextView h = (TextView) findViewById(R.id.editTextNumberHeight);
            height = Integer.parseInt(h.getText().toString());


            int weight;
            TextView w = (TextView) findViewById(R.id.editTextNumberWeight);
            weight = Integer.parseInt(w.getText().toString());

            int years;
            TextView y = (TextView) findViewById(R.id.editTextNumberYears);
            years = Integer.parseInt(y.getText().toString());


            if (Gender.equals("Female")) {
                BMR = 655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * years);
            } else if (Gender.equals("Male")) {
                BMR = 66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * years);
            }

            Toast toast = Toast.makeText(this, Double.toString(BMR * AMR), Toast.LENGTH_LONG);
            toast.show();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }

        }
    }
