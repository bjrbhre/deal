package com.mines.deal;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mines.deal.Shopping.Cart;

public class Home extends ListActivity {
	Shopping shop;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		CartAdapter mAdapter = new CartAdapter();
		setListAdapter(mAdapter);

		Shopping.Cart mCaddie = new Shopping.Cart();
		mAdapter.add(mCaddie);

//		shop = new Shopping(this, "shopping", null, 1);
//
//		SQLiteDatabase db = shop.getWritableDatabase();
//		db.delete("item", null, null);
//		ContentValues values = new ContentValues();
//		values.put("name", "EAU CRISTALINE");
//		db.insert("item", null, values);
//		db.close();
//		Log.e("Home", "onCreate");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return true;

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case (R.id.menu_add):
			intent = new Intent(getApplicationContext(), CaddieActivity.class);
			intent.putExtra("action", 1);
			startActivity(intent);
			break;
		case (R.id.menu_history):
			intent = new Intent(getApplicationContext(), HistoryActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}

	private class CartAdapter extends BaseAdapter {
		private ArrayList<Cart> carts;
		LayoutInflater mInflater;

		public CartAdapter() {
			super();
			mInflater = getLayoutInflater();
			carts = new ArrayList<Shopping.Cart>();
		}

		@Override
		public int getCount() {
			return carts.size();
		}

		public void add(Cart mCaddie) {
			carts.add(mCaddie);
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			return carts.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			convertView = mInflater.inflate(R.layout.shopping_cart, null);
			Shopping.Cart mCart = (Shopping.Cart) getItem(position);

			TextView date = (TextView) convertView.findViewById(R.id.date);
			date.setText(mCart.date.toString());
			TextView price = (TextView) convertView.findViewById(R.id.price);
			price.setText(String.valueOf(mCart.getTotal()) + "�");
			TextView quantity = (TextView) convertView
					.findViewById(R.id.quantity);
			quantity.setText(String.valueOf(mCart.getQuantity()) + " achats");
			
			return convertView;
		}

	}

}