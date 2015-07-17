package co.infinum.academy.danijel_sokac.boatit.Network;

import org.json.JSONObject;

import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 13.7.2015..
 */
public interface BoatitService {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/users/login")
    void login(@Body TypedString loginData,Callback<LoginResponse> callback);

    @GET("/api/v1/posts")
    void getBoats(@Query("token") String token, @Query("page") int page,
                  @Query("per_page") int perPage ,Callback<AllBoats> callback);
}
