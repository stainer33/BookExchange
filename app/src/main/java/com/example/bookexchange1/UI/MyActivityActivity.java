package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyActivityAdapter;
import Adapter.NotificationAdapter;

public class MyActivityActivity extends AppCompatActivity {
RecyclerView myActivity;
MyActivityAdapter myActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        myActivity=findViewById(R.id.myActivityRecyclerView);

        List<Notification> notifications=new ArrayList<>();
        notifications.add(new Notification("Rajesh Hamal","Muna Madan","Catch 22","1 hours ago",R.drawable.book3,"completed"));
        notifications.add(new Notification("Paul Walker","Catch 22","Hamlet","3 hours ago",R.drawable.catch22,"pending"));
        notifications.add(new Notification("Axl Roses","Siris ko Phool","Seto Bagh","3 hours ago",R.drawable.book6,"completed"));

        myActivityAdapter=new MyActivityAdapter(notifications,this);
        myActivity.setAdapter(myActivityAdapter);
        myActivity.setLayoutManager(new LinearLayoutManager(this));

//        NotificationAdapter    notificationAdapter=new NotificationAdapter(this,notifications);
//        myActivity.setAdapter(notificationAdapter);
//   myActivity.setLayoutManager(new LinearLayoutManager(this));

    }
}