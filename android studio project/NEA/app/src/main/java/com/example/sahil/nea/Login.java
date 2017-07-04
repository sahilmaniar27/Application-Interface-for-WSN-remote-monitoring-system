package com.example.sahil.nea;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        EditText username = (EditText)findViewById(R.id.editText);
        EditText password = (EditText)findViewById(R.id.editText2);
        String usr_str = username.getText().toString();
        String pass_str = password.getText().toString();
        String usr_check = "anupama";
        String pass_check = "nea";
        if(username.getText() != null && password.getText() != null) {
            if (usr_str.equals(usr_check) && pass_str.equals(pass_check)) {
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                finish();
            }else{
                // Username or password false, display and an error
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                dlgAlert.setMessage("wrong password or username");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
            }

        }else{
            AlertDialog.Builder dlgAlert2  = new AlertDialog.Builder(this);

            dlgAlert2.setMessage("Enter both username and password");
            dlgAlert2.setTitle("Error Message...");
            dlgAlert2.setPositiveButton("OK", null);
            dlgAlert2.setCancelable(true);
            dlgAlert2.create().show();

            dlgAlert2.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }


    }
}


