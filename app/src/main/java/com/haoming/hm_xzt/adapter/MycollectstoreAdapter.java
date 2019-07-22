package com.haoming.hm_xzt.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoming.hm_xzt.app.R;
import com.haoming.hm_xzt.bean.AssistBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class MycollectstoreAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> getData;

	public MycollectstoreAdapter(List<Map<String, Object>> ls) {

		getData = ls;
	}

	public void updateData(List<Map<String, Object>> ls) {

		getData = ls;
	}

	// private ImageLoadingListener animateFirstListener = new
	// AnimateFirstDisplayListener();

	static class ViewHolder {

		public ImageView collectstoreimage;// 收藏店铺的头像
		public TextView collectstorename;// 收藏的店名
		public TextView coolectstorername;// 掌柜
	}

	@Override
	public int getCount() {

		if (getData == null)
			return 0;
		return getData.size();
		// return CollectstoreActivity.imageUrls.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		context = parent.getContext();
		if (convertView == null) {

			convertView = getItemView(parent);
		}

		setViewData((ViewHolder) convertView.getTag(), getData.get(position),
				position);
		return convertView;
	}

	private View getItemView(ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent
				.getContext());

		View view = inflater
				.inflate(R.layout.mycollectstorelistview_item, null);

		ViewHolder holder = new ViewHolder();
		holder.collectstorename = (TextView) view
				.findViewById(R.id.collectstorename);
		holder.collectstoreimage = (ImageView) view
				.findViewById(R.id.collectstoretouxiang);
		holder.coolectstorername = (TextView) view
				.findViewById(R.id.collectstorer);

		view.setTag(holder);
		return view;

	}

	private void setViewData(final ViewHolder vh,
			final Map<String, Object> item, final int position) {

		if (vh == null || item == null)
			return;

		String head = (String) item.get("");

		if (head.equals("null") || head.equals("")) {

			vh.collectstoreimage.setImageResource(R.drawable.header);

		} else {
			ImageLoader.getInstance().loadImage(head,
					new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String arg0, View arg1) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onLoadingFailed(String arg0, View arg1,
								FailReason arg2) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onLoadingComplete(String arg0, View arg1,
								Bitmap bit) {

							Bitmap bitmap = AssistBean
									.getRoundedCornerBitmap(bit);
							vh.collectstoreimage.setImageBitmap(bitmap);
						}

						@Override
						public void onLoadingCancelled(String arg0, View arg1) {
							// TODO Auto-generated method stub

						}
					});

			 vh.collectstorename.setText((CharSequence) item.get("collectstorename"));
			 vh.coolectstorername.setText((CharSequence) item.get("coolectstorername"));
//
//			vh.collectstorename.setText("二手平板电脑连锁店");
//			vh.coolectstorername.setText("小兰");

		}

	}

	// @Override
	// public View getView(final int arg0, View convertView, ViewGroup parent) {
	// View view = convertView;
	// final ViewHolder holder;
	//
	// LayoutInflater viewInflater = (LayoutInflater) LayoutInflater
	// .from(parent.getContext());
	//
	// if (convertView == null) {
	//
	// view = viewInflater.inflate(
	// R.layout.mycollectstorelistview_item, parent, false);
	// holder = new ViewHolder();
	// holder.collectstorename = (TextView) view
	// .findViewById(R.id.collectstorename);
	// holder.collectstoreimage = (ImageView) view
	// .findViewById(R.id.collectstoretouxiang);
	// holder.coolectstorername = (TextView) view
	// .findViewById(R.id.collectstorer);
	// view.setTag(holder);
	// } else {
	// holder = (ViewHolder) view.getTag();
	// }
	//
	// holder.collectstorename.setText("二手平板电脑连锁店 " + (arg0 + 1));
	// holder.coolectstorername.setText("小兰" + (arg0 + 2));
	// BaseListViewActivity.imageLoader.displayImage(
	// CollectstoreActivity.imageUrls[arg0], holder.collectstoreimage,
	// CollectstoreActivity.options, animateFirstListener);
	//
	// return view;
	// }

}
