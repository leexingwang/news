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
 * 10=���ţ���ͼ���� �����¼��
 * 
 * <p>
 * 11=ͼ����2�ţ�Ĭ�ϵ�1��2�ţ���ͼȡ�м�
 * 
 * <p>
 * 12=��Ƶ��ͬ10
 * 
 * <p>
 * 13=������14=ר�⣻15=ֱ����17=������18=����᣻19=���¼���������ͼ �����¼��
 * 
 * <p>
 * 16=ͨ����1��ͼ
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
		 * NewsType - ��������
		 * <p>
		 * ���� ˵��
		 * <p>
		 * int �������ͷ�Ϊ ʵ������ �� �������� �����࣬��һ���ֶ� IsEntity ���������ж��Ƿ���ʵ������
		 * ʵ�����ͣ�ʵ������+100=��Ӧ����������
		 * <p>
		 * 10=���ţ�11=ͼ����12=��Ƶ��13=������14=ר�⣻15=ֱ����16=ͨ����17=������18=����᣻19=���¼�
		 * <p>
		 * �������ͣ���������-100=��Ӧ��ʵ������
		 * <p>
		 * 110=����������111=ͼ��������112=��Ƶ������113=����������114=ר��������115=ֱ��������116=ͨ��������117=
		 * ���������� 118=�����������119=���¼�����
		 * <p>
		 * EntityPath Ϊʵ�����ŵ� ��/�� ��ʽ������ƴ������url�ã������������Ϊʵ�����ŵ���/��
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
	 * ���Ż�����Ƶviewholder
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
	 * ͼ��viewholder
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
	 * ͨ�� viewholder
	 * 
	 * @author lixingwang
	 * 
	 */
	static class TongLanViewHolder {
		public ImageView imageview_NewsThumbs_Tonglan;
	}

	/**
	 * ר�� viewholder
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
