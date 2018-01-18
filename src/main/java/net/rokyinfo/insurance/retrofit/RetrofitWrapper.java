package net.rokyinfo.insurance.retrofit;

import com.google.gson.Gson;
import net.rokyinfo.insurance.config.RkInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

@Component
public class RetrofitWrapper {

    @Value("${remote.service.url}")
    private String remoteUrl;

    private Retrofit getRetrofit(String url){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RkInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        return retrofit;
    }

    public RemoteApi getRemoteApi() {
        return getRetrofit(remoteUrl).create(RemoteApi.class);
    }

}
