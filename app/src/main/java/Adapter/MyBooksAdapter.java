package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.AddBookDialog;
import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.EditBookDialog;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Book book=books.get(position);
        holder.bookName.setText(book.getName());
        Picasso.with(context)
                .load(book.getImage())

                .into(holder.bookImg);
    //    holder.bookImg.setBackgroundResource(book.getImage());
        holder.authorName.setText(book.getAuthor());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookBLL bookBLL=new BookBLL();
               boolean res = bookBLL.delete(book.getId());
               if(res)
               {
                   books.remove(position);
                   notifyItemRemoved(position);
                   notifyItemRangeChanged(position, books.size());
                   Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(context, "Couldn't delete", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }


    public void openDialog()
    {
        EditBookDialog editBookDialog= new EditBookDialog();
        editBookDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"tag");
    }
    @Override
    public int getItemCount() {
        return books.size();
    }

    public void filterList(List<Book> filteredList) {
        books = filteredList;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView bookImg;
        TextView bookName, authorName;
         ImageButton btnEdit, btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg=itemView.findViewById(R.id.bookImg);
            bookName=itemView.findViewById(R.id.bookName);
            authorName=itemView.findViewById(R.id.authorName);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
        }
}
