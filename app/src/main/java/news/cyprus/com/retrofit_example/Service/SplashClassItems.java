package news.cyprus.com.retrofit_example.Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 12/6/2017.
 */

public class SplashClassItems {
    @SerializedName("status")
            @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
