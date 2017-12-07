package mateusz.grabarski.andanim1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.models.DashboardItem;

/**
 * Created by MGrabarski on 06.12.2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<DashboardItem> mItems;
    private OnItemClickListener mListener;

    public DashboardAdapter(List<DashboardItem> items, OnItemClickListener listener) {
        this.mItems = items;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(mItems.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_dashboard_cv)
        CardView cardView;

        @BindView(R.id.item_dashboard_iv)
        ImageView imageIv;

        @BindView(R.id.item_dashboard_title_tv)
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(final DashboardItem item, final OnItemClickListener listener) {

            final Context context = imageIv.getContext();

            titleTv.setText(item.getTitle());
            Picasso.with(context).load(item.getPicture()).into(imageIv);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            Bitmap photo = BitmapFactory.decodeResource(context.getResources(), item.getPicture());
            Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    int textBgColor = palette.getMutedColor(ContextCompat.getColor(context, android.R.color.white));
                    titleTv.setBackgroundColor(textBgColor);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DashboardItem item);
    }
}
