package movieDetails;

import WebService.MovieService;
import WebService.Reviews;
import WebService.ServiceFactory;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import models.ReviewList;
import models.Vedio;

/**
 * Created by siyuanhu on 15/6/17.
 */

public class MovieDetailsInteractor implements IMovieDetailsInteractor {

    private OnTrailerFinishedListener trailerFinishedListener;
    private MovieService movieService;

    public MovieDetailsInteractor(OnTrailerFinishedListener trailerFinishedListener) {
        this.trailerFinishedListener = trailerFinishedListener;
        this.movieService = ServiceFactory.createRetrofitService(MovieService.class,MovieService.SERVICE_ENDPOINT);
    }

    @Override
    public void loadTrailers(String apiKey, String language, int id) {

        movieService.getMovieTrailers(id,apiKey,language).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<Vedio>() {
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
                    protected void subscribeActual(Observer<? super Vedio> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Vedio value) {
                        trailerFinishedListener.OnTrailerSuccessFinishedListener(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        trailerFinishedListener.OnTrailerFailedFinishedListener(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadReviews(String apiKey, String language, int id) {
        movieService.getMovieReviews(id,apiKey,language).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<ReviewList>() {
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
                    protected void subscribeActual(Observer<? super ReviewList> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReviewList value) {
                        trailerFinishedListener.onReviewSuccessFinishedListner(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        trailerFinishedListener.OnTrailerFailedFinishedListener(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
