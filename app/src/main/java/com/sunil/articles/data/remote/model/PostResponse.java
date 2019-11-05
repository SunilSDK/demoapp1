package com.sunil.articles.data.remote.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.sunil.articles.data.local.entity.RowsItem;


public class PostResponse{

	@SerializedName("title")
	private String title;

	@SerializedName("rows")
	private List<RowsItem> rows;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setRows(List<RowsItem> rows){
		this.rows = rows;
	}

	public List<RowsItem> getRows(){
		return rows;
	}

	@Override
 	public String toString(){
		return 
			"PostResponse{" + 
			"title = '" + title + '\'' + 
			",rows = '" + rows + '\'' + 
			"}";
		}
}