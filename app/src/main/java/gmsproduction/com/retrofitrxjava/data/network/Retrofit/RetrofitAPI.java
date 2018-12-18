package gmsproduction.com.retrofitrxjava.data.network.Retrofit;


import gmsproduction.com.retrofitrxjava.Model.AllUsers;
import gmsproduction.com.retrofitrxjava.Model.Users;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Hima on 10/31/2018.
 */

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("users")
    Single<Users> addUsers(@Field("name")String name, @Field("job")String job);

    @GET("users?page=2")
    Observable<AllUsers> getUsers();

    @GET("users?page=2")
    Single<AllUsers> getUsers2();
}
