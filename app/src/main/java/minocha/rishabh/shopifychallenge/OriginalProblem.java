package minocha.rishabh.shopifychallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by rishabh on 09/05/18.
 */

public class OriginalProblem extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_problem);
        TextView ordersByProvince = (TextView)findViewById(R.id.tvOrdersByProvince);
        TextView ordersByYear = (TextView)findViewById(R.id.tvOrdersByYear);
        Intent intent = getIntent();


        HashMap<String, Integer> hashProvince = (HashMap<String, Integer>)intent.getSerializableExtra("provinceMap");
        String dataOfProvince="";
        for (String province: hashProvince.keySet()){
            dataOfProvince=dataOfProvince+"\n"+hashProvince.get(province)+" number of orders in "+province;
        }
        ordersByProvince.setText(dataOfProvince);
        HashMap<String, Integer> hashYear = (HashMap<String, Integer>)intent.getSerializableExtra("yearMap");
        String dataOfYear="";
        for (String year: hashYear.keySet()){
            dataOfYear=dataOfYear+"\n"+hashYear.get(year)+" number of orders in "+year;
        }
        ordersByYear.setText(dataOfYear);

    }
}
