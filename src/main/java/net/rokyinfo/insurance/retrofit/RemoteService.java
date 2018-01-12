package net.rokyinfo.insurance.retrofit;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import net.rokyinfo.insurance.entity.Ebike;
import net.rokyinfo.insurance.util.JacksonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;

@Component
public class RemoteService {

    @Autowired
    private RetrofitWrapper retrofitWrapper;

    public String createPayOrder(String userId, Long payChannelId, String payType, double amount, String orderId, ChargeProductEntity productEntity) throws IOException {
        Call<ResponseBody> createPayOrderCall = retrofitWrapper.getOrderApi().createPayOrder(userId, payChannelId,
                payType, amount, orderId, "88880000000000000002", productEntity);
        Response<ResponseBody> createPayOrderResp = createPayOrderCall.execute();
        if (createPayOrderResp.isSuccessful()) {
            return createPayOrderResp.body().string();
        }
        return null;
    }

    public List<Ebike> getEbikeList(List<String> ccuList) throws IOException {
        RequestBody rawBody = RequestBody.create(MediaType.parse("application/json"), JacksonUtil.toJSon(ccuList));
        Call<List<Ebike>> ebikeList = retrofitWrapper.getEbikeApi().getEbikeList(rawBody);
        Response<List<Ebike>> response = ebikeList.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

}
