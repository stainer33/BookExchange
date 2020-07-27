package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.NotificationAdapter;


public class NotificationFragment extends Fragment {


    public NotificationFragment() {
        // Required empty public constructor
    }


   RecyclerView notificationRecyclerView;
    NotificationAdapter notificationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);

        notificationRecyclerView=view.findViewById(R.id.notificationRecyclerView);

        List<Notification> notifications=new ArrayList<>();
//        notifications.add(new Notification("Rajesh Hamal","Muna Madan","Catch 22","1 hours ago",R.drawable.book3,"completed"));
//        notifications.add(new Notification("Paul Walker","Catch 22","Hamlet","3 hours ago",R.drawable.catch22,"completed"));
//        notifications.add(new Notification("Axl Roses","Siris ko Phool","Seto Bagh","3 hours ago",R.drawable.book6,"completed"));

        notificationAdapter=new NotificationAdapter(getContext(),notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}