package net.rokyinfo.insurance.retrofit;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.UUID;

@Component
public class CreatePayOrderWrapper {

    private static final int RETRY_COUNT = 3;

    private int retryCount;

    @Autowired
    private RetrofitWrapper retrofitWrapper;

    public String createPayOrder(String userId, Long payChannelId, String payType, double amount, ChargeProductEntity productEntity) throws IOException {
        retryCount++;
        Call<ResponseBody> createPayOrderCall = retrofitWrapper.getRemoteApi().createPayOrder(userId, payChannelId, payType, amount, generateOrderNo(), productEntity);
        Response<ResponseBody> createPayOrderResp = createPayOrderCall.execute();
        if (createPayOrderResp.isSuccessful()) {
            return createPayOrderResp.body().string();
        }
        if (retryCount <= RETRY_COUNT) {
            return createPayOrder(userId, payChannelId, payType, amount, productEntity);
        }
        return null;
    }

    private String generateOrderNo() {
        return UUID.randomUUID().toString();
    }

}
