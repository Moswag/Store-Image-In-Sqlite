package zw.co.cytext.sqlitestoreimage;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;


import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        gridView=(GridView)findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new FoodListAdapter(this,R.layout.food_items,list);
        gridView.setAdapter(adapter);

        //get data from sqlite

        Cursor cursor=MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String price=cursor.getString(2);
            byte[] image=cursor.getBlob(3);

            list.add(new Food(id,name,price,image));
        }
        adapter.notifyDataSetChanged();
    }
}
