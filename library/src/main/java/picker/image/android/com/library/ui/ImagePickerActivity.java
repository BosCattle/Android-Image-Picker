package picker.image.android.com.library.ui;

 import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.ImageOption;

/**
 * picker image by kevin
 */
public class ImagePickerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

  public static final int CONSTANT_REQUEST_CODE = 100;

  private Toolbar mToolbar;
  private RecyclerView mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_picker);
    initialization();
  }

  private void initialization() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mRecyclerView = (RecyclerView) findViewById(R.id.ui_image_recycler);
    mToolbar.setTitle(getResources().getString(R.string.label_toolbar));
    mToolbar.setNavigationIcon(android.R.drawable.ic_menu_crop);
  }

  /**
   * open image picker activity
   */
  public static void openActivity(AppCompatActivity activity, ImageOption option, int code) {
    Intent intent = new Intent(activity, ImagePickerActivity.class);
    activity.startActivityForResult(intent, code);
  }

  @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    return null;
  }

  @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

  }

  @Override public void onLoaderReset(Loader<Cursor> loader) {

  }
}
