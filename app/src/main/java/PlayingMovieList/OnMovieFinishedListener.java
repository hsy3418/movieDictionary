package PlayingMovieList;

import java.util.List;
import java.util.logging.ErrorManager;

import models.PlayingMovie;


/**
 * Created by siyuanhu on 5/06/2017.
 */

public interface OnMovieFinishedListener {
    void OnMovieSuccessFinishedListener(PlayingMovie movie);

    void OnMovieFailedFinishedListener(String message);


}
