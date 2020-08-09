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

import com.example.bookexchange1.BLL.ExchangeBLL;
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
      ExchangeBLL exchangeBLL = new ExchangeBLL();
      notifications=exchangeBLL.notification();

        notificationAdapter=new NotificationAdapter(getContext(),notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}