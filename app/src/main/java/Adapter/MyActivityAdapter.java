package Adapter;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
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
import com.example.bookexchange1.BLL.ExchangeBLL;
import com.example.bookexchange1.ContactDetail;
import com.example.bookexchange1.EditBookDialog;
import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {
            Picasso.with(context)
                    .load(notifications.get(position).getBookImg())

                    .into(  ((PendingView)holder).profileImageView);
            String txtAction = "You requested <b>" + notifications.get(position).getSender() + "</b> to exchange <b>" + notifications.get(position).getRequestedBook() + "</b>  with <b>" + notifications.get(position).getProposedBook() + "</b> ";
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
            }
            ((PendingView)holder).btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExchangeBLL exchangeBLL=new ExchangeBLL();
                   boolean res=  exchangeBLL.delete(notifications.get(position).getId());
                    if(res)
                    {notifications.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, notifications.size());
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();}
                }
            });

        }
        else
        {  Picasso.with(context)
                .load(notifications.get(position).getBookImg())

                .into(  ((CompletedView)holder).profileImageView);
            String txtAction = "You have accepted to  exchange <b>" + notifications.get(position).getRequestedBook() + "</b> with <b>" + notifications.get(position).getProposedBook() + "</b> " +" with <b>"+notifications.get(position).getSender() + "</b> "  ;
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
            ((CompletedView)holder).btnContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  ContactDetail contactDetail= new ContactDetail(notifications.get(position).getSenderEmail());
                    contactDetail.show(((AppCompatActivity)context).getSupportFragmentManager(),"tag");
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
        if (notifications.get(position).getStatus().equals("requested")) {
            return VIEW_TYPE_ONE;

        } else {
            return VIEW_TYPE_TWO;
        }
    }
    class PendingView extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView txtAction, txtTime;
        ImageButton  btnDelete;
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
        Button btnContact;
        public CompletedView(@NonNull View itemView) {
            super(itemView);

            profileImageView=itemView.findViewById(R.id.profileImageView);
            txtAction=itemView.findViewById(R.id.txtAction);
            txtTime=itemView.findViewById(R.id.txtTime);
            btnContact=itemView.findViewById(R.id.btnContact);
        }
    }
}
