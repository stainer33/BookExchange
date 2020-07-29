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
import android.widget.Toast;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.NotificationAdapter;
import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.bookAPI;
import static com.example.bookexchange1.URL.URL.exchangeAPI;


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
        Call<ResponseBody> call =exchangeAPI.notification(User.id);
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
            String status = jsonObject.getString("status");
            JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
            JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
            JSONObject request = jsonObject.getJSONObject("requested_by");
            JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
            JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
            JSONObject requestJSONObject = request.getJSONObject("data");
            String sender = requestJSONObject.getString("name");
            String requestedBook=bookWantedJSONObject.getString("name");
            String proposedBook=bookOfferedJSONObject.getString("name");
            String bookImg=bookOfferedJSONObject.getString("image");
            String time = jsonObject.getString("created_at");
            notifications.add(new Notification(id,sender,requestedBook,proposedBook,time,bookImg,status));
            }
        }
           catch (Exception ex)
            {
                ex.printStackTrace();
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

//        notifications.add(new Notification("Rajesh Hamal","Muna Madan","Catch 22","1 hours ago",R.drawable.book3,"completed"));
//        notifications.add(new Notification("Paul Walker","Catch 22","Hamlet","3 hours ago",R.drawable.catch22,"completed"));
//        notifications.add(new Notification("Axl Roses","Siris ko Phool","Seto Bagh","3 hours ago",R.drawable.book6,"completed"));

        notificationAdapter=new NotificationAdapter(getContext(),notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}