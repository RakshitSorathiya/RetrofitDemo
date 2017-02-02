package demo.android.retrofitdemo;

import java.util.List;

import demo.android.retrofitdemo.model.MovieModel;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by ln-149 on 1/2/17.
 */

interface MovieApi {


    @GET("/RetrofitExample/books.json")
    public void getMovies(Callback<List<MovieModel>> response);
}
