package minocha.rishabh.shopifychallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import minocha.rishabh.shopifychallenge.API.ApiCall;
import minocha.rishabh.shopifychallenge.POJO.DataObject;
import minocha.rishabh.shopifychallenge.POJO.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    List<Order> mList;
    Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl("https://shopicruit.myshopify.com/")
            .addConverterFactory(GsonConverterFactory.create());

    HashMap<String,Integer> provinceOrders = new HashMap<String,Integer>();
    HashMap<String,Integer> yearOrders = new HashMap<String,Integer>();

    Retrofit retrofit = builder.build();
    ApiCall client = retrofit.create(ApiCall.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Getting data",Toast.LENGTH_LONG).show();
        Call<DataObject> call=client.getData("1","c32313df0d0ef512ca64d5b336a0d7c6");
        call.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {
                //Toast.makeText(getApplication(),"Success",Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(getApplicationContext(),"Got the data bro "+response.body().getOrders().size(),Toast.LENGTH_SHORT).show();
                int size = response.body().getOrders().size();

                    mList = response.body().getOrders();
                        //To add into the hashmap
                        for (int i = 0; i < size; i++) {
                            if(mList.get(i).getBillingAddress()!= null) { // As the billing and shipping is the same
                                String province = mList.get(i).getBillingAddress().getProvince();
                                String year = mList.get(i).getCreatedAt();
                                if (provinceOrders.containsKey(province)){
                                    //If hashmap contains province

                                    provinceOrders.put(province, provinceOrders.get(province) + 1);
                                }
                                else {
                                    provinceOrders.put(province, 1);
                                }
                            }
                        }
                }
            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {
                Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();
                Log.i("Error",t.getMessage());
            }
        });

        Button checkProvinceSummary = (Button) findViewById(R.id.btnProvince);
        checkProvinceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,OriginalProblemActivityProvince.class);
                i1.putExtra("map",provinceOrders);
                startActivity(i1);
            }
        });
    }
}
