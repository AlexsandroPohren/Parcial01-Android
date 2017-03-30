package com.example.pauloneide.parcial1;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Preferences extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    public void onClick(View view){

        Intent intent = new Intent(this, Preferences.class);

        startActivity(intent);
    }
    public class Salvar extends AppCompatActivity implements View.OnClickListener{

        private String filename = "MySampleFile.txt";
        private String filepath = "MyFileStorage";
        File myInternalFile;
        File myExternalFile;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_preferences);

            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
            myInternalFile = new File(directory, filename);

            Button SalvarInterno = (Button) findViewById(R.id.button5);
            SalvarInterno.setOnClickListener(this);


            Button SalvarExterno = (Button) findViewById(R.id.button7);
            SalvarExterno.setOnClickListener(this);


            if (! isExternalStorageAvailable() || isExternalStorageReadOnly()) {
                SalvarExterno.setEnabled(false);
            }else{
                myExternalFile = new File(getExternalFilesDir(filepath), filename);
            }


        }

        public boolean isExternalStorageReadOnly(){
            String extStorageState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
                return  true;
            }
            return false;
        }

        public boolean isExternalStorageAvailable(){
            String extStorageState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(extStorageState)){
                return true;
            }
            return false;
        }


        @Override
        public void onClick(View v) {
            EditText myInputText = (EditText) findViewById(R.id.editText);
            TextView responseText = (TextView) findViewById(R.id.textView3);
            String myData = "";

            switch (v.getId()) {
                case R.id.button5:
                    try {
                        FileOutputStream fos = new FileOutputStream(myInternalFile);
                        fos.write(myInputText.getText().toString().getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myInputText.setText("");
                    responseText.setText("Salvar no Armazenamento Interno ...");
                    break;


                case R.id.button7:
                    try {
                        FileOutputStream fos = new FileOutputStream(myExternalFile);
                        fos.write(myInputText.getText().toString().getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myInputText.setText("");
                    responseText.setText("Salvar no armazenamento Externo ...");
                    break;


            }
        }
    }

}
