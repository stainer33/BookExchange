package Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Notification>notifications;
    private Context context;

    public MyActivityAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    final int VIEW_TYPE_ONE = 1;//pending
    final int VIEW_TYPE_TWO = 2;//completed
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ONE)
        {View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.request_layout),parent, false);
            return new PendingView(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate((R.layout.request_layout2),parent, false);
            return new CompletedView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {
            Picasso.with(context)
                    .load("http://10.0.2.2:8000/storage/books/July2020/"+notifications.get(position).getBookImg())

                    .into(  ((PendingView)holder).profileImageView);
            String txtAction = "<b>" + notifications.get(position).getSender() + "</b> " +" requested you to exchange " +"<b>" + notifications.get(position).getRequestedBook() + "</b> " +" with "+"<b>" + notifications.get(position).getProposedBook() + "</b> ";
            ((PendingView)holder).txtAction.setText(Html.fromHtml(txtAction));
            ((PendingView)holder).txtTime.setText(notifications.get(position).getTime());
            ((PendingView)holder).btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
            ((PendingView)holder).btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {  Picasso.with(context)
                .load("http://10.0.2.2:8000/storage/books/July2020/"+notifications.get(position).getBookImg())

                .into(  ((CompletedView)holder).profileImageView);
            String txtAction = "<b>" + notifications.get(position).getSender() + "</b> " +" requested you to exchange " +"<b>" + notifications.get(position).getRequestedBook() + "</b> " +" with "+"<b>" + notifications.get(position).getProposedBook() + "</b> ";
            ((CompletedView)holder).txtAction.setText(Html.fromHtml(txtAction));
            ((CompletedView)holder).txtTime.setText(notifications.get(position).getTime());
        }

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    @Override
    public int getItemViewType(int position){
        if (notifications.get(position).getStatus().equals("requested")) {
            return VIEW_TYPE_ONE;

        } else {
            return VIEW_TYPE_TWO;
        }
    }
    class PendingView extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtAction, txtTime;
        ImageButton btnEdit, btnDelete;
        public PendingView(@NonNull View itemView) {
            super(itemView);

            btnDelete=itemView.findViewById(R.id.btnDelete);
            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtAction=itemView.findViewById(R.id.txtAction);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
    class CompletedView extends  RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtAction,txtTime;
        public CompletedView(@NonNull View itemView) {
            super(itemView);

            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtAction=itemView.findViewById(R.id.txtAction);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
}
