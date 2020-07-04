package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookexchange1.AddBookDialog;
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


    private RecyclerView myBooksRecylerView;
    MyBooksAdapter booksListAdapter;
    FloatingActionButton btnAddBook;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_books, container, false);
        myBooksRecylerView=view.findViewById(R.id.myBooksRecyclerView);
        btnAddBook=view.findViewById(R.id.btnAddbook);

        List<Book> books= new ArrayList<>();
        books.add(new Book("Catch 22","Joseph Heller",R.drawable.catch22));
        books.add( new Book("Muna Madan","Laxmi Prasad Devkota",R.drawable.munamadan));
        books.add(new Book("Brokeback Mountain","Annie Proulx",R.drawable.brokebackmountain));
        books.add(new Book("Seto Darti","Amar Neupane",R.drawable.sethodarthi));

        booksListAdapter = new MyBooksAdapter(getContext(),books);
        myBooksRecylerView.setAdapter(booksListAdapter);
        myBooksRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void openDialog()
    {
        AddBookDialog addBookDialog=new AddBookDialog();
        addBookDialog.show(getFragmentManager(),"add book");
    }
}