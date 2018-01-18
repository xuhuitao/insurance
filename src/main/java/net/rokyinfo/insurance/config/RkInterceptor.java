package net.rokyinfo.insurance.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
/**
 * @author: yangyang.cao
 * @date : 2018-01-18 13:06
 **/
public class RkInterceptor implements Interceptor {

    private static final String FIRM_FIELD = "firm";

    //rq的base64编码
    private static final String RQ_BASE64 = "cnE=";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request()
                .newBuilder()
                .addHeader(FIRM_FIELD, RQ_BASE64)
                .build();
        Response response = chain.proceed(request);

        return response;

    }

}
