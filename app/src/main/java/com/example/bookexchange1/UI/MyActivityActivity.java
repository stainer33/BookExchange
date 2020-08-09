package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bookexchange1.BLL.ExchangeBLL;
import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyActivityAdapter;
import Adapter.NotificationAdapter;
import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.exchangeAPI;

public class MyActivityActivity extends AppCompatActivity {
RecyclerView myActivity;
MyActivityAdapter myActivityAdapter;
ExchangeBLL exchangeBLL= new ExchangeBLL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        myActivity=findViewById(R.id.myActivityRecyclerView);

        List<Notification> notifications=new ArrayList<>();
        notifications=exchangeBLL.myActivity();
        myActivityAdapter=new MyActivityAdapter(notifications,this);
        myActivity.setAdapter(myActivityAdapter);
        myActivity.setLayoutManager(new LinearLayoutManager(this));



    }
}