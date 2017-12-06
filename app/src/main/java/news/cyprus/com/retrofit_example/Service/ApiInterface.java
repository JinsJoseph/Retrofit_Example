package news.cyprus.com.retrofit_example.Service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sreelal on 6/12/17.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/pravasi-registration")
    Call<SplashClassItems> authenticate
            (@Field("name") String tag);

}
