package dias_plbtw.com.asikinaja.APIs;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import dias_plbtw.com.asikinaja.Helper.ToStringConverter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Dias on 2/28/2017.
 */
public class RestClient {
    private static APIList api;
    private static String baseUrl = "http://ibacor.com";

    public static APIList getClient() {
        if (api == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = client.create(APIList.class);
        }
        return api;
    }
}
