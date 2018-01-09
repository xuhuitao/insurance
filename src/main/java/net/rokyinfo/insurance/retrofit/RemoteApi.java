package net.rokyinfo.insurance.retrofit;

import io.reactivex.Flowable;
import net.rokyinfo.insurance.entity.ChargeProductEntity;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteApi {

    @POST("/v3.1/gateway/create-pay-order")
    Flowable<Response<ResponseBody>> createPayOrder(@Header("X-USER-ID") String userId, @Query("payChannelId") Long payChannelId, @Query("payType") String payType,
                                              @Query("amount") double amount, @Query("orderId") String orderId, @Body ChargeProductEntity productEntity);

}
