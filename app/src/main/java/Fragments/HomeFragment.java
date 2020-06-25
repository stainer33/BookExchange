package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BooksListAdapter;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView booksRecylerView;
    BooksListAdapter booksListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        booksRecylerView=view.findViewById(R.id.booksRecyclerView);


        List<Book>books= new ArrayList<>();
      books.add( new Book("Muna Madan","Laxmi Prasad Devkota",R.drawable.munamadan));
      books.add(new Book("Seto Darti","Amar Neupane",R.drawable.sethodarthi));
      books.add(new Book("Brokeback Mountain","Annie Proulx",R.drawable.brokebackmountain));
      books.add(new Book("Catch 22","Joseph Heller",R.drawable.catch22));
         booksListAdapter = new BooksListAdapter(getContext(),books);
        booksRecylerView.setAdapter(booksListAdapter);
    booksRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        return  view;
        return view;
    }
}