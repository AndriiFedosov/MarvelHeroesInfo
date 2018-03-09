package com.example.shink.marvelheroesinfo.activity.detail;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shink.marvelheroesinfo.R;
import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.utils.Constants;
import com.example.shink.marvelheroesinfo.utils.Utils;
import com.example.shink.marvelheroesinfo.view.details.DetailPresent;
import com.example.shink.marvelheroesinfo.view.details.impl.DetailPresentImpl;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by shink on 08.03.2018.
 */

//create detail information activity
public class DetailActivity extends AppCompatActivity implements DetailView  {


    private static final String STATE_ID = "stateId";
    private static final String STATE_NAME = "stateName";
    private static final String STATE_PATH = "statePath";

    //annotated element of view with butterknife annotation
    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.description)
    TextView txtDescription;
    @BindView(R.id.comics)
    TextView txtComics;
    @BindView(R.id.events)
    TextView txtEvents;
    @BindView(R.id.series)
    TextView txtSeries;
    @BindView(R.id.stories)
    TextView txtStories;

    private DetailPresent detailPresenter;
    private int id;
    private String name;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //create butterknife
        ButterKnife.bind(this);
        //create intent
        Intent intent = getIntent();
        //get parameter of intent
        name = intent.getStringExtra(Constants.CHARACTER_NAME);
        imgPath = intent.getStringExtra(Constants.CHARACTER_PHOTO);
        id = intent.getIntExtra(Constants.CHARACTER_ID, 0);
        setToolbar(name, imgPath);
        //create detail information present
        detailPresenter = new DetailPresentImpl(this);
        detailPresenter.onCreate();
        if (id > 0) {
            //look for connection to internet
            if (Utils.isConnected(this)) {
                //return remote character
                detailPresenter.getCharacter(id);
            } else {
                //return locl character
                detailPresenter.getLocalCharacter(id);
            }
        }

    }

    @Override
    protected void onDestroy() {
        detailPresenter.onDestroy();
        super.onDestroy();
    }


    public void setItem(Character item) {
        //create character on activity
        toolbar.setTitle(item.getName());
        //get description of character
        if (item.getDescription().isEmpty()) {
            txtDescription.setText(R.string.no_description);
        } else {
            txtDescription.setText(item.getDescription());
        }
        Resources resources = getResources();
        //create another information about character
        int comics = item.getComicsDto().getAvailable();
        //get comics
        txtComics.setText(resources.getQuantityString(R.plurals.ComicsAvailable, comics, comics));
        //get series
        txtSeries.setText(resources.getQuantityString(R.plurals.SeriesAvailable,
                item.getSeriesDto().getAvailable(),
                item.getSeriesDto().getAvailable()));
        //getevents
        txtEvents.setText(resources.getQuantityString(R.plurals.EventAvailable,
                item.getEventsDto().getAvailable(),
                item.getEventsDto().getAvailable()));
        //get stories
        txtStories.setText(resources.getQuantityString(R.plurals.HistoriesAvailable,
                item.getStoriesDto().getAvailable(),
                item.getStoriesDto().getAvailable()));

        //get photo of char
        if (!String.valueOf(item.getThumbnail()).isEmpty()) {
            Glide.with(imgAvatar.getContext())
                    .load(item.getThumbnail())
                    .crossFade()
                    .centerCrop()
                    .into(imgAvatar);
        }
        //create bar wih comics
        if (comics > 4) {
            Utils.createNotification(item.getId(), item, this);
        }


    }
    //show progress bar
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    //hide progress bar
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    //show error massage
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        detailPresenter.getLocalCharacter(id);

    }
    //create toolbar
    private void setToolbar(String title, String imgPath) {
        if (title.isEmpty()) {
            toolbar.setTitle(R.string.app_name);
        } else {
            toolbar.setTitle(title);
        }
        //create photo of hero with glide lib
        if (!imgPath.isEmpty()) {
            Glide.with(imgAvatar.getContext())
                    .load(imgPath)
                    .crossFade()
                    .centerCrop()
                    .into(imgAvatar);
        }
        setSupportActionBar(toolbar);
    }

    //save state if change activity or close your app
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_ID, id);
        outState.putString(STATE_NAME, name);
        outState.putString(STATE_PATH, imgPath);
        super.onSaveInstanceState(outState);

    }
    //get state after closed activity
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getInt(STATE_ID);
        name = savedInstanceState.getString(STATE_NAME);
        imgPath = savedInstanceState.getString(STATE_PATH);
    }
}