package PlayingMovieList;

import org.reactivestreams.Subscriber;

import java.io.IOException;

import WebService.MovieService;
import WebService.ServiceFactory;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import models.PlayingMovie;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by siyuanhu on 5/06/2017.
 */

public class PlayingMovieInteractor  {

    private OnMovieFinishedListener onMovieFinishedListener;

    public PlayingMovieInteractor(OnMovieFinishedListener onMovieFinishedListener) {
        this.onMovieFinishedListener = onMovieFinishedListener;
    }




    public void loadRecentCommits(String api,String language) {
        
        MovieService movieService = ServiceFactory.createRetrofitService(MovieService.class,MovieService.SERVICE_ENDPOINT);

        movieService.getMovie(api,language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<PlayingMovie>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super PlayingMovie> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PlayingMovie playingMovie) {
                        onMovieFinishedListener.OnMovieSuccessFinishedListener(playingMovie);
                    }

                    @Override
                    public void onError(Throwable t) {
                        onMovieFinishedListener.OnMovieFailedFinishedListener(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    

}
