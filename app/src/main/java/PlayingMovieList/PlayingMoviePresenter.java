package PlayingMovieList;

import models.PlayingMovie;


/**
 * Created by siyuanhu on 5/06/2017.
 */

public class PlayingMoviePresenter implements OnMovieFinishedListener {

    private IPlayingMovieView view;
    private PlayingMovieInteractor playingMovieInteractor;

    public PlayingMoviePresenter(IPlayingMovieView view) {
        this.playingMovieInteractor = new PlayingMovieInteractor(this);
        this.view = view;
    }

    public void startLoadMovies(String api,String language){
        playingMovieInteractor.loadRecentCommits(api,language);
    }

    @Override
    public void OnMovieSuccessFinishedListener(PlayingMovie movie) {
        this.view.onLoadSuccess(movie);
    }

    @Override
    public void OnMovieFailedFinishedListener(String message) {
        this.view.onLoadFailed(message);
    }
}
