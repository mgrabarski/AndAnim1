package mateusz.grabarski.andanim1.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    FloatingActionButton commentAddFab;

    @BindView(R.id.content_details_comments_list)
    ListView list;

    private DashboardItem mItem;
    private InputMethodManager mInputManager;
    private boolean isEditTextVisible = false;
    private List<String> comments;
    private ArrayAdapter<String> commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setUpAdapter();

        initUI();

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (getIntent().getExtras() != null) {
            mItem = (DashboardItem) getIntent().getExtras().getSerializable(DASHBOARD_ITEM);

            Log.d(TAG, "onCreate: " + mItem);

            loadData();
        }
    }

    private void setUpAdapter() {
        comments = new ArrayList<>();
        commentsAdapter = new ArrayAdapter<String>(this, R.layout.item_comment, comments);
        list.setAdapter(commentsAdapter);
    }

    private void initUI() {
        revealView.setVisibility(View.INVISIBLE);
        isEditTextVisible = false;
    }

    private void loadData() {
        Picasso.with(this).load(mItem.getPicture()).into(image);

        titleTv.setText(mItem.getTitle());
    }

    @OnClick(R.id.content_details_comment_fab)
    public void onAddCommentClick() {
        if (!isEditTextVisible) {
            revealEditText();
            commentAddFab.setImageResource(R.drawable.ic_done);

            commentEt.requestFocus();
            mInputManager.showSoftInput(commentEt, InputMethodManager.SHOW_IMPLICIT);
        } else {
            hideEditText();
            commentAddFab.setImageResource(R.drawable.ic_add);
            mInputManager.hideSoftInputFromWindow(commentEt.getWindowToken(), 0);

            addComment();
        }
    }

    private void addComment() {
        comments.add(commentEt.getText().toString().trim());
        commentsAdapter.notifyDataSetChanged();
    }

    private void revealEditText() {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int finalRadius = Math.max(revealView.getWidth(), revealView.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, finalRadius);
        revealView.setVisibility(View.VISIBLE);
        isEditTextVisible = true;
        anim.start();
    }

    private void hideEditText() {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int initialRadius = revealView.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, initialRadius, 0f);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        isEditTextVisible = false;
        anim.start();
    }
}
