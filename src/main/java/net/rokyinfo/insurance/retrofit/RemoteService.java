package net.rokyinfo.insurance.retrofit;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;

@Component
public class RemoteService {

    @Autowired
    private RetrofitWrapper retrofitWrapper;

    public String createPayOrder(String userId, Long payChannelId, String payType, double amount, String orderId, ChargeProductEntity productEntity) throws IOException {
        Call<ResponseBody> createPayOrderCall = retrofitWrapper.getRemoteApi().createPayOrder(userId, payChannelId,
                payType, amount, orderId, productEntity);
        Response<ResponseBody> createPayOrderResp = createPayOrderCall.execute();
        if (createPayOrderResp.isSuccessful()) {
            return createPayOrderResp.body().string();
        }
        return null;
    }

}
