package minocha.rishabh.shopifychallenge.API;

import minocha.rishabh.shopifychallenge.POJO.DataObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rishabh on 08/05/18.
 */

public interface ApiCall {
    @GET("admin/orders.json")
    Call<DataObject> getData (@Query("page") String page, @Query("access_token") String access_token);
}
