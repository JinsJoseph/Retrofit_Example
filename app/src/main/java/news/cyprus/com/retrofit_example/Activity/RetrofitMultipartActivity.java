package news.cyprus.com.retrofit_example.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import news.cyprus.com.retrofit_example.Model.Registerd_User_Response;
import news.cyprus.com.retrofit_example.R;
import news.cyprus.com.retrofit_example.Service.RestBuilderPro;
import news.cyprus.com.retrofit_example.Helper.SelectedFilePath;
import news.cyprus.com.retrofit_example.databinding.HalloManBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RetrofitMultipartActivity extends AppCompatActivity {
    private static final int GALLERYPICK = 23;
    HalloManBinding binding;
    private String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.hallo_man);

       binding= DataBindingUtil.setContentView(RetrofitMultipartActivity.this, R.layout.hallo_man);

          binding.subButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(imagepath==null)
                  {return;}
                    File propertyImageFile = new File(imagepath);



        RequestBody propertyImage = RequestBody.create(MediaType.parse("*/*"), propertyImageFile);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("resume", propertyImageFile.getName(), propertyImage);
        HashMap<String, RequestBody> partmap=new HashMap<>();
        partmap.put("email", createRequestBody("sreelal8s02ss5ssss@gm,ail.com"));
        partmap.put("name", createRequestBody("namesdsddddf"));
        partmap.put("mobile",createRequestBody("7747453389"));
        partmap.put("designation",createRequestBody("2"));
        partmap.put("current_city",createRequestBody("2"));
        partmap.put("password", createRequestBody("123456"));
        partmap.put("experience",createRequestBody ("16"));
        partmap.put("confirm_password", createRequestBody("123456"));




                  RestBuilderPro.getService().authenticate(propertyImagePart,partmap).enqueue(new Callback<Registerd_User_Response>() {
                      @Override
                      public void onResponse(Call<Registerd_User_Response> call, retrofit2.Response<Registerd_User_Response> response) {

                          if(response.isSuccessful())
                          {
                            Registerd_User_Response  respo=response.body();
                            String imageurl=respo.getMessage();
                            Log.e("imageurl",imageurl);
                          }else{
                              ResponseBody responseBody=response.errorBody();
                              try {
                                  Log.e("success",responseBody.string());
                                  Toast.makeText(RetrofitMultipartActivity.this, responseBody.string(), Toast.LENGTH_SHORT).show();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          }

                      }

                      @Override
                      public void onFailure(Call<Registerd_User_Response> call, Throwable t) {
                          Log.e("error",t.getMessage());
                          Toast.makeText(RetrofitMultipartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                      }
                  });





              }
          });
binding.subButton1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERYPICK);
        }catch (Exception e){
            // showsnackbar("Something went wrong");
            e.printStackTrace();}




    }
});


    }
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static RequestBody createRequestBody(@NonNull String s) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         switch(requestCode) {

             case GALLERYPICK:
                 if(resultCode == RESULT_OK){

                     try {
                         Uri pickedImage = data.getData();
                         if (null != pickedImage) {
                             // Get the path from the Uri
                             String path= SelectedFilePath.getPath(RetrofitMultipartActivity.this,pickedImage);


                             File file = new File(path);


                             imagepath = ""+file.getAbsolutePath();

                             Log.e("filepath",imagepath);


                         }


                     }catch (Exception e){e.printStackTrace();}
                 }


         }

    }
}
