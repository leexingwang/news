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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <p>
 * 10=新闻：左图右字 上字下简介
 * 
 * <p>
 * 11=图集：2张，默认第1、2张，竖图取中间
 * 
 * <p>
 * 12=视频：同10
 * 
 * <p>
 * 13=外链；14=专题；15=直播；17=报名；18=见面会；19=大事件：上字下图 上字下简介
 * 
 * <p>
 * 16=通栏：1张图
 * 
 * @author lixingwang
 * 
 */
public class NewsItemAdapter extends BaseAdapter {

	private List<News> news;
	private Context context;
	private LayoutInflater inflater;

	public NewsItemAdapter(Context context, List<News> news) {
		super();
		this.news = news;
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

	public int getItemViewType(int position) {
		int type = 0;
		if (news.get(position).getNewsType() > 100) {
			type = news.get(position).getNewsType() - 100;
		} else {
			type = news.get(position).getNewsType();
		}
		return type;
	}

	@Override
	public int getViewTypeCount() {
		return 20;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		News newitem = news.get(position);
		int type = getItemViewType(position);
		if (type >= 110) {
			type -= 100;
		}
		NewsViewHolder viewHolder = null;
		TuJiViewHolder tujiviewHolder = null;
		ZhuanTiViewHolder zhuanTiviewHolder = null;
		TongLanViewHolder tonglanviewHolder = null;
		// if (newitem.getChannelID() == 3479) {
		// type = Constants.NewsType_XINWEN;
		// }
		/**
		 * <p>
		 * NewsType - 新闻类型
		 * <p>
		 * 类型 说明
		 * <p>
		 * int 新闻类型分为 实体新闻 和 跳连新闻 两大类，有一个字段 IsEntity 用来快速判断是否是实体新闻
		 * 实体类型：实体类型+100=对应的跳链类型
		 * <p>
		 * 10=新闻；11=图集；12=视频；13=外链；14=专题；15=直播；16=通栏；17=报名；18=见面会；19=大事件
		 * <p>
		 * 跳链类型：跳链类型-100=对应的实体类型
		 * <p>
		 * 110=新闻跳链；111=图集跳链；112=视频跳链；113=外链跳链；114=专题跳链；115=直播跳链；116=通栏跳链；117=
		 * 报名跳链； 118=见面会跳链；119=大事件跳链
		 * <p>
		 * EntityPath 为实体新闻的 年/月 格式，用来拼接完整url用，如果是跳链则为实体新闻的年/月
		 * 
		 */
		if (convertView == null) {
			switch (type) {
			case Constants.NewsType_SHIPING:
			case Constants.NewsType_XINWEN:
				convertView = inflater.inflate(
						R.layout.cbn_item_news_adpter_news, null);
				viewHolder = new NewsViewHolder();
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
				viewHolder.imageview_NewsVideoPlay = (ImageView) convertView
						.findViewById(R.id.imageview_NewsVideoPlay);
				viewHolder.framelayout_News = (FrameLayout) convertView
						.findViewById(R.id.framelayout_News);
				convertView.setTag(viewHolder);
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(viewHolder.imageview_NewsThumbs);
					} else {
						Picasso.with(context).load(newitem.getNewsThumbs())
								.into(viewHolder.imageview_NewsThumbs);
					}
				} else {
					viewHolder.framelayout_News.setVisibility(View.GONE);
				}
				viewHolder.textview_NewsNotes.setText(newitem.getNewsNotes());
				viewHolder.textview_NewsTitle.setText(newitem.getNewsTitle());
				viewHolder.textview_NewsTags.setText(newitem.getTags());
				viewHolder.textview_NewsTagsOrDate.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_TUJI:
				convertView = inflater.inflate(
						R.layout.cbn_item_news_adpter_tuji, null);
				tujiviewHolder = new TuJiViewHolder();
				tujiviewHolder.textview_NewsTitle = (TextView) convertView
						.findViewById(R.id.textview_NewsTitle);
				tujiviewHolder.textview_NewsChannelName = (TextView) convertView
						.findViewById(R.id.textview_NewsChannelName);
				tujiviewHolder.textview_NewsUpdateTime = (TextView) convertView
						.findViewById(R.id.textview_NewsUpdateTime);
				tujiviewHolder.imageview_NewsThumbs_Tuji1 = (ImageView) convertView
						.findViewById(R.id.imageview_NewsThumbs_TuJi1);
				tujiviewHolder.imageview_NewsThumbs_Tuji2 = (ImageView) convertView
						.findViewById(R.id.imageview_NewsThumbs_TuJi2);
				convertView.setTag(tujiviewHolder);
				// } else {
				// TujiviewHolder = (TuJiViewHolder) convertView.getTag();
				// }
				if (!StringUtils.isEmpty(newitem.getNewsAddons())) {
					String[] thumbs = newitem.getNewsAddons().split(";");
					if (thumbs.length > 0) {
						if (!thumbs[0].startsWith("http://")) {
							Picasso.with(context)
									.load(Constants.IMGURL
											+ Constants.IMGURL_ORIGIN
											+ thumbs[0])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji1);
						} else {
							Picasso.with(context)
									.load(thumbs[0])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji1);
						}
						if (!thumbs[1].startsWith("http://")) {
							Picasso.with(context)
									.load(Constants.IMGURL
											+ Constants.IMGURL_ORIGIN
											+ thumbs[1])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji2);
						} else {
							Picasso.with(context)
									.load(thumbs[1])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji2);
						}
					}

				}
				tujiviewHolder.textview_NewsTitle.setText(newitem
						.getNewsTitle());
				tujiviewHolder.textview_NewsChannelName.setText(newitem
						.getTags());
				tujiviewHolder.textview_NewsUpdateTime.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_WAILIAN:
			case Constants.NewsType_ZHIBO:
			case Constants.NewsType_BAOMING:
			case Constants.NewsType_JIANMIANHUI:
			case Constants.NewsType_DASHIJIAN:
			case Constants.NewsType_ZHUANTI:
				convertView = inflater.inflate(
						R.layout.cbn_item_news_adpter_zhuanti, null);
				zhuanTiviewHolder = new ZhuanTiViewHolder();
				zhuanTiviewHolder.textview_NewsTitle = (TextView) convertView
						.findViewById(R.id.textview_NewsTitle);
				zhuanTiviewHolder.imageview_NewsThumbs = (ImageView) convertView
						.findViewById(R.id.imageview_NewsThumbs_ZhuanTi);
				zhuanTiviewHolder.textview_NewsNotes = (TextView) convertView
						.findViewById(R.id.textview_NewsNotes);
				zhuanTiviewHolder.textview_NewsChannelName = (TextView) convertView
						.findViewById(R.id.textview_NewsChannelName);
				zhuanTiviewHolder.textview_NewsTagsOrDate = (TextView) convertView
						.findViewById(R.id.textview_NewsUpdateTime);
				convertView.setTag(zhuanTiviewHolder);
				// } else {
				// zhuanTiviewHolder = (ZhuanTiViewHolder) convertView
				// .getTag();
				// }
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(zhuanTiviewHolder.imageview_NewsThumbs);
					} else {
						Picasso.with(context).load(newitem.getNewsThumbs())
								.into(zhuanTiviewHolder.imageview_NewsThumbs);
					}
					zhuanTiviewHolder.textview_NewsNotes
							.setVisibility(View.GONE);

				} else {
					zhuanTiviewHolder.imageview_NewsThumbs
							.setVisibility(View.GONE);
					zhuanTiviewHolder.textview_NewsNotes
							.setVisibility(View.VISIBLE);
				}
				zhuanTiviewHolder.textview_NewsNotes.setText(newitem
						.getNewsNotes());
				zhuanTiviewHolder.textview_NewsTitle.setText(newitem
						.getNewsTitle());
				zhuanTiviewHolder.textview_NewsChannelName.setText(newitem
						.getTags());
				zhuanTiviewHolder.textview_NewsTagsOrDate.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_TONGLAN:
				convertView = inflater.inflate(
						R.layout.cbn_item_news_adpter_tonglan, null);
				tonglanviewHolder = new TongLanViewHolder();
				tonglanviewHolder.imageview_NewsThumbs_Tonglan = (ImageView) convertView
						.findViewById(R.id.imageview_NewsThumbs_Tonglan);
				convertView.setTag(tonglanviewHolder);
				// } else {
				// tonglanviewHolder = (TongLanViewHolder) convertView
				// .getTag();
				// }
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(tonglanviewHolder.imageview_NewsThumbs_Tonglan);
					} else {
						Picasso.with(context)
								.load(newitem.getNewsThumbs())
								.into(tonglanviewHolder.imageview_NewsThumbs_Tonglan);
					}
				} else {
					tonglanviewHolder.imageview_NewsThumbs_Tonglan
							.setVisibility(View.GONE);
				}
				break;
			default:
				break;
			}
		} else {
			switch (type) {
			case Constants.NewsType_SHIPING:
			case Constants.NewsType_XINWEN:
				viewHolder = (NewsViewHolder) convertView.getTag();
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(viewHolder.imageview_NewsThumbs);
					} else {
						Picasso.with(context).load(newitem.getNewsThumbs())
								.into(viewHolder.imageview_NewsThumbs);
					}

				} else {
					viewHolder.framelayout_News.setVisibility(View.GONE);
				}
				viewHolder.textview_NewsNotes.setText(newitem.getNewsNotes());
				viewHolder.textview_NewsTitle.setText(newitem.getNewsTitle());
				viewHolder.textview_NewsTags.setText(newitem.getTags());
				viewHolder.textview_NewsTagsOrDate.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_TUJI:
				tujiviewHolder = (TuJiViewHolder) convertView.getTag();
				if (!StringUtils.isEmpty(newitem.getNewsAddons())) {
					String[] thumbs = newitem.getNewsAddons().split(";");
					if (thumbs.length > 0) {
						if (!thumbs[0].startsWith("http://")) {
							Picasso.with(context)
									.load(Constants.IMGURL
											+ Constants.IMGURL_ORIGIN
											+ thumbs[0])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji1);
						} else {
							Picasso.with(context)
									.load(thumbs[0])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji1);
						}
						if (!thumbs[1].startsWith("http://")) {
							Picasso.with(context)
									.load(Constants.IMGURL
											+ Constants.IMGURL_ORIGIN
											+ thumbs[1])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji2);
						} else {
							Picasso.with(context)
									.load(thumbs[1])
									.into(tujiviewHolder.imageview_NewsThumbs_Tuji2);
						}
					}

				}
				tujiviewHolder.textview_NewsTitle.setText(newitem
						.getNewsTitle());
				tujiviewHolder.textview_NewsChannelName.setText(newitem
						.getTags());
				tujiviewHolder.textview_NewsUpdateTime.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_WAILIAN:
			case Constants.NewsType_ZHIBO:
			case Constants.NewsType_BAOMING:
			case Constants.NewsType_JIANMIANHUI:
			case Constants.NewsType_DASHIJIAN:
			case Constants.NewsType_ZHUANTI:
				zhuanTiviewHolder = (ZhuanTiViewHolder) convertView.getTag();
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(zhuanTiviewHolder.imageview_NewsThumbs);
					} else {
						Picasso.with(context).load(newitem.getNewsThumbs())
								.into(zhuanTiviewHolder.imageview_NewsThumbs);
					}
					zhuanTiviewHolder.textview_NewsNotes
							.setVisibility(View.GONE);

				} else {
					zhuanTiviewHolder.imageview_NewsThumbs
							.setVisibility(View.GONE);
					zhuanTiviewHolder.textview_NewsNotes
							.setVisibility(View.VISIBLE);
				}
				zhuanTiviewHolder.textview_NewsNotes.setText(newitem
						.getNewsNotes());
				zhuanTiviewHolder.textview_NewsTitle.setText(newitem
						.getNewsTitle());
				zhuanTiviewHolder.textview_NewsChannelName.setText(newitem
						.getTags());
				zhuanTiviewHolder.textview_NewsTagsOrDate.setText(newitem
						.getCreateDate());
				break;
			case Constants.NewsType_TONGLAN:
				tonglanviewHolder = (TongLanViewHolder) convertView.getTag();
				if (!StringUtils.isEmpty(newitem.getNewsThumbs())) {
					if (!newitem.getNewsThumbs().startsWith("http://")) {
						Picasso.with(context)
								.load(Constants.IMGURL
										+ Constants.IMGURL_ORIGIN
										+ newitem.getNewsThumbs())
								.into(tonglanviewHolder.imageview_NewsThumbs_Tonglan);
					} else {
						Picasso.with(context)
								.load(newitem.getNewsThumbs())
								.into(tonglanviewHolder.imageview_NewsThumbs_Tonglan);
					}
				} else {
					tonglanviewHolder.imageview_NewsThumbs_Tonglan
							.setVisibility(View.GONE);
				}
				break;
			default:
				break;
			}

		}
		return convertView;
	}

	/**
	 * 新闻或者视频viewholder
	 * 
	 * @author lixingwang
	 * 
	 */
	static class NewsViewHolder {
		public FrameLayout framelayout_News;
		public ImageView imageview_NewsThumbs;
		public ImageView imageview_NewsVideoPlay;
		public TextView textview_NewsTitle;
		public TextView textview_NewsNotes;
		public TextView textview_NewsTags;
		public TextView textview_NewsTagsOrDate;
	}

	/**
	 * 图集viewholder
	 * 
	 * @author lixingwang
	 * 
	 */
	static class TuJiViewHolder {
		public TextView textview_NewsTitle;
		public ImageView imageview_NewsThumbs_Tuji1;
		public ImageView imageview_NewsThumbs_Tuji2;
		public TextView textview_NewsChannelName;
		public TextView textview_NewsUpdateTime;
	}

	/**
	 * 通栏 viewholder
	 * 
	 * @author lixingwang
	 * 
	 */
	static class TongLanViewHolder {
		public ImageView imageview_NewsThumbs_Tonglan;
	}

	/**
	 * 专题 viewholder
	 * 
	 * @author lixingwang
	 * 
	 */
	static class ZhuanTiViewHolder {
		public TextView textview_NewsTitle;
		public ImageView imageview_NewsThumbs;
		public TextView textview_NewsChannelName;
		public TextView textview_NewsTagsOrDate;
		public TextView textview_NewsNotes;
	}
}
