package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        myActivity=findViewById(R.id.myActivityRecyclerView);

        List<Notification> notifications=new ArrayList<>();
//        notifications.add(new Notification("Rajesh Hamal","Muna Madan","Catch 22","1 hours ago",R.drawable.book3,"completed"));
//        notifications.add(new Notification("Paul Walker","Catch 22","Hamlet","3 hours ago",R.drawable.catch22,"pending"));
//        notifications.add(new Notification("Axl Roses","Siris ko Phool","Seto Bagh","3 hours ago",R.drawable.book6,"completed"));
        Call<ResponseBody> call =exchangeAPI.myActivity(User.id);
        StrictModeClass.StrictMode();
        try{
            Response<ResponseBody> response =call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));

            //  String status = (jobj.getString("message"));

            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray =object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
                JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
                JSONObject request = jsonObject.getJSONObject("requested_to");
                JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
                JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
                JSONObject requestJSONObject = request.getJSONObject("data");
                String sender = requestJSONObject.getString("name");
                String requestedBook=bookWantedJSONObject.getString("name");
                String proposedBook=bookOfferedJSONObject.getString("name");
                String bookImg=bookOfferedJSONObject.getString("image");
                String time = jsonObject.getString("created_at");
                String status = jsonObject.getString("status");
                notifications.add(new Notification(id,sender,requestedBook,proposedBook,time,bookImg,status));
            }
            Toast.makeText(this, "s"+jsonArray.length(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        myActivityAdapter=new MyActivityAdapter(notifications,this);
        myActivity.setAdapter(myActivityAdapter);
        myActivity.setLayoutManager(new LinearLayoutManager(this));

//        NotificationAdapter    notificationAdapter=new NotificationAdapter(this,notifications);
//        myActivity.setAdapter(notificationAdapter);
//   myActivity.setLayoutManager(new LinearLayoutManager(this));

    }
}