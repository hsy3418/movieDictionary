package movieDetails;

/**
 * Created by siyuanhu on 15/6/17.
 */

public interface IMovieDetailsInteractor {

    void loadTrailers(String apiKey,String language,int id);
    void loadReviews(String apiKey,String language,int id);
}
