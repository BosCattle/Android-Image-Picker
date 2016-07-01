package picker.image.android.com.library.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.ImageOption;

public class ImagePickerActivity extends AppCompatActivity {

  public static final int CONSTANT_REQUEST_CODE = 100;

  private Toolbar mToolbar;
  private RecyclerView mRecyclerView;
  private FloatingActionButton mFloatingActionButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_picker);
    initialization();
  }

  private void initialization() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    mRecyclerView = (RecyclerView) findViewById(R.id.ui_image_recycler);
    mToolbar.setTitle(getResources().getString(R.string.label_toolbar));
    mToolbar.setNavigationIcon(android.R.drawable.ic_menu_crop);
  }

  /**
   * open image picker activity
   * @param activity
   * @param option
   * @param code
   */
  public static void openActivity(AppCompatActivity activity, ImageOption option, int code) {
    Intent intent = new Intent(activity, ImagePickerActivity.class);
    activity.startActivityForResult(intent, code);
  }


}
