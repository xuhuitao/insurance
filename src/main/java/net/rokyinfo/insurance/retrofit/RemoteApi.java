package net.rokyinfo.insurance.retrofit;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import net.rokyinfo.insurance.entity.Ebike;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface RemoteApi {

    @POST("/v3.1/gateway/create-pay-order")
    Call<ResponseBody> createPayOrder(@Header("X-USER-ID") String userId, @Query("payChannelId") Long payChannelId, @Query("payType") String payType,
                                                @Query("amount") double amount, @Query("orderId") String orderId, @Query("merchantNo") String merchantNo, @Body ChargeProductEntity productEntity);


    @POST("/v3.1/ebikes/last-status")
    Call<List<Ebike>> getEbikeList(@Query("ccuArray") String ccuArray);

}
