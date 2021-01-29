package com.ikatech.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ikatech.R;
import com.ikatech.businessObject.Const;
import com.ikatech.dataObject.Vehicle;
import com.ikatech.view.AddVehicleActivity;

import java.util.ArrayList;

public class NewVehicleAviableAdapter extends RecyclerView.Adapter<NewVehicleAviableAdapter.VehicleViewHolder> {

    //private View.OnClickListener listener;
    private RecyclerViewOnItemClickListener recListener;
    private ArrayList<Vehicle> data;
    private Context contexto;
    public NewVehicleAviableAdapter(ArrayList<Vehicle> data, Context applicationContext) {
        this.data = data;
        this.contexto = applicationContext;
    }

    @Override
    public NewVehicleAviableAdapter.VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int Layout = R.layout.vehicle_list_item;
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        return new NewVehicleAviableAdapter.VehicleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false));
        //return new NewVehicleAviableAdapter.VehicleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NewVehicleAviableAdapter.VehicleViewHolder holder, final int position) {
        final Vehicle vehi = data.get(position);
        holder.tvTradeMark.setText(vehi.getMarca());
        holder.tvStatus.setText(vehi.getEstado());
        if(vehi.getFavorito() == true){
            holder.favorite.setVisibility(View.VISIBLE);
        }


        holder.btn_view_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(contexto, "click boton:   " + vehi.getMarca() , Toast.LENGTH_SHORT).show();
                Intent  intent = new Intent(contexto, AddVehicleActivity.class);
                intent.putExtra(Const.VEHI, vehi);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                contexto.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /*public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }*/

    public interface  RecyclerViewOnItemClickListener {
        void onClick(Vehicle v);
    }
    public void setListener (RecyclerViewOnItemClickListener listener){
        this.recListener = listener;
    }

    class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTradeMark, tvStatus;
        ImageView favorite, image;
        LinearLayout lyContent;

        Button btn_view_post;
        public VehicleViewHolder(View itemView) {
            super(itemView);
            tvTradeMark = (TextView) itemView.findViewById(R.id.tvTradeMark);
            tvStatus = (TextView) itemView.findViewById(R.id.tvState);
            image = (ImageView) itemView.findViewById(R.id.imageVehi);
            favorite = (ImageView) itemView.findViewById(R.id.tvFavorite);
            btn_view_post = (Button) itemView.findViewById(R.id.btn_view_post);
            lyContent = (LinearLayout) itemView.findViewById(R.id.lyContent);
        }


    }
}