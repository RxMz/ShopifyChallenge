package minocha.rishabh.shopifychallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by rkminoch on 08/05/18.
 */


public class OriginalProblemActivityYear extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_by_year);
        Intent intent = getIntent();
        TextView sample = (TextView)findViewById(R.id.tvSample);

        HashMap<String, Integer> hashMap = (HashMap<String, Integer>)intent.getSerializableExtra("yearmap");
        String data="";
        for (String key: hashMap.keySet()){
            data=data+"\n"+hashMap.get(key)+" number of orders in "+key;
        }
        sample.setText(data);
    }
}
