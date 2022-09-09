package com.example.phamthanhdat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phamthanhdat.entity.FeedBack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUser, edEmail,edDes;
    Spinner spinner;
    CheckBox ckAgree;
    Button btnSend;
    String status = "Good";
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        initView();
    }
    private void initView(){
        ckAgree = findViewById(R.id.ck);
        edUser = findViewById(R.id.edUser);
        edDes = findViewById(R.id.edDes);
        edEmail = findViewById(R.id.edEmail);
        spinner = findViewById(R.id.spinner);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        String[] listStatus = {"Good", "Bad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listStatus);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected: "+listStatus[i]);
                status = listStatus[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void onSend(){
        if (!validate()) {
            return;
        }
        FeedBack feedBack = new FeedBack();
        feedBack.username = edUser.getText().toString();
        feedBack.email = edEmail.getText().toString();
        feedBack.des =edDes.getText().toString();
        feedBack.status = status;
        long id = db.feedBackDao().insertFeed(feedBack);
        if (id > 0) {
            Toast.makeText(this, "You have" +""+db.feedBackDao().listAll().size()+""+"records", Toast.LENGTH_SHORT).show();
            goToMain();
        }

    }
    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private boolean validate() {
        String mes = null;
        if (edUser.getText().toString().trim().isEmpty()) {
            mes = " username required";
        }else if (edEmail.getText().toString().trim().isEmpty()) {
            mes = "email required";
        }else if (!ckAgree.isChecked()) {
            mes = "You must agree";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                onSend();
                break;
            default:
                break;
        }
    }
}