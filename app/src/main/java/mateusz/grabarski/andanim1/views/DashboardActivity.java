package mateusz.grabarski.andanim1.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.adapters.DashboardAdapter;
import mateusz.grabarski.andanim1.utils.DataGenerator;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.activity_dashboard_rv)
    RecyclerView recyclerView;

    private DashboardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        StaggeredGridLayoutManager mStaggeredGridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        mAdapter = new DashboardAdapter(DataGenerator.getDashboardItems());
        recyclerView.setAdapter(mAdapter);
    }
}
