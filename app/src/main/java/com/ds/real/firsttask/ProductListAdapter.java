package com.ds.real.firsttask;

/**
 * Created by ReaL on 6/20/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zahan on 6/18/2016.
 */
public class ProductListAdapter extends BaseAdapter {

    ArrayList<ProductModel> databaseData;
    Activity activity;
    private static LayoutInflater inflater=null;

    public ProductListAdapter(ProductListActivity activity, ArrayList<ProductModel> databaseData) {
        this.activity=activity;
        this.databaseData=databaseData;
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return databaseData.size();
    }

    @Override
    public Object getItem(int position) {
        return databaseData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Holder  //holder class containing view components/widgets
    {
        TextView name;
        TextView price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();  //instansiating holder class
        View rowView = inflater.inflate(R.layout.layout_product_list_item, null);
        holder.name=(TextView) rowView.findViewById(R.id.product_name);
        holder.price=(TextView) rowView.findViewById(R.id.product_price);
        holder.name.setText(databaseData.get(position).getName());
        holder.price.setText(databaseData.get(position).getPrice());
        return rowView;
    }
}