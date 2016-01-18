package com.example.qianlong.view.adpter;

import java.util.List;

import com.example.qianlong.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	private List<String> news;
	private Context context;

	public NewsAdapter(Context context, List<String> news) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.context);
			convertView = inflater.inflate(R.layout.cbn_item_news_adpter, null);
			viewHolder = new ViewHolder();
			viewHolder.textView_news = (TextView) convertView
					.findViewById(R.id.cbn_item_news_textView1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView_news.setText(news.get(position));
		return convertView;
	}

	static class ViewHolder {
		public TextView textView_news;
	}
}
