package ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siyuanhu.moviedic.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import PlayingMovieList.PlayingMoviePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Result;
import models.ReviewList;
import models.ReviewResult;
import models.Vedio;
import models.VedioResult;
import movieDetails.IMovieDetailsView;
import movieDetails.MovieDetailsPresenter;
import util.Constants;

/**
 * Created by siyuanhu on 11/6/17.
 */

public class MovieDetailsFragment extends Fragment implements IMovieDetailsView,View.OnClickListener {

    private Result result;


    private CollapsingToolbarLayout collapsingToolbar;
    private LinearLayout trailers;
    private TextView movieTitle;
    private ImageView imageView;
    private  TextView movieOverview;
    private MovieDetailsPresenter movieDetailsPresenter;
    private HorizontalScrollView horizontalScrollView;
    private TextView trailersLabel;
    private   LayoutInflater inflater;

    @BindView(R.id.reviews_label)
    TextView reviewLabel;
    @BindView(R.id.reviews)
    LinearLayout reviews;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scores)
    TextView scores;
    @BindView(R.id.release_date)
    TextView releaseDate;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsPresenter = new MovieDetailsPresenter(this);
        inflater = getActivity().getLayoutInflater();



    }

    public static MovieDetailsFragment getInstance(@NonNull Result movie)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE, movie);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =null;
        if(view ==null){
            view = inflater.inflate(R.layout.fragment_movie_details,container,false);
        }   else{
            ((ViewGroup)view.getParent()).removeView(view);
        }
        ButterKnife.bind(this,view);
        trailers = (LinearLayout) view.findViewById(R.id.trailers);
        movieTitle = (TextView) view.findViewById(R.id.movie_title);
        imageView = (ImageView) view.findViewById(R.id.movie_image);
        movieOverview = (TextView) view.findViewById(R.id.movie_overview);
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.trailers_container);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        trailersLabel = (TextView) view.findViewById(R.id.trailers_label);
        setToolBar();
        showDetails(result);
        return view;
    }

    private void setToolBar(){
        collapsingToolbar.setTitle("Movie Details");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);


        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else
        {
            // Don't inflate. Tablet is in landscape mode.
        }

    }



    @Override
    public void showTrailers(Vedio vedio) {
        List<VedioResult> vedioResults = vedio.getResults();

        Picasso picasso = Picasso.with(getContext());
        trailersLabel.setVisibility(View.VISIBLE);
        horizontalScrollView.setVisibility(View.VISIBLE);
        for(VedioResult vedioResult:vedioResults){
         View thumbContainer = inflater.inflate(R.layout.video,this.trailers,false);
         ImageView thumbView = (ImageView) thumbContainer.findViewById(R.id.video_thumb);
            thumbView.setTag(VedioResult.getUrl(vedioResult));//set tag of the thumbView with the vedio url,then the tag can be retrieved in the on click
            thumbView.requestLayout();
            thumbView.setOnClickListener(this);
            picasso
                    .load(VedioResult .getThumbnailUrl(vedioResult))
                    .resizeDimen(R.dimen.video_width, R.dimen.video_height)
                    .centerCrop()
                    .into(thumbView);
            this.trailers.addView(thumbContainer);

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieDetailsPresenter.LoadTrailers("f851855e66226474444431466b91fda4","en-AU",this.result.getId());
        movieDetailsPresenter.LoadReviews("f851855e66226474444431466b91fda4","en-AU",this.result.getId());
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showReviews(ReviewList reviewList) {
        if(reviewList==null){
            reviewLabel.setVisibility(View.GONE);
            reviews.setVisibility(View.GONE);

        }else {
            this.reviews.setVisibility(View.VISIBLE);
            this.reviewLabel.setVisibility(View.GONE);
            this.reviews.removeAllViews();
           // LayoutInflater inflater = getActivity().getLayoutInflater();
            List<ReviewResult> reviewResults = reviewList.getResults();
            for(ReviewResult reviewResult:reviewResults){
                ViewGroup reviewGroup = (ViewGroup) inflater.inflate(R.layout.review,reviews,false);
                TextView reviewAuthor = ButterKnife.findById(reviewGroup,R.id.review_author);
                TextView reviewContent = ButterKnife.findById(reviewGroup,R.id.review_content);
                reviewAuthor.setText(reviewResult.getAuthor());
                reviewContent.setText(reviewResult.getContent());
                reviewContent.setOnClickListener(this);
                reviews.addView(reviewGroup);

            }
        }
    }

    @Override
    public void showDetails(Result movie) {
        if(result!=null) {
            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w780"+result.getBackdropPath()).into(imageView);
            movieOverview.setText(result.getOverview());
            movieTitle.setText(result.getTitle());
            scores.setText("scores: " + result.getVoteCount());
            releaseDate.setText("release date: " + result.getReleaseDate());
        }
    }

    @Override
    public void showTrailersFailed(String message) {
        Toast.makeText(getContext(),"Trailer failed to retrieve",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showReviewsFailed(String message) {
        Toast.makeText(getContext(),"Review failed to retrieve",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.review_content:onReviewClick((TextView) v);break;
            case R.id.video_thumb:onVideoClick((ImageView) v);break;

        }
    }


    private void onReviewClick(TextView view){
        if(view.getMaxLines()==5){
            view.setMaxLines(100);
        }else{
            view.setMaxLines(5);
        }
    }

    private void onVideoClick(ImageView view){
        String videoUrl = (String) view.getTag();//get tag which is the url
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(playVideoIntent);

    }
}
