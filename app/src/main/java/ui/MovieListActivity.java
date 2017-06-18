package ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Movie;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.siyuanhu.moviedic.R;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import models.Result;
import util.Constants;

public class MovieListActivity extends AppCompatActivity implements MovieListFragment.Callback {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
    }


    @Override
    public void onMovieClicked(Result result) {
        startMovieDetailsActivity(result);
    }


    private void startMovieDetailsActivity(Result result){
        Intent intent= new Intent(this,MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE,result);
        intent.putExtras(extras);
        SwipeBackActivityHelper.activityBuilder(MovieListActivity.this)
                .intent(intent)
                .needParallax(true)
                .needBackgroundShadow(true)
                // .fitSystemWindow(true) // status bar height
                // .prepareView(swipeRefreshLayout)
                // see http://stackoverflow.com/questions/29356607/android-swiperefreshlayout-cause-recyclerview-not-update-when-take-screenshot
                .startActivity();


    }
}
