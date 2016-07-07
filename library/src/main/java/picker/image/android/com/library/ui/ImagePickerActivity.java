package picker.image.android.com.library.ui;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.tbruyelle.rxpermissions.RxPermissions;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.ImageOption;
import picker.image.android.com.library.option.MediaUtils;

/**
 * picker image by kevin
 */
public class ImagePickerActivity extends AppCompatActivity
    implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

  public static final int CONSTANT_REQUEST_CODE = 100;
  private static final String LOADER_EXTRA_URI = "loader_extra_uri";
  private static final String LOADER_EXTRA_PROJECT = "loader_extra_project";

  private Toolbar mToolbar;
  private RecyclerView mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_picker);
    initialization();
    requestPhoto();
  }

  private void initialization() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mRecyclerView = (RecyclerView) findViewById(R.id.ui_image_recycler);
    mToolbar.setTitle(getResources().getString(R.string.label_toolbar));
    mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
  }

  /**
   * open image picker activity
   */
  public static void openActivity(AppCompatActivity activity, ImageOption option, int code) {
    Intent intent = new Intent(activity, ImagePickerActivity.class);
    activity.startActivityForResult(intent, code);
  }

  private void requestPhoto() {
    RxPermissions.getInstance(this)
        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .subscribe(granted -> {
          if (granted) {
            requestPhotos(false);
          } else {
            Toast.makeText(this, "选择相册需要你打开读写SD卡权限..", Toast.LENGTH_LONG).show();
          }
        });
  }

  private void requestPhotos(boolean isRestart) {
    requestMedia(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaUtils.PROJECT_PHOTO, isRestart);
  }

  private void requestMedia(Uri uri, String[] projects, boolean isRestart) {
    Bundle bundle = new Bundle();
    bundle.putStringArray(LOADER_EXTRA_PROJECT, projects);
    bundle.putString(LOADER_EXTRA_URI, uri.toString());

    if (isRestart) {
      getLoaderManager().restartLoader(0, bundle, this);
    } else {
      getLoaderManager().initLoader(0, bundle, this);
    }
  }

  @Override public android.content.Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
    Uri uri = Uri.parse(bundle.getString(LOADER_EXTRA_URI));
    String[] projects = bundle.getStringArray(LOADER_EXTRA_PROJECT);
    String order = MediaStore.MediaColumns.DATE_ADDED + " DESC";
    return new android.content.CursorLoader(this, uri, projects, null, null, order);
  }

  @Override public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
    ImagePickerAdapter imagePickerAdapter = new ImagePickerAdapter(ImagePickerActivity.this,cursor,null,0,null);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
    mRecyclerView.setAdapter(imagePickerAdapter);
  }

  @Override public void onLoaderReset(android.content.Loader<Cursor> loader) {

  }
}
