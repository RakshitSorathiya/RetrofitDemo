package demo.android.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ln-149 on 2/2/17.
 */

public class ShowMovieDetails extends AppCompatActivity {

    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_price;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        //Initializing Views
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_price = (TextView) findViewById(R.id.tv_price);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values by fetching from intent
        tv_id.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_ID, 0)));
        tv_name.setText(intent.getStringExtra(MainActivity.KEY_BOOK_NAME));
        tv_time.setText(intent.getStringExtra(MainActivity.KEY_BOOK_PRICE));
        tv_price.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_STOCK,0)));

    }

}
