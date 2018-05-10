package minocha.rishabh.shopifychallenge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import minocha.rishabh.shopifychallenge.API.ApiCall;
import minocha.rishabh.shopifychallenge.Converters.StampToYear;
import minocha.rishabh.shopifychallenge.POJO.DataObject;
import minocha.rishabh.shopifychallenge.POJO.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static minocha.rishabh.shopifychallenge.Converters.StampToYear.getYear;

public class MainActivity extends AppCompatActivity {


    List<Order> mList;
    Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl("https://shopicruit.myshopify.com/")
            .addConverterFactory(GsonConverterFactory.create());

    HashMap<String,Integer> provinceOrders = new HashMap<String,Integer>();
    HashMap<String,Integer> yearOrders = new HashMap<String,Integer>();
    List<Order> extraOne;
    Retrofit retrofit = builder.build();
    ApiCall client = retrofit.create(ApiCall.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Here, thisActivity is the current activity

        Toast.makeText(getApplicationContext(),"Getting data",Toast.LENGTH_LONG).show();
        Call<DataObject> call=client.getData("1","c32313df0d0ef512ca64d5b336a0d7c6");
        call.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {
                //Toast.makeText(getApplication(),"Success",Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null){
                    storeData(response);
                }
            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {
                Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();
                Log.i("Error",t.getMessage());
            }
        });

        Button checkSummary = (Button)findViewById(R.id.btnOrderSummary);
        checkSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i0=new Intent(MainActivity.this,OriginalProblem.class);
                i0.putExtra("provinceMap",provinceOrders);
                i0.putExtra("yearMap",yearOrders);
                startActivity(i0);
            }
        });
        Button checkProvinceSummary = (Button) findViewById(R.id.btnProvince);
        checkProvinceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,OriginalProblemActivityProvince.class);
                i1.putExtra("provincemap",provinceOrders);
                startActivity(i1);
            }
        });
        Button checkYearSummary = (Button)findViewById(R.id.btnYear);
        checkYearSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(MainActivity.this, OriginalProblemActivityYear.class);
                i2.putExtra("yearmap",yearOrders);
                startActivity(i2);
            }
        });
        Button extraOneActivity = (Button)findViewById(R.id.btnExtra1);
        extraOneActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(MainActivity.this,ExtraOneActivity.class);
                i3.putExtra("listOfOrders", (Serializable) mList);
                startActivity(i3);
            }
        });
        Button extraTwo = (Button)findViewById(R.id.btnExtra2);
        extraTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Couldn't do this on time :(",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void storeData(Response<DataObject> response){
        int size = response.body().getOrders().size();
        mList = response.body().getOrders();
        //To add into the hashmap
        for (int i = 0; i < size; i++) {
            if(mList.get(i).getBillingAddress()!= null) { // As the billing and shipping is the same
                String province = mList.get(i).getBillingAddress().getProvince();
                if (provinceOrders.containsKey(province))
                    provinceOrders.put(province, provinceOrders.get(province) + 1);
                else
                    provinceOrders.put(province, 1);
            }
        }
        // To add the years hashmap
        for (int i = 0; i < size; i++) {
            String year = getYear(mList.get(i).getCreatedAt());
            if (yearOrders.containsKey(year))
                yearOrders.put(year, yearOrders.get(year) + 1);
            else
                yearOrders.put(year, 1);
        }
       // for(int i = 0;i < size; i++)
         //   extraOne.add(mList.get(i));
    }
}
