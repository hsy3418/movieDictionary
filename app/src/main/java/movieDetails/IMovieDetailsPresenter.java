package movieDetails;

/**
 * Created by siyuanhu on 15/6/17.
 */

public interface IMovieDetailsPresenter {
    void LoadTrailers(String apiKey,String language,int movieId);
    void LoadReviews(String apiKey,String language,int movieId);
}
