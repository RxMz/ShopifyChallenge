package minocha.rishabh.shopifychallenge.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import minocha.rishabh.shopifychallenge.POJO.Order;
import minocha.rishabh.shopifychallenge.R;

/**
 * Created by rishabh on 09/05/18.
 */

public class ExtraOneAdapter extends RecyclerView.Adapter<ExtraOneAdapter.ExtraOneHolder> {
    List<Order> list;

    public ExtraOneAdapter(List<Order> list){
        this.list=list;
    }
    @Override
    public ExtraOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_extra_one,parent,false);
        return new ExtraOneHolder(v);

    }

    @Override
    public void onBindViewHolder(ExtraOneHolder holder, int position) {
        holder.totalPrice.setText("CAD " + list.get(position).getTotalPrice());
        holder.email.setText(list.get(position).getEmail());
        if(list.get(position).getBillingAddress() != null){
            holder.location.setText(list.get(position).getBillingAddress().getProvince()+", "+list.get(position).getBillingAddress().getCountry());


            holder.name.setText(list.get(position).getCustomer().getFirstName()+" "+list.get(position).getCustomer().getLastName());
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ExtraOneHolder extends RecyclerView.ViewHolder {
        public TextView email,totalPrice,name,location;
        public ExtraOneHolder(View itemView) {
            super(itemView);
            email=(TextView) itemView.findViewById(R.id.tvEmail);
            totalPrice=(TextView) itemView.findViewById(R.id.tvTotalPrice);
            name=(TextView) itemView.findViewById(R.id.tvName);
            location=(TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
