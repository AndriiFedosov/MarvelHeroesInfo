package com.example.shink.marvelheroesinfo.activity.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shink.marvelheroesinfo.R;
import com.example.shink.marvelheroesinfo.activity.detail.DetailActivity;
import com.example.shink.marvelheroesinfo.activity.main.adapters.CharacterAdapter;
import com.example.shink.marvelheroesinfo.activity.main.adapters.OnItemClickListener;
import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.utils.Constants;
import com.example.shink.marvelheroesinfo.utils.Utils;
import com.example.shink.marvelheroesinfo.view.main.MainPresenter;
import com.example.shink.marvelheroesinfo.view.main.impl.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClickListener<Character>,MainView {

    //annotated view with butterknife annotation
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewHeroes)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //get fields to create activity
    private CharacterAdapter adapter;
    private MainPresenter mainPresenter;
    private GridLayoutManager layoutManager;
    private boolean isLoading;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init butterknife
        ButterKnife.bind(this);
        //init toolbar
        setToolbar();
        //geinit recycler view
        setRecyclerView();
        //init main present
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.onCreate();
        //lok at connection
        if (Utils.isConnected(this)) {
            //get characters frm intrnet
            mainPresenter.getCharacters(adapter.getItemCount());
        } else {
            //get charters from local repo
            mainPresenter.getLocalCharacters();
        }
        isLoading = true;
    }

    //destroy activity
    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        isLoading = false;
        super.onDestroy();
    }

    //get charter in row
    public void setItems(List<Character> items, int total) {
        isLoading = false;
        this.total = total;
        adapter.addItems(items);
    }

    //show progress bar
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    //hide progressbar
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    //create error message
    public void showErrorMessage(String error) {
        isLoading = false;
        Toast.makeText(this, R.string.no_connection, Toast.LENGTH_SHORT).show();
        mainPresenter.getLocalCharacters();

    }

    //create on item clicked
    @Override
    public void onItemClicked(int position, Character item) {
        //create intent
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.CHARACTER_ID, item.getId());
        intent.putExtra(Constants.CHARACTER_NAME, item.getName());
        intent.putExtra(Constants.CHARACTER_PHOTO, item.getThumbnail());
        startActivity(intent);
    }

    //create recycler view
    private void setRecyclerView() {
        //create character adapter
        adapter = new CharacterAdapter(new ArrayList<Character>(), this);
        //set on row one character
        recyclerView.setAdapter(adapter);
        //create layout manager
        layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        //clear on scrooling
        recyclerView.clearOnScrollListeners();
        //add row if scrool
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //create new roow
                switch (newState) {
                    //get scrool state
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //get visible character count
                        int visibleItemCount = layoutManager.getChildCount();
                        //get all character count
                        int totalItemCount = layoutManager.getItemCount();
                        //get visible intems
                        int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                        if (pastVisiblesItems + visibleItemCount >= totalItemCount) {
                            if (!isLoading && total != totalItemCount) {
                                isLoading = true;
                                //lok at the internet conection
                                if (Utils.isConnected(MainActivity.this)) {
                                    //get charterss from JSON
                                    mainPresenter.getCharacters(totalItemCount);
                                }
                                else{
                                    //show error massage
                                    showErrorMessage(getString(R.string.no_connection));
                                }
                            }
                        }
                        break;
                }
            }
        });
    }

    //show toolbar
    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }
}