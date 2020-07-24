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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.BooksListAdapter;
import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.bookAPI;


public class HomeFragment extends Fragment {

    List<Book>books;

    public HomeFragment() {
        // Required empty public constructor
    }
    TextView test;

    private RecyclerView booksRecylerView; EditText etSearch;
    BooksListAdapter booksListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        booksRecylerView=view.findViewById(R.id.booksRecyclerView);
        etSearch=view.findViewById(R.id.etSearch);
        test=view.findViewById(R.id.test);

        BookBLL bookBLL= new BookBLL();

       books= bookBLL.getAllBook();
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