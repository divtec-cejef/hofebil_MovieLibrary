package com.example.movielibrary.APIMovie;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/popular")
    Call<MovieResults> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    //TODO TEMP TEST
    @GET("movie/{movie_id}")
    Call<BasicMovie> getMovie(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );
}