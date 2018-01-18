package net.rokyinfo.insurance.retrofit;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import net.rokyinfo.insurance.entity.Ebike;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface RemoteApi {

    @POST("/api-pay/v3.1/gateway/create-pay-order-web")
    Call<ResponseBody> createPayOrder(@Header("X-USER-ID") String userId, @Query("payChannelId") Long payChannelId, @Query("payType") String payType,
                                                @Query("amount") double amount, @Query("orderId") String orderId, @Query("merchantNo") String merchantNo, @Body ChargeProductEntity productEntity);

    @POST("/api-pay/v3.1/gateway/create-pay-info-web")
    Call<ResponseBody> createPayInfo(@Query("payChannelId") Long payChannelId, @Query("payType") String payType,
                                     @Query("orderId") String orderId, @Query("merchantNo") String merchantNo, @Body ChargeProductEntity productEntity);

    @POST("/api-ebike/v3.1/ebikes/last-status")
    Call<List<Ebike>> getEbikeList(@Body RequestBody requestBody);

    @GET("/api-pay/v3.1/gateway/refund")
    Call<ResponseBody> refundByOrderNo(@Query("orderNo") String orderNo, @Query("merchantNo") String merchantNo);

}
