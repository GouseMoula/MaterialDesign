package com.recycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.materialdesign.R;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by compindia-fujitsu on 16-11-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MViewHolder> {
    private ArrayList<MoviesData> moviesData;
    private ItemClickListener mClickListener;
    private LayoutInflater inflater;
    private boolean switchToGrid;
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    public int lastPosition = -1;
    private Context context;
    public PopupWindow popupWindow;


    class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView movieName, year, director;

        public MViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            movieName = itemView.findViewById(R.id.item_title);
            year = itemView.findViewById(R.id.item_year);
            director = itemView.findViewById(R.id.item_director);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//            showPopup(view, getAdapterPosition());
            if (mClickListener != null) {
                Activity activity = (Activity) mClickListener;
                Intent intent = new Intent(activity, DetailedActivity.class);
                intent.putExtra("movieData", moviesData.get(getAdapterPosition()));

                Pair<View, String> p1 = Pair.create((View) imageView, "image");
                Pair<View, String> p2 = Pair.create((View) movieName, "title");
                Pair<View, String> p3 = Pair.create((View) year, "year");
                Pair<View, String> p4 = Pair.create((View) director, "director");
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2, p3, p4);

                activity.startActivity(intent, optionsCompat.toBundle());
            }
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                        view.findViewById(R.id.item_image), "image");
        }
    }


    public RecyclerAdapter(Context context, ArrayList<MoviesData> moviesData) {
        this.moviesData = moviesData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType == LIST_ITEM ? R.layout.list_item_recycler : R.layout.grid_item_recycler, parent, false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        Glide.with(context).load(moviesData.get(position).getMovieImage())
                .apply(bitmapTransform(new CircleCrop()))
                .into(holder.imageView);
//        holder.imageView.setImageResource(moviesData.get(position).getMovieImage());
        holder.movieName.setText(moviesData.get(position).getMovieName());
        holder.director.setText(moviesData.get(position).getMovieDirector());
        holder.year.setText(moviesData.get(position).getMovieYear());
//        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (switchToGrid) {
            return GRID_ITEM;
        }
        return LIST_ITEM;
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    void setSwitchListGrid(boolean isGrid) {
        switchToGrid = isGrid;
    }

//    private void setAnimation(View viewToAnimate, int position) {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (viewToAnimate.getAnimation() != null) {
//            viewToAnimate.clearAnimation();
//        }
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }

//    @Override
//    public void onViewDetachedFromWindow(final MViewHolder holder) {
//
//        holder.imageView.getRootView().clearAnimation();
//    }

//    public boolean closePopUp() {
//        if (popupWindow.isShowing()) {
//            popupWindow.dismiss();
//            return false;
//        }
//        return true;
//    }

//    public void showPopup(View view, int position) {
//        View popupView = inflater.inflate(R.layout.detailed_screen, null);
//
//        popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        ImageView image = popupView.findViewById(R.id.item_image);
//        TextView title = popupView.findViewById(R.id.item_title);
//        TextView year = popupView.findViewById(R.id.item_year);
//        TextView director = popupView.findViewById(R.id.item_director);
//
//        image.setImageResource(moviesData.get(position).getMovieImage());
//        title.setText(moviesData.get(position).getMovieName());
//        year.setText(moviesData.get(position).getMovieYear());
//        director.setText(moviesData.get(position).getMovieDirector());
//
//        ImageView btnDismiss = popupView.findViewById(R.id.item_image_back);
//        btnDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        popupWindow.showAsDropDown(popupView, 0, 0);
//    }

}
