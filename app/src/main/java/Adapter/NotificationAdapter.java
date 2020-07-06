package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;

import java.text.Normalizer;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    Context context;
    List<Notification>notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.notification_layout),parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        Notification notification=notifications.get(position);
        holder.profileImageView.setBackgroundResource(notification.getProfileImg());
        holder.txtSender.setText(notification.getSender());
        holder.txtRequestedBook.setText(notification.getRequestedBook());
        holder.txtProposedBook.setText(notification.getProposedBook());
        holder.txtTime.setText(notification.getTime());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtSender, txtRequestedBook, txtProposedBook,txtTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtSender=itemView.findViewById((R.id.txtSender));
            txtProposedBook=itemView.findViewById(R.id.txtProposedBook);
            txtRequestedBook=itemView.findViewById(R.id.txtRequestedBook);
            txtTime=itemView.findViewById(R.id.txtTime);
        }
    }
}
