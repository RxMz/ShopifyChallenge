package minocha.rishabh.shopifychallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import minocha.rishabh.shopifychallenge.POJO.DataObject;

/**
 * Created by rishabh on 08/05/18.
 */

public class ExtraOneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_one);
        List<DataObject> myList = (List<DataObject>) getIntent().getSerializableExtra("listOfOrders");

    }
}
