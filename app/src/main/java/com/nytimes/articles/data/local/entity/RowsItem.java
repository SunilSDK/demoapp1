package com.nytimes.articles.data.local.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.nytimes.articles.R;
import com.squareup.picasso.Picasso;

@Entity
public class RowsItem{

	@PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	private long id;

	@SerializedName("imageHref")
	private String imageHref;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public void setImageHref(String imageHref){
		this.imageHref = imageHref;
	}

	public String getImageHref(){
		return imageHref;
	}
	@BindingAdapter({"bind:imageUrl"})
	public static void loadImage(ImageView view, String imageUrl) {
		Picasso.with(view.getContext())
				.load(imageUrl)
				.placeholder(R.drawable.ic_article)
				.into(view);
	}
	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
 	public String toString(){
		return 
			"RowsItem{" + 
			"imageHref = '" + imageHref + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}