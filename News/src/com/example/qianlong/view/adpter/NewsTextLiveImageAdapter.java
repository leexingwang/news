/**
 * 
 */
package com.example.qianlong.view.adpter;

import java.util.ArrayList;
import java.util.List;

import com.example.qianlong.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * @author lixingwang
 * 
 */
public class NewsTextLiveImageAdapter extends BaseAdapter {

	private List<String> image_urls = new ArrayList<String>();
	private Context context;

	public NewsTextLiveImageAdapter(Context context, List<String> image_urls) {
		this.context = context;
		this.image_urls = image_urls;
	}

	@Override
	public int getCount() {
		return image_urls.size();
	}

	@Override
	public Object getItem(int position) {
		return image_urls.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.cbn_live7_24_adapter, null);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageView_7_24_live_image);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Picasso.with(context).load(image_urls.get(position))
				.error(R.drawable.cbn_default).into(viewHolder.imageView);
		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
	}
}
