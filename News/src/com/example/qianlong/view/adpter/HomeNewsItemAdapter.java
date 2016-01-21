package com.example.qianlong.view.adpter;

import java.util.List;

import com.example.qianlong.R;
import com.example.qianlong.bean.News;
import com.example.qianlong.constants.Constants;
import com.example.qianlong.utils.StringUtils;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeNewsItemAdapter extends BaseAdapter {

	private List<News> news;
	private Context context;

	public HomeNewsItemAdapter(Context context, List<News> news) {
		super();
		this.news = news;
		this.context = context;
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		News newitem = news.get(position);
		;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.context);
			convertView = inflater.inflate(
					R.layout.cbn_home_page_recommend_adpter, null);
			viewHolder = new ViewHolder();
			viewHolder.imageview_NewsThumbs = (ImageView) convertView
					.findViewById(R.id.imageview_NewsThumbs);
			viewHolder.textview_NewsNotes = (TextView) convertView
					.findViewById(R.id.textview_NewsNotes);
			viewHolder.textview_NewsTags = (TextView) convertView
					.findViewById(R.id.textview_NewsTags);
			viewHolder.textview_NewsTagsOrDate = (TextView) convertView
					.findViewById(R.id.textview_NewsTagsOrDate);
			viewHolder.textview_NewsTitle = (TextView) convertView
					.findViewById(R.id.textview_NewsTitle);
			viewHolder.textview_NewsNotes = (TextView) convertView
					.findViewById(R.id.textview_NewsNotes);
			viewHolder.textview_NewsTags = (TextView) convertView
					.findViewById(R.id.textview_NewsTags);
			viewHolder.textview_NewsTagsOrDate = (TextView) convertView
					.findViewById(R.id.textview_NewsTagsOrDate);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
			if (!newitem.getNewsThumbs().startsWith("http://")) {
				Picasso.with(context)
						.load(Constants.IMGURL + Constants.IMGURL_ORIGIN
								+ newitem.getNewsThumbs())
						.into(viewHolder.imageview_NewsThumbs);
			} else {
				Picasso.with(context).load(newitem.getNewsThumbs())
						.into(viewHolder.imageview_NewsThumbs);
			}

		} else {
			viewHolder.imageview_NewsThumbs.setVisibility(View.GONE);
		}
		viewHolder.textview_NewsNotes.setText(newitem.getNewsNotes());
		viewHolder.textview_NewsTitle.setText(newitem.getNewsTitle());
		viewHolder.textview_NewsTags.setText(newitem.getTags());
		viewHolder.textview_NewsTagsOrDate.setText(newitem.getCreateDate());
		return convertView;
	}

	static class ViewHolder {
		public ImageView imageview_NewsThumbs;
		public TextView textview_NewsTitle;
		public TextView textview_NewsNotes;
		public TextView textview_NewsTags;
		public TextView textview_NewsTagsOrDate;
	}
}
