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

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BooksListAdapter;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }
    List<Book>books;
    private RecyclerView booksRecylerView; EditText etSearch;
    BooksListAdapter booksListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        booksRecylerView=view.findViewById(R.id.booksRecyclerView);
        etSearch=view.findViewById(R.id.etSearch);

       books = new ArrayList<>();
      books.add( new Book("Muna Madan","Laxmi Prasad Devkota",R.drawable.munamadan));
      books.add(new Book("Seto Darti","Amar Neupane",R.drawable.sethodarthi));
      books.add(new Book("Brokeback Mountain","Annie Proulx",R.drawable.brokebackmountain));
      books.add(new Book("Catch 22","Joseph Heller",R.drawable.catch22));
         booksListAdapter = new BooksListAdapter(getContext(),books);
        booksRecylerView.setAdapter(booksListAdapter);
    booksRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

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



//        return  view;
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

}