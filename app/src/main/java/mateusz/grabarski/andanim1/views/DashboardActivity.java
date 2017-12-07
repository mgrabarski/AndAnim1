package mateusz.grabarski.andanim1.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.adapters.DashboardAdapter;
import mateusz.grabarski.andanim1.models.DashboardItem;
import mateusz.grabarski.andanim1.utils.DataGenerator;

public class DashboardActivity extends AppCompatActivity implements DashboardAdapter.OnItemClickListener {

    @BindView(R.id.activity_dashboard_rv)
    RecyclerView recyclerView;

    @BindView(R.id.activity_dashboard_toolbar)
    Toolbar toolbar;

    private DashboardAdapter mAdapter;
    boolean isListView = true;
    private Menu menu;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        mAdapter = new DashboardAdapter(DataGenerator.getDashboardItems(), this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_dashboard_toggle:
                toggleList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleList() {
        if (isListView)
            showGridView();
        else
            showListView();
    }

    private void showListView() {
        mStaggeredGridLayoutManager.setSpanCount(1);
        MenuItem menuItem = menu.findItem(R.id.menu_dashboard_toggle);
        menuItem.setIcon(R.drawable.ic_action_grid);
        menuItem.setTitle(R.string.show_as_grid);
        isListView = true;
    }

    private void showGridView() {
        mStaggeredGridLayoutManager.setSpanCount(2);
        MenuItem menuItem = menu.findItem(R.id.menu_dashboard_toggle);
        menuItem.setIcon(R.drawable.ic_action_list);
        menuItem.setTitle(R.string.show_as_list);
        isListView = false;
    }

    @Override
    public void onItemClick(DashboardItem item) {

    }
}
