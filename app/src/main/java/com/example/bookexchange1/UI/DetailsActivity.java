package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bookexchange1.R;

public class DetailsActivity extends AppCompatActivity {
    Spinner bookListSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String[] books = {"select a book", "Seto Baagh", "Siris ko phool", "Catch 22", "Lolita", "Hmalet" };
        bookListSpinner=  findViewById(R.id.bookListspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bookListSpinner.setAdapter(adapter);
    }
}