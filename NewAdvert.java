//Robert Bajan 17/05/22
package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lostandfound.data.DatabaseHelper;
import com.example.lostandfound.model.Item;

public class NewAdvert extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText editTxtName, editTxtPhone, editTxtDesc, editTxtDate, editTxtLocation;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        radioGroup = findViewById(R.id.radioGroup);

        editTxtName = findViewById(R.id.editTxtName);
        editTxtPhone = findViewById(R.id.editTxtPhone);
        editTxtDesc = findViewById(R.id.editTxtDesc);
        editTxtDate = findViewById(R.id.editTxtDate);
        editTxtLocation = findViewById(R.id.editTxtLocation);

        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                String type = radioButton.getText().toString();
                String name = editTxtName.getText().toString();
                String phone = editTxtPhone.getText().toString();
                String desc = editTxtDesc.getText().toString();
                String date = editTxtDate.getText().toString();
                String location = editTxtLocation.getText().toString();

                long result = db.insertItem(new Item(type, name, phone, desc, date, location));

                if(result > 0){
                    Toast.makeText(NewAdvert.this, "Successful Post", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(NewAdvert.this, ItemList.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(NewAdvert.this, "Post Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}