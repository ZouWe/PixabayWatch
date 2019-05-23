package com.example.daggerdemo;

import entity.RequestInfo;
import entity.VideoRequestInfo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageAPI {
    @GET("api")
    Observable<RequestInfo> getRequestInfo(@Query("key") String key,
                                           @Query("q") String q,
                                           @Query("lang") String lang,
                                           @Query("image_type") String imageType,
                                           @Query("category") String category,
                                           @Query("page") int page,
                                           @Query("per_page") int perPage);

    @GET("api/videos")
    Observable<VideoRequestInfo> getVideoRequestInfo(@Query("key") String key,
                                                     @Query("q") String q,
                                                     @Query("lang") String lang,
                                                     @Query("video_type") String VideoType,
                                                     @Query("category") String category,
                                                     @Query("page") int page,
                                                     @Query("per_page") int perPage);
}
