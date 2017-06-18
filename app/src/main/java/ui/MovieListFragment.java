package ui;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.siyuanhu.moviedic.R;

import java.util.List;

import PlayingMovieList.IPlayingMovieView;
import PlayingMovieList.PlayingMoviePresenter;
import adapters.MoiveListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.PlayingMovie;
import models.Result;

/**
 * Created by siyuanhu on 5/06/2017.
 */

public class MovieListFragment extends Fragment implements IPlayingMovieView {


    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PlayingMoviePresenter playingMoviePresenter;
    private ProgressDialog progressDialog;
    private Callback callback;


    public interface Callback{
        void onMovieClicked(Result result);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        playingMoviePresenter = new PlayingMoviePresenter(this);
        mLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playingMoviePresenter.startLoadMovies("f851855e66226474444431466b91fda4","en-AU");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =null;
        if(view ==null){
            view = inflater.inflate(R.layout.fragment_movie_list,container,false);
        }   else{
            ((ViewGroup)view.getParent()).removeView(view);
        }
        ButterKnife.bind(view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onLoadSuccess(PlayingMovie playingMovieList) {

        mAdapter = new MoiveListAdapter(playingMovieList.getResults(), new MoiveListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Result result) {
                  callback.onMovieClicked(result);
//                MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
//                movieDetailsFragment.setResult(result);
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, movieDetailsFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();




    }

    @Override
    public void onLoadFailed(String message) {

        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }
}
