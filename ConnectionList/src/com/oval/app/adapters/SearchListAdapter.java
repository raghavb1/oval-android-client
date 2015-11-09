package com.oval.app.adapters;

import java.util.List;

import org.mitre.svmp.activities.ConnectionList;
import org.mitre.svmp.client.R;
import org.mitre.svmp.common.Constants;

import com.oval.app.activities.OvalLoginActivity;
import com.oval.app.activities.OvalSearchActivity;
import com.oval.app.vo.SearchResultItemVO;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchListAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private List<SearchResultItemVO> items;

	public SearchListAdapter(Activity activity, List<SearchResultItemVO> items) {
		// TODO Auto-generated constructor stub

		this.activity = activity;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (inflater == null)
			inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_item_search, null);

		Holder holder = new Holder();
		holder.appNameTextView = (TextView) convertView.findViewById(R.id.appNameTextView);
		holder.appCategoryTextView = (TextView) convertView.findViewById(R.id.appCategoryTextView);
		holder.appIconImageView=(ImageView) convertView.findViewById(R.id.appIconImageView);
		holder.openOrInstallBtn=(Button) convertView.findViewById(R.id.openOrInstallBtn);
		final int finalPosition= position;
		
		holder.openOrInstallBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(items!=null)
				{
					SearchResultItemVO searchItem = items.get(finalPosition);
					if(searchItem!=null)
					{
						Intent i = new Intent(activity, ConnectionList.class);
						i.setAction(Constants.ACTION_LAUNCH_APP);
					//	Intent i = new Intent(OvalLoginActivity.this, OvalSearchActivity.class);
						i.putExtra("connectionID", 0);
						i.putExtra("pkgName", activity.getString(R.string.oval_app_services_pkgname));
					//	i.putExtra("pkgName", "com.flipkart.android");
						i.putExtra("apkPath",activity.getString(R.string.services_prefix_url)+ searchItem.getApkPath());
						activity.startActivity(i);
						
					}
				}
				
			}
		});

		if (items != null) {
			SearchResultItemVO searchItem = items.get(position);
			
			if(searchItem!=null)
			{
				holder.appCategoryTextView.setText(searchItem.getTypeName());
				holder.appNameTextView.setText(searchItem.getBasename());
				Picasso
				.with(activity)
				.load(activity.getString(R.string.services_prefix_url)+searchItem.getIconPath()+searchItem.getLargeIcon())
				 .placeholder(R.drawable.ic_launcher)
				.into(holder.appIconImageView);
			}
		}
		return convertView;
	}

	class Holder {
		public ImageView appIconImageView;
		public TextView appNameTextView;
		public TextView appCategoryTextView;
		public Button openOrInstallBtn;
	}

}
