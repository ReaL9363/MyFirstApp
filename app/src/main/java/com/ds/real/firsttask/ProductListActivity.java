package com.ds.real.firsttask;

/**
 * Created by ReaL on 6/20/2016.
 */
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zahan on 6/18/2016.
 */
public class ProductListActivity extends AppCompatActivity {

    /**
     * UI elements
     */

    ListView mproductList;
    Button mnext;
    /*
    * Database Helper
    */

    DbHelperUtil dbHelper;
    /*
    * data from database
    */
    ArrayList<ProductModel> databaseData=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        //fetch database data
        dbHelper=new DbHelperUtil(this);
        databaseData=dbHelper.getAllData();
        //set adapter
        mnext=(Button) findViewById(R.id.button_next);
        mproductList=(ListView) findViewById(R.id.product_list);
        mproductList.setAdapter(new ProductListAdapter(this, databaseData));
        mproductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String name=databaseData.get(position).getName();
                //listview clicked
                final Dialog amountDialog=new Dialog(ProductListActivity.this);
                amountDialog.setTitle("Order Selected Product");
                amountDialog.setContentView(R.layout.layout_amount_dialog);
                final EditText mamount=(EditText) amountDialog.findViewById(R.id.product_amount);
                final Button msave=(Button) amountDialog.findViewById(R.id.button_save_amount);
                final Button cancel=(Button) amountDialog.findViewById(R.id.button_cancel);
                msave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated=dbHelper.saveProductAmount(mamount.getText().toString(),name);
                        if(isUpdated){
                            Toast.makeText(ProductListActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ProductListActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
                        }
                        amountDialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amountDialog.dismiss();
                    }
                });
                amountDialog.show();
            }
        });
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button clicked
                Intent i=new Intent(ProductListActivity.this,OrderListActivity.class);
                startActivity(i);
            }
        });
    }
}
