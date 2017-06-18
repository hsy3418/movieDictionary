package PlayingMovieList;

import java.util.List;

import models.PlayingMovie;

/**
 * Created by siyuanhu on 5/06/2017.
 */

public interface IPlayingMovieView  {
    void onLoadSuccess(PlayingMovie playingMovie);
    void onLoadFailed(String message);
}
