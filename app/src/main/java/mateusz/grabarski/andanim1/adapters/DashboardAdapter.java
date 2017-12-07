package mateusz.grabarski.andanim1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.models.DashboardItem;

/**
 * Created by MGrabarski on 06.12.2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<DashboardItem> mItems;

    public DashboardAdapter(List<DashboardItem> items) {
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_dashboard_iv)
        ImageView imageIv;

        @BindView(R.id.item_dashboard_title_tv)
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(DashboardItem item) {
            titleTv.setText(item.getTitle());

            Picasso.with(imageIv.getContext()).load(item.getPicture()).fit().centerCrop().into(imageIv);
        }
    }
}
