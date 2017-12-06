package news.cyprus.com.retrofit_example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import news.cyprus.com.retrofit_example.Service.RestBuilderPro;
import news.cyprus.com.retrofit_example.Service.SplashClassItems;
import news.cyprus.com.retrofit_example.databinding.HalloManBinding;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    HalloManBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.hallo_man);
     binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.hallo_man);

        RestBuilderPro.getService().authenticate("jins").enqueue(new Callback<SplashClassItems>() {
            @Override
            public void onResponse(Call<SplashClassItems> call, Response<SplashClassItems> response) {

                if (response.isSuccessful()) {
                    Log.e("succ", "success");
                } else {

                    ResponseBody responseBody=response.errorBody();
                    Gson gson=new GsonBuilder().create();
                    SplashClassItems obj=new SplashClassItems();
                    try {
                    obj=gson.fromJson(responseBody.string(),SplashClassItems.class);

                       // String res=responseBody.string();
                        Log.e("response body", obj.getMessage());
                       binding.subButton.setText(obj.getMessage());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


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
