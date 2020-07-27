package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookexchange1.AddBookDialog;
import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.BooksListAdapter;
import Adapter.MyBooksAdapter;


public class MyBooksFragment extends Fragment {



    public MyBooksFragment() {
        // Required empty public constructor
    }

    List<Book> books;
    private RecyclerView myBooksRecylerView;
    MyBooksAdapter booksListAdapter;
    FloatingActionButton btnAddBook; EditText etSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view =inflater.inflate(R.layout.fragment_my_books, container, false);
        myBooksRecylerView=view.findViewById(R.id.myBooksRecyclerView);
        btnAddBook=view.findViewById(R.id.btnAddbook);
        etSearch=view.findViewById(R.id.etSearch);

        BookBLL bookBLL= new BookBLL();
        books= bookBLL.getMyBook();
        booksListAdapter = new MyBooksAdapter(getContext(),books);
        myBooksRecylerView.setAdapter(booksListAdapter);
        myBooksRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        return view;
    }
    private void filter(String text) {
        List<Book>filteredList=new ArrayList<>();

        for(Book book: books)
        {
            if(book.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(book);
            }
        }
        booksListAdapter.filterList(filteredList);
    }
    public void openDialog()
    {
        AddBookDialog addBookDialog=new AddBookDialog();
        addBookDialog.show(getFragmentManager(),"add book");
    }
}