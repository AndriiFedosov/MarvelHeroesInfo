package com.example.shink.marvelheroesinfo.activity.main.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shink.marvelheroesinfo.R;
import com.example.shink.marvelheroesinfo.db.entity.Character;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shink on 08.03.2018.
 */
//create character adapter
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> characters;
    private OnItemClickListener<Character> clickListener;


    public CharacterAdapter(List<Character> characters, OnItemClickListener<Character> itemClickListener) {
        this.characters = characters;
        this.clickListener = itemClickListener;
    }

    //create view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);
        return new ViewHolder(view);
    }

    //create bind view holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        holder.setCharacter(characters.get(index));
        holder.bindViewHolder();
    }
    //return count of heroes
    @Override
    public int getItemCount() {
        return characters.size();
    }

    //add char after refresh or scrooll down
    public void addItems(List<Character> characters) {
        int size = this.characters.size();
        this.characters.addAll(characters);
        notifyItemRangeInserted(size, this.characters.size());
    }

    //create view of row hero
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //get photo
        @BindView(R.id.imgAvatar)
        ImageView photo;
        //get name of hero
        @BindView(R.id.txtName)
        TextView name;

        private Character character;

        //create view holder of hero row
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ((CardView) view).setPreventCornerOverlap(false);
            view.setOnClickListener(this);
        }

        public void bindViewHolder() {
            //render info about hero
            //get name of character
            name.setText(character.getName());
            //get photo of hero
            photo.setImageResource(R.mipmap.ic_launcher);
            //crop photo and set in center of area
            photo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            //get image path to photo in api
            String imgPath = String.valueOf(character.getThumbnail());
            if (!imgPath.isEmpty()) {
                Glide.with(photo.getContext())
                        .load(imgPath)
                        .crossFade()
                        .centerCrop()
                        .into(photo);
            }
        }


        //create method on click
        @Override
        @OnClick
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClicked(getAdapterPosition(), character);
        }
        //set character on view
        public void setCharacter(Character character) {
            this.character = character;
        }


    }
}