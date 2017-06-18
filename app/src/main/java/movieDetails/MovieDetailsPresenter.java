package movieDetails;

import WebService.Review;
import models.ReviewList;
import models.Vedio;

/**
 * Created by siyuanhu on 15/6/17.
 */

public class MovieDetailsPresenter implements IMovieDetailsPresenter,OnTrailerFinishedListener {

    private IMovieDetailsView view;
    private MovieDetailsInteractor interactor;

    public MovieDetailsPresenter(IMovieDetailsView view) {
        this.view = view;
        interactor = new MovieDetailsInteractor(this);
    }

    @Override
    public void LoadTrailers(String apiKey, String language, int movieId) {
        interactor.loadTrailers(apiKey,language,movieId);
    }

    @Override
    public void LoadReviews(String apiKey, String language, int movieId) {
        interactor.loadReviews(apiKey,language,movieId);
    }

    @Override
    public void OnTrailerSuccessFinishedListener(Vedio vedio) {
        this.view.showTrailers(vedio);
    }

    @Override
    public void OnTrailerFailedFinishedListener(String message) {
        this.view.showTrailersFailed(message);
    }

    @Override
    public void onReviewSuccessFinishedListner(ReviewList review) {
        this.view.showReviews(review);
    }

    @Override
    public void OnReviewFailedinishedListener(String message) {
        this.view.showReviewsFailed(message);
    }
}
