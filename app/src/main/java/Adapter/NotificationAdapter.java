package Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Notification>notifications;
    final int VIEW_TYPE_ONE = 1;//pending
    final int VIEW_TYPE_TWO = 2;//completed
    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ONE){ View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.notification_layout),parent, false);
        return  new PendingView (view);}
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
            ((PendingView)holder).txtTime.setText(notifications.get(position).getTime());}
        else
        {
            Picasso.with(context)
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
        TextView txtAction,txtTime;
        public PendingView(@NonNull View itemView){
            super(itemView);
            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtAction=itemView.findViewById((R.id.txtAction));
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
