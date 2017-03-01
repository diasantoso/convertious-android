package dias_plbtw.com.asikinaja.APIs;

import dias_plbtw.com.asikinaja.Models.APIBaseResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Dias on 2/28/2017.
 */
public interface APIList {

    @GET("/api/youtube-converter")
    Call<APIBaseResponse> getConvert(@Query("u") String url, @Query("k") String key);
}
