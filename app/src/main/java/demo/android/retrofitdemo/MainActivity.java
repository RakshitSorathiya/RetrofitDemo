package demo.android.retrofitdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import demo.android.retrofitdemo.model.MovieModel;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {


    //Root URL of our web service
    public static final String ROOT_URL = "http://api.androidhive.info/contacts/";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_MOVIE_ID = "key_movie_id";
    public static final String KEY_MOVIE_NAME = "key_movie_name";
    public static final String KEY_MOVIE_TIME = "key_movie_price";
    public static final String KEY_MOVIE_PRICE = "key_movie_stock";

    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<MovieModel> movielist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv_movies);

        //Calling the method that will fetch data
        getMovies();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);
    }

    private void getMovies() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        MovieApi api = adapter.create(MovieApi.class);

        //Defining the method
        api.getMovies(new Callback<List<MovieModel>>() {
            @Override
            public void success(List<MovieModel> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                movielist = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    //Our method to show list
    private void showList() {
        //String array to store all the book names
        String[] items = new String[movielist.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < movielist.size(); i++) {
            //Storing names to string array
            items[i] = movielist.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Creating an intent
        Intent intent = new Intent(this, ShowMovieDetails.class);

        //Getting the requested book from the list
        MovieModel movie = movielist.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_MOVIE_ID, movie.getId());
        intent.putExtra(KEY_MOVIE_NAME, movie.getName());
        intent.putExtra(KEY_MOVIE_TIME, movie.getTime());
        intent.putExtra(KEY_MOVIE_PRICE, movie.getPrice());

        //Starting another activity to show book details
        startActivity(intent);

    }
}
