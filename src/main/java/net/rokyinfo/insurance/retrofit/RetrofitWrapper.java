package net.rokyinfo.insurance.retrofit;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

@Component
public class RetrofitWrapper {

    @Value("${remote.url.order}")
    private String remoteUrlOrder;

    @Value("${remote.url.ebike}")
    private String remoteUrlEbike;

    private Retrofit getRetrofit(String url){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    private RemoteApi getRemoteApi(String url){
        return getRetrofit(url).create(RemoteApi.class);
    }

    public RemoteApi getOrderApi() {
        return getRemoteApi(remoteUrlOrder);
    }

    public RemoteApi getEbikeApi() {
        return getRemoteApi(remoteUrlEbike);
    }

}
