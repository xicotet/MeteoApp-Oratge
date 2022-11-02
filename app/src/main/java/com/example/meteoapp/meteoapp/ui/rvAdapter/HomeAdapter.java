package com.example.meteoapp.meteoapp.ui.rvAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meteoapp.R;
import com.example.meteoapp.meteoapp.data.model.Forecast;

import org.w3c.dom.Text;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Forecast> forecasts;

    public HomeAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @NonNull
    @Override //Llamado cuando el Recycler requiere de una nueva vista para un item
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View homeView = inflater.inflate(R.layout.home_row_item, parent, false);

        return new ViewHolder(homeView);
    }

    @Override //Llamado cuando el Recycler quiere vincular unos datos en un item a traves del ViewHolder
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);

        holder.txtTownName.setText(forecast.getNombre());
        holder.txtProvinceName.setText(forecast.getProvincia());
        //holder.txtCurrentTemp.setText();
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTownName;
        public TextView txtProvinceName;
        public TextView txtCurrentTemp;
        public TextView txtCurrentHumidity;
        public TextView txtCurrentRainProb;
        public ImageView imgCurrentSkyState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTownName = itemView.findViewById(R.id.txtTownName);
            txtProvinceName = itemView.findViewById(R.id.txtProvinceName);
            txtCurrentTemp = itemView.findViewById(R.id.txtCurrentTemp);
            txtCurrentHumidity = itemView.findViewById(R.id.txtCurrentHumidity);
            txtCurrentRainProb = itemView.findViewById(R.id.txtCurrentRainProb);
            imgCurrentSkyState = itemView.findViewById(R.id.imgCurrentSkyState);
        }
    }
}
