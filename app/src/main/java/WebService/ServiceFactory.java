package WebService;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siyuanhu on 11/6/17.
 */

public class ServiceFactory {

    private static MovieService movieService;

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint).client(new OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).
                 addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
