package co.infinum.academy.danijel_sokac.boatit.Network;

import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.CommentList;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Models.RateBoat;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
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

    @Headers("Content-Type: application/json")
    @POST("/api/v1/users/register")
    void register(@Body TypedString registerData,Callback<LoginResponse> callback);

    @GET("/api/v1/posts")
    void getBoats(@Query("token") String token, @Query("page") int page,
                  @Query("per_page") int perPage ,Callback<AllBoats> callback);

    @GET("/api/v1/posts/{id}/upboat")
    void getUpboat(@Path("id") int id, @Query("token") String token, Callback<RateBoat> callback);

    @GET("/api/v1/posts/{id}/downboat")
    void getDownboat(@Path("id") int id, @Query("token") String token, Callback<RateBoat> callback);

    @GET("/api/v1/posts/{post_id}/comments")
    void getComments(@Path("post_id") int postId,
                     @Query("token") String token,
                     @Query("page") int page,
                     @Query("per_page") int perPage,
                     Callback<CommentList> comments);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/posts/{post_id}/comments")
    void sendComment(@Path("post_id") int postId,
                     @Query("token") String token,
                     @Body TypedString comment,
                     Callback<Comment> response);
}
