package news.cyprus.com.retrofit_example.Service;

import java.util.HashMap;

import news.cyprus.com.retrofit_example.Model.Registerd_User_Response;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by sreelal on 6/12/17.
 */

public interface ApiInterface {

    @Multipart
    @POST("api/signup")
    Call<Registerd_User_Response> authenticate
            (@Part MultipartBody.Part propertyImage, @PartMap HashMap<String, RequestBody> hashMap);

}
