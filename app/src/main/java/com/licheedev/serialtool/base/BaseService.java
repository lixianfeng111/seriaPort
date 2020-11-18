package com.licheedev.serialtool.base;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseService {
    @GET
    Observable<BaseEntity> doGet(@Url String url, @QueryMap Map<String, Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseEntity> doPost(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    @Multipart
    Observable<BaseEntity> upImage(@Url String url, @Part MultipartBody.Part image);

    @DELETE
    Observable<BaseEntity> doDelete(@Url String url, @QueryMap Map<String, Object> params);

    @PUT
    Observable<BaseEntity> doPut(@Url String url, @QueryMap Map<String, Object> params);
    @POST
    @FormUrlEncoded
    Observable<BaseEntity> doPost(@Url String url, @FieldMap Map<String, Object> params, @HeaderMap Map<String, Object> map);
    @GET
    Observable<BaseEntity> doGettwo(@Url String url);
}
