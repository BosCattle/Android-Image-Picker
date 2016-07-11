package picker.image.android.com.library.ui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import java.util.List;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.MediaItem;
import picker.image.android.com.library.option.MediaOptions;
import picker.image.android.com.library.option.MediaUtils;

/**
 * Created by Kevin on 2016/7/6.
 */
public class ImagePickerAdapter extends CursorRecyclerViewAdapter<ImagePickerViewHolder> {

  private MediaOptions mMediaOptions;
  private List<MediaItem> mMediaListSelected;
  private int mMediaType;
  public Context mContext;
  public RelativeLayout.LayoutParams mImageViewLayoutParams;
  public Cursor mCursor;

  public ImagePickerAdapter(Context context, Cursor c, int mediaType, MediaOptions mediaOptions) {
    this(context, c, null, mediaType, mediaOptions);
  }

  public ImagePickerAdapter(Context context, Cursor c, List<MediaItem> mediaListSelected,
      int mediaType, MediaOptions mediaOptions) {
    super(context, c);
    if (mediaListSelected != null) mMediaListSelected = mediaListSelected;
    this.mContext = context;
    mMediaType = mediaType;
    mMediaOptions = mediaOptions;
    mCursor = c;
  }

  @Override public ImagePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ImagePickerViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false));
  }

  @Override public int getItemCount() {
    return mCursor.getCount();
  }

  @Override public void onBindViewHolder(ImagePickerViewHolder viewHolder, Cursor cursor) {
    Uri mUri;
    mUri = MediaUtils.getPhotoUri(mCursor);
    Glide.with(mContext).load(mUri).centerCrop().crossFade().into(viewHolder.mImageView);
    viewHolder.mAppCompatCheckBox.setOnClickListener(view -> {
      if (viewHolder.mAppCompatCheckBox.isChecked()) {
        viewHolder.mAppCompatCheckBox.setChecked(false);
        viewHolder.mAppCompatCheckBox.setVisibility(View.GONE);
      } else {
        viewHolder.mAppCompatCheckBox.setChecked(true);
        viewHolder.mAppCompatCheckBox.setVisibility(View.VISIBLE);
      }
    });
    viewHolder.mImageView.setOnTouchListener((view, event) -> {
      if (MotionEvent.ACTION_DOWN == event.getAction()) {
        if (viewHolder.mAppCompatCheckBox.isChecked()) {
          viewHolder.mAppCompatCheckBox.setChecked(false);
          viewHolder.mAppCompatCheckBox.setVisibility(View.GONE);
        } else {
          viewHolder.mAppCompatCheckBox.setChecked(true);
          viewHolder.mAppCompatCheckBox.setVisibility(View.VISIBLE);
        }
      }
      return true;
    });
  }
}
