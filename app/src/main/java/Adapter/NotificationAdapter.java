package Adapter;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

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
                    .inflate((R.layout.notification_layout2),parent, false);
            return new CompletedView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {

        Picasso.with(context)
                .load(notifications.get(position).getBookImg())

                .into(  ((PendingView)holder).profileImageView);
        String txtAction = "<b>" + notifications.get(position).getSender() + "</b> requested you to exchange <b>" + notifications.get(position).getRequestedBook() + "</b>  with <b>" + notifications.get(position).getProposedBook() + "</b> ";
            ((PendingView)holder).txtAction.setText(Html.fromHtml(txtAction));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                long time = sdf.parse(notifications.get(position).getTime()).getTime();
                long now = System.currentTimeMillis();
                CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
                ((PendingView)holder).txtTime.setText(ago);
            } catch (ParseException e) {
                e.printStackTrace();
            }}
        else
        {
            Picasso.with(context)
                    .load(notifications.get(position).getBookImg())

                    .into(  ((CompletedView)holder).profileImageView);
            String txtAction = "<b>" + notifications.get(position).getSender() + "</b>  has accepted request to exchange <b>" + notifications.get(position).getRequestedBook() + "</b>  with <b>" + notifications.get(position).getProposedBook() + "</b> with you";
            ((CompletedView)holder).txtAction.setText(Html.fromHtml(txtAction));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                long time = sdf.parse(notifications.get(position).getTime()).getTime();
                long now = System.currentTimeMillis();
                CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
                ((CompletedView)holder).txtTime.setText(ago);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
