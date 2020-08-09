package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.API.ExchangeAPI;
import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.BLL.ExchangeBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private Spinner bookListSpinner;
    private TextView txtName, txtAuthor,txtDes,txtOwner;
    private ImageView bookImg; private Button btnExchange; int bookId;
    ExchangeBLL exchangeBLL=new ExchangeBLL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        bookListSpinner=  findViewById(R.id.bookListspinner);
        txtName=findViewById(R.id.txtName);
        txtAuthor=findViewById(R.id.txtAuthor);
        txtDes=findViewById(R.id.txtDescritpion);
        txtOwner=findViewById(R.id.txtOwner);
        bookImg=findViewById(R.id.bookImg);
        btnExchange=findViewById(R.id.btnExchange);
        Intent intent=getIntent();
        final Book book=(Book)intent.getSerializableExtra("book");
       // Toast.makeText(this, "dd"+book.getId(), Toast.LENGTH_SHORT).show();
       // String[] books = {"select a book", "Seto Baagh", "Siris ko phool", "Catch 22", "Lolita", "Hmalet" };
        List<String>books=new ArrayList<>();
        final List<Integer>ids=new ArrayList<>();
        BookBLL bookBLL=new BookBLL();
        List<Book>bookList=bookBLL.getMyBook();
        for(int i=0;i<bookList.size();i++)
        {
            ids.add(bookList.get(i).getId());
            books.add(bookList.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bookListSpinner.setAdapter(adapter);
bookListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 bookId= ids.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        final Integer userId=book.getOwner().getId();
        txtDes.setText(book.getDes());

        txtOwner.setText("owner: "+book.getOwner().getName());
        Picasso.with(this)
                .load("http://10.0.2.2:8000/storage/books/July2020/"+book.getImage())

                .into(bookImg);
btnExchange.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        boolean res =exchangeBLL.add(book.getId(),bookId,userId, User.id,"requested");
        if(res)
        {Toast.makeText(DetailsActivity.this, "Request send successfully" , Toast.LENGTH_SHORT).show();}
        else
        {
            Toast.makeText(DetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
});
    }

}