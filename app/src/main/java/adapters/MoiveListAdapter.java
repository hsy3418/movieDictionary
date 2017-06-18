package adapters;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siyuanhu.moviedic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.PlayingMovie;
import models.Result;

/**
 * Created by siyuanhu on 5/06/2017.
 */

public class MoiveListAdapter extends RecyclerView.Adapter<MoiveListAdapter.ViewHolder> {

    private List<Result> playingMovies;
    private Context context;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(Result result);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Result result,final OnItemClickListener onItemClickListener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(result);
                }
            });
        }
    }

    public MoiveListAdapter(List<Result> playingMovies,OnItemClickListener onItemClickListener) {
        this.playingMovies = playingMovies;
        this.listener = onItemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MoiveListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.movie_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(playingMovies.get(position).getTitle());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342"+playingMovies.get(position).getPosterPath()).into(holder.poster);
        holder.bind(playingMovies.get(position),listener);

    }





    @Override
    public int getItemCount() {
        return playingMovies.size();
    }
}
