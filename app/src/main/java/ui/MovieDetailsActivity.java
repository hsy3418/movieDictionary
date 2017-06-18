package ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.siyuanhu.moviedic.R;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;


import models.Result;
import util.Constants;

/**
 * Created by siyuanhu on 12/6/17.
 */

public class MovieDetailsActivity extends AppCompatActivity {


    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras !=null && extras.containsKey(Constants.MOVIE)){
                Result result = extras.getParcelable(Constants.MOVIE);
                if(result!=null){
                    MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(result);
                movieDetailsFragment.setResult(result);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, movieDetailsFragment);
                transaction.commit();
                }
            }
        }

        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);

    }

    @Override
    public void onBackPressed() {
        helper.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
