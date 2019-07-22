package com.haoming.hm_xzt.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.haoming.hm_xzt.adapter.MycollectstoreAdapter;
import com.haoming.hm_xzt.http.JsonHttpResponseHandler;
import com.haoming.hm_xzt.http.RequestParams;
import com.haoming.xzt.BaseListViewActivity;
import com.haoming.xzt.MyListView;
import com.haoming.xzt.MyListView.IXListViewListener;
import com.haoming.xzt.NetworkUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class CollectstoreActivity extends BaseListViewActivity implements
		IXListViewListener {

	public static DisplayImageOptions options;
	public static String[] imageUrls;
	private MycollectstoreAdapter adapter;
	private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	private TextView mDataTip;
	private String getID;
	private int mpagerNumber = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collectstorelayout);
		Exit.activityList.add(this);

		listView = (MyListView) findViewById(R.id.list_bou);

		if (adapter == null) {

			((ListView) listView).setAdapter(new MycollectstoreAdapter(null));

		} else {
			adapter.updateData(listItem);
			adapter.notifyDataSetChanged();
		}

		mDataTip = (TextView) findViewById(R.id.datatip);
		mDataTip.setVisibility(View.GONE);
		geneItems();
	}

	private void geneItems() {

		NetworkUtil.get("/goods/list", new RequestParams("uid", getID, "page",
				mpagerNumber + 1, "type", 2), new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				System.out.println("response:" + response);
				try {
					JSONArray jsArray = response.getJSONArray("list");

					if (jsArray.length() != 0) {
						Map<String, Object> map = new HashMap<String, Object>();

						mpagerNumber++;

						listView.setPullLoadEnable(true, 1);
						for (int i = 0; i < jsArray.length(); i++) {

							map = new HashMap<String, Object>();
							map.put("item_user_id", jsArray.getJSONObject(i)
									.getString("uid"));

							map.put("item_user_gid", jsArray.getJSONObject(i)
									.getString("gid"));

							map.put("item_user_head", jsArray.getJSONObject(i)
									.getString("header"));
							System.out.println(jsArray.getJSONObject(i)
									.getString("header"));
							map.put("item_user_imageview", jsArray
									.getJSONObject(i).getString("photo"));
							System.out.println("");
							map.put("item_user_name_text", jsArray
									.getJSONObject(i).getString("nickname"));
							System.out.println("nickname:"
									+ jsArray.getJSONObject(i).getString(
											"nickname"));
							map.put("item_time_text", mytime(jsArray
									.getJSONObject(i).getString("pubtime")));
							map.put("item_xin_btn_isfav", jsArray
									.getJSONObject(i).getBoolean("isfav"));
							System.out.println(jsArray.getJSONObject(i)
									.getBoolean("isfav"));
							map.put("item_price_btn", jsArray.getJSONObject(i)
									.getString("price"));
							System.out.println("price:"
									+ jsArray.getJSONObject(i).getString(
											"price"));
							if (!jsArray.getJSONObject(i).getString("info")
									.equals("")) {
								map.put("item_description_text",
										"商品描述："
												+ jsArray.getJSONObject(i)
														.getString("info"));
							}

							map.put("item_shop_name_text",

									"店名："
											+ jsArray.getJSONObject(i)
													.getString("shopname"));
							map.put("item_shop_place_text",

									"地址："
											+ jsArray.getJSONObject(i)
													.getString("address"));
							map.put("item_fav_num", jsArray.getJSONObject(i)
									.getInt("favnum"));
							map.put("ischecked", "0");

							listItem.add(map);

						}
						if (adapter == null) {
							adapter = new MycollectstoreAdapter(listItem);
							listView.setAdapter(adapter);
						} else {
							adapter.updateData(listItem);
							adapter.notifyDataSetChanged();
						}
						onLoad();
					}

					if (listItem.size() < 1) {
						mDataTip.setVisibility(View.VISIBLE);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.onSuccess(statusCode, headers, response);
			}

		});

	}
	
	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String displayTime = sdf.format(new java.util.Date());
		listView.setRefreshTime(displayTime);
	}
	
	private String mytime(String timeStr) {
		StringBuffer sb = new StringBuffer();
		long t = Long.parseLong(timeStr);
		long time = System.currentTimeMillis() - (t * 1000);
		long mill = (long) Math.ceil(time / 1000);// ��ǰ

		long minute = (long) Math.ceil(time / 60 / 1000.0f);// ����ǰ

		long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// Сʱ

		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// ��ǰ

		if (day - 1 > 0) {
			sb.append(day + "天");
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				sb.append("1天");
			} else {
				sb.append(hour + "小时");
			}
		} else if (minute - 1 > 0) {
			if (minute == 60) {
				sb.append("1小时");
			} else {
				sb.append(minute + "分钟");
			}
		} else if (mill - 1 > 0) {
			if (mill == 60) {
				sb.append("分钟");
			} else {
				sb.append(mill + "秒");
			}
		} else {
			sb.append("刚刚");
		}
		if (!sb.toString().equals("刚刚")) {
			sb.append("前");
		}
		return sb.toString();

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		mDataTip.setVisibility(View.GONE);
		listView.setPullLoadEnable(true, 0);
		 geneItems();

	}

}
