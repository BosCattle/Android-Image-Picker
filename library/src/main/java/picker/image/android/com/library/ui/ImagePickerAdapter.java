package picker.image.android.com.library.ui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.os.TraceCompat;
import android.support.v7.util.AsyncListUtil;
import android.util.MutableInt;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.MediaItem;
import picker.image.android.com.library.option.MediaOptions;
import picker.image.android.com.library.option.MediaUtils;
import picker.image.android.com.library.utils.ScreenUtils;

/**
 * Created by Kevin on 2016/7/6.
 */
public class ImagePickerAdapter extends CursorRecyclerViewAdapter<ImagePickerViewHolder> {

  private MediaOptions mMediaOptions;
  private List<MediaItem> mMediaListSelected;
  public Context mContext;
  public Cursor mCursor;

  public ImagePickerAdapter(Context context, Cursor c, MediaOptions mediaOptions, int mMediaType) {
    this(context, c, null, mediaOptions);
  }

  public ImagePickerAdapter(Context context, Cursor c, List<MediaItem> mediaListSelected,
      MediaOptions mediaOptions) {
    super(context, c);
    if (mediaListSelected != null) {
      mMediaListSelected = mediaListSelected;
    } else {
      mMediaListSelected = new ArrayList<>();
    }
    this.mContext = context;
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
    mUri = MediaUtils.getPhotoUri(cursor);
    Glide.with(mContext).load(mUri).centerCrop().crossFade().into(viewHolder.mImageView);
    //Judge image is pick.
    if (mMediaListSelected != null && mMediaListSelected.size() != 0) {
      for (int i = 0; i < mMediaListSelected.size(); i++) {
        if (mUri.equals(mMediaListSelected.get(i).getUriOrigin())) {
          viewHolder.mAppCompatCheckBox.setVisibility(View.VISIBLE);
          viewHolder.mAppCompatCheckBox.setChecked(true);
        }
      }
    } else {
      viewHolder.mAppCompatCheckBox.setOnClickListener(view -> {
        if (viewHolder.mAppCompatCheckBox.isChecked()) {
          viewHolder.mAppCompatCheckBox.setChecked(false);
          viewHolder.mAppCompatCheckBox.setVisibility(View.GONE);
          removeMediaItem(mUri);
        } else {
          if (mMediaOptions.canSelectMultiPhoto()
              && mMediaOptions.getImageSize()>mMediaListSelected.size()) {
            viewHolder.mAppCompatCheckBox.setChecked(true);
            viewHolder.mAppCompatCheckBox.setVisibility(View.VISIBLE);
            addMediaItem(mUri);
          } else {
            Toast.makeText(mContext, "图片总数不能大于" + mMediaOptions.getImageSize(), Toast.LENGTH_SHORT)
                .show();
          }
        }
      });
      viewHolder.mImageView.setOnTouchListener((view, event) -> {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
          if (viewHolder.mAppCompatCheckBox.isChecked()) {
            viewHolder.mAppCompatCheckBox.setChecked(false);
            viewHolder.mAppCompatCheckBox.setVisibility(View.GONE);
            removeMediaItem(mUri);
          } else {
            if (mMediaOptions.canSelectMultiPhoto()
                && mMediaOptions.getImageSize() > mMediaListSelected.size()) {
              viewHolder.mAppCompatCheckBox.setChecked(true);
              viewHolder.mAppCompatCheckBox.setVisibility(View.VISIBLE);
              addMediaItem(mUri);
            } else {
              Toast.makeText(mContext, "图片总数不能大于" + mMediaOptions.getImageSize(),
                  Toast.LENGTH_SHORT).show();
            }
          }
        }
        return false;
      });
    }
  }

  /**
   *  Remove MediaItem
   * @param uri
   */
  public void removeMediaItem(Uri uri){
    MediaItem mediaItem = new MediaItem(MediaItem.PHOTO,uri);
    if (mMediaListSelected.remove(mediaItem)){
      //Remove MediaItem successful
    }
  }

  /**
   * Add MediaItem
   * @param uri
   */
  public void addMediaItem(Uri uri){
    MediaItem mediaItem = new MediaItem(MediaItem.PHOTO,uri);
    if (mMediaListSelected.add(mediaItem)){
      //Add MediaItem successful
    }
  }
}
