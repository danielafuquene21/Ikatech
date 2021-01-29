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

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ikatech.R;
import com.ikatech.businessObject.Const;
import com.ikatech.dataObject.Vehicle;
import com.ikatech.view.AddVehicleActivity;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    //private View.OnClickListener listener;
    private RecyclerViewOnItemClickListener recListener;
    private ArrayList<Vehicle> data;
    private Context contexto;
    private  String tipo;
    public VehicleAdapter(ArrayList<Vehicle> data, Context applicationContext, String tipoAdapter) {
        this.data = data;
        this.contexto = applicationContext;
        this.tipo = tipoAdapter;
    }

    @Override
    public VehicleAdapter.VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int Layout = R.layout.vehicle_list_item;
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        return new VehicleAdapter.VehicleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false));
        //return new NewVehicleAviableAdapter.VehicleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(VehicleAdapter.VehicleViewHolder holder, final int position) {
        final Vehicle vehi = data.get(position);
        holder.tvTradeMark.setText(vehi.getMarca());

        if(vehi.getFavorito() == true){
            holder.favorite.setVisibility(View.VISIBLE);
        }
        Glide.with(contexto)
                .load(vehi.getImagen().toLowerCase())
                .into(holder.image);

        if(tipo.equals("")){
            //holder.btn_view_post.setVisibility(View.GONE);
            holder.tvStatus.setText("comprado");
        }else{
            holder.tvStatus.setText(vehi.getEstado().toLowerCase());
        }
        holder.btn_view_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(contexto, AddVehicleActivity.class);
                if(tipo.equals("")){
                    intent.putExtra(Const.VEHI, vehi);
                    intent.putExtra(Const.BOUGHT_VEHI, true);
                }else {
                    intent.putExtra(Const.VEHI, vehi);
                }
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