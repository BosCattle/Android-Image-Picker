package picker.image.android.com.library.ui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    viewHolder.mImageView.setImageURI(mUri);
  }
}