package com.example.bookexchange1.UI;

import com.example.bookexchange1.BLL.UserBLL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test
    public void testLogin() {
        UserBLL userBLL = new UserBLL();
//        final Call<ResponseBody> call = userAPI.login("test@test.com", "test");
//
////        StrictModeClass.StrictMode();
//        try {
//            Response<ResponseBody> response = call.execute();
//            final Response<ResponseBody> finalResponse = response;
//            int value = response.code();
////            JSONObject jobj = new JSONObject((response.body().string()));
        boolean result = userBLL.login("sujit@gmail.com", "12345678");
        assertEquals(true, result);

//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    }
}