package WebService;







import java.util.List;

import io.reactivex.Observable;
import models.PlayingMovie;
import models.ReviewList;
import models.Vedio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by siyuanhu on 5/06/2017.
 */

public interface MovieService {

    String SERVICE_ENDPOINT = "https://api.themoviedb.org/3/";

    @GET("movie/now_playing/")
    Observable<PlayingMovie> getMovie(@Query("api_key") String apiKey, @Query("language") String language);
    @GET("movie/{movie_id}/videos")
    Observable<Vedio> getMovieTrailers(@Path("movie_id") int movieId,@Query("api_key") String apiKey, @Query("language") String language);
    @GET("movie/{movie_id}/reviews")
    Observable<ReviewList> getMovieReviews(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

}
