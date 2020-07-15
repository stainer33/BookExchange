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
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;

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
            return new PendingView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {
            ((PendingView)holder).txtReceiver.setText(notifications.get(position).getSender());
            ((PendingView)holder).profileImageView.setBackgroundResource(notifications.get(position).getProfileImg());
            ((PendingView)holder).txtRequestedBook.setText(notifications.get(position).getRequestedBook());
            ((PendingView)holder).txtProposedBook.setText(notifications.get(position).getProposedBook());
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

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    @Override
    public int getItemViewType(int position){
        if (notifications.get(position).getStatus().equals("pending")) {
            return VIEW_TYPE_ONE;

        } else {
            return VIEW_TYPE_TWO;
        }
    }
    class PendingView extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtReceiver, txtRequestedBook,txtProposedBook,txtTime;
        ImageButton btnEdit, btnDelete;
        public PendingView(@NonNull View itemView) {
            super(itemView);

            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtReceiver=itemView.findViewById(R.id.txtReceiver);
            txtRequestedBook=itemView.findViewById(R.id.txtRequestedBook);
            txtProposedBook=itemView.findViewById(R.id.txtProposedBook);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
    class CompletedView extends  RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtReceiver, txtRequestedBook,txtProposedBook,txtTime;
        public CompletedView(@NonNull View itemView) {
            super(itemView);

            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtReceiver=itemView.findViewById(R.id.txtReceiver);
            txtRequestedBook=itemView.findViewById(R.id.txtRequestedBook);
            txtProposedBook=itemView.findViewById(R.id.txtProposedBook);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
}
