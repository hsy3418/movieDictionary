package movieDetails;

import models.Result;
import models.ReviewList;
import models.Vedio;

/**
 * Created by siyuanhu on 15/6/17.
 */

public interface IMovieDetailsView {
    void showTrailers(Vedio vedio);
    void showReviews(ReviewList reviewList);
    void showDetails(Result movie);
    void showTrailersFailed(String message);
    void showReviewsFailed(String message);

}
