package mateusz.grabarski.andanim1.views;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.models.DashboardItem;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    public static final String DASHBOARD_ITEM = "ITEM";

    @BindView(R.id.activity_details_toolbar)
    Toolbar toolbar;

    @BindView(R.id.content_details_image)
    ImageView image;

    @BindView(R.id.content_details_comments)
    EditText commentEt;

    @BindView(R.id.content_reveal_view)
    LinearLayout revealView;

    @BindView(R.id.content_details_title)
    TextView titleTv;

    @BindView(R.id.content_details_name_holder)
    LinearLayout contentDetailsCourseNameHolder;

    @BindView(R.id.content_details_comment_fab)
    FloatingActionButton contentDetailsCommentFab;

    @BindView(R.id.content_details_comments_list)
    ListView contentDetailsCommentsList;

    private DashboardItem mItem;
    private InputMethodManager mInputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (getIntent().getExtras() != null) {
            mItem = (DashboardItem) getIntent().getExtras().getSerializable(DASHBOARD_ITEM);

            Log.d(TAG, "onCreate: " + mItem);

            loadData();
        }
    }

    private void loadData() {
        Picasso.with(this).load(mItem.getPicture()).into(image);

        titleTv.setText(mItem.getTitle());
    }
}
