package minocha.rishabh.shopifychallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import minocha.rishabh.shopifychallenge.API.ApiCall;
import minocha.rishabh.shopifychallenge.POJO.DataObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ArrayList<DataObject> arrayList;
    Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl("https://shopicruit.myshopify.com/")
            .addConverterFactory(GsonConverterFactory.create());
    HashMap<String,Integer> provinceOrders = new HashMap<String,Integer>();

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
                Toast.makeText(getApplication(),"Success",Toast.LENGTH_LONG).show();
                // To check if data is present and valid
                //Toast.makeText(MainActivity.this, response.body().getOrders().get(0).getCreatedAt(), Toast.LENGTH_SHORT).show();
                int size = response.body().getOrders().size();
                for(int i=0;i<size;i++){
                    //provinceOrders.put(response.body().getOrders().get(i));
                }
            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {
                Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();
                Log.i("Error",t.getMessage());
            }
        });
    }
}
