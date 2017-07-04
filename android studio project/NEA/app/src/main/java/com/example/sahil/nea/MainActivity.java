package com.example.sahil.nea;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "buckysMessage";
    String query_signal = "Query";
    String query_latest_signal = "Query_latest";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText ET = (EditText)findViewById(R.id.editText3);
        Calendar mcurrentTime = Calendar.getInstance();
        final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minute = mcurrentTime.get(Calendar.MINUTE);
        ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ET.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Current Time for Latest ");
                mTimePicker.show();

            }
        });

    }

    public void fetch(View view){
        String method = "query_message";

        Calendar mcurrentTime = Calendar.getInstance();
        final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minute = mcurrentTime.get(Calendar.MINUTE);
        EditText ET = (EditText)findViewById(R.id.editText3);


        String ip = ET.getText().toString();
        String current= hour +":" + minute;
        if (ip.equals(current)){
            ip="latest";
        }
        BackgroundTask backgroundTask = new BackgroundTask(this);
        if(ip.equals("latest")){
            backgroundTask.execute(method,query_latest_signal,ip);
        }else{
            backgroundTask.execute(method,query_signal,ip);
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

