package news.cyprus.com.retrofit_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import news.cyprus.com.retrofit_example.Service.RestBuilderPro;
import news.cyprus.com.retrofit_example.Service.SplashClassItems;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RestBuilderPro.getService().authenticate("jins").enqueue(new Callback<SplashClassItems>() {
            @Override
            public void onResponse(Call<SplashClassItems> call, Response<SplashClassItems> response) {

                if (response.isSuccessful()) {
                    Log.e("succ", "success");
                } else {
                    Log.e("succ", response.code() + "");


                    Gson gson = new GsonBuilder().create();

                    SplashClassItems1 items = new SplashClassItems1();
                    items = gson.fromJson(response.errorBody().toString(), SplashClassItems1.class);

                    Log.e("succ", response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<SplashClassItems> call, Throwable t) {

                Log.e("succ", t.getMessage());
                ;
            }
        });

    }
}
