package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;
import com.example.bookexchange1.UI.DetailsActivity;

import java.util.List;

public class MyBooksAdapter extends RecyclerView.Adapter<MyBooksAdapter.ViewHolder>{
    Context context;
    List<Book> books;

    public MyBooksAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.my_books_layout),parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book book=books.get(position);
        holder.bookName.setText(book.getName());
        holder.bookImg.setBackgroundResource(book.getImage());
        holder.authorName.setText(book.getAuthor());

    }


    @Override
    public int getItemCount() {
        return books.size();
    }
    public  class ViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView bookImg;
        TextView bookName, authorName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg=itemView.findViewById(R.id.bookImg);
            bookName=itemView.findViewById(R.id.bookName);
            authorName=itemView.findViewById(R.id.authorName);
        }
        }
}
