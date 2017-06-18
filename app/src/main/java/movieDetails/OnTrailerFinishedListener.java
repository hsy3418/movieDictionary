package movieDetails;

import WebService.Review;
import models.PlayingMovie;
import models.ReviewList;
import models.Vedio;
import models.VedioResult;

/**
 * Created by siyuanhu on 15/6/17.
 */

public interface OnTrailerFinishedListener {
    void OnTrailerSuccessFinishedListener(Vedio vedio);

    void OnTrailerFailedFinishedListener(String message);

    void onReviewSuccessFinishedListner(ReviewList review);
    void OnReviewFailedinishedListener(String message);
}
