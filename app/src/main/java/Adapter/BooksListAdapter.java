package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;
import com.example.bookexchange1.UI.DetailsActivity;

import java.util.List;

import Fragments.HomeFragment;

import static androidx.core.content.ContextCompat.startActivity;

public class BooksListAdapter extends RecyclerView.Adapter <BooksListAdapter.ViewHolder>{
    Context context;
    List<Book> books;

    public BooksListAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //attaching layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.layout),parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //attaching  data one by one
        final Book book=books.get(position);
        holder.bookName.setText(book.getName());
        holder.bookImg.setBackgroundResource(book.getImage());
        holder.authorName.setText(book.getAuthor());
        holder.btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",book.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return books.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView bookImg;
        TextView bookName, authorName;
        Button btnExchange;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImg=itemView.findViewById(R.id.bookImg);
            bookName=itemView.findViewById(R.id.bookName);
            authorName=itemView.findViewById(R.id.authorName);
            btnExchange=itemView.findViewById(R.id.btnExchange);
        }
    }
}
