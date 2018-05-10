package minocha.rishabh.shopifychallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import minocha.rishabh.shopifychallenge.Adapters.ExtraOneAdapter;
import minocha.rishabh.shopifychallenge.POJO.DataObject;
import minocha.rishabh.shopifychallenge.POJO.Order;

/**
 * Created by rishabh on 08/05/18.
 */

public class ExtraOneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_one);
        List<Order> myList = (List<Order>) getIntent().getSerializableExtra("listOfOrders");

        final RecyclerView rview = (RecyclerView)findViewById(R.id.recyclerView);
        // List
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rview.setLayoutManager(llm);
        ExtraOneAdapter adapter = new ExtraOneAdapter(myList);
        rview.setAdapter(adapter);
    }
}
