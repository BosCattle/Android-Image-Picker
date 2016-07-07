package picker.image.android.com.library.ui;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import picker.image.android.com.library.R;

/**
 * Created by Kevin on 2016/7/6.
 */
public class ImagePickerViewHolder extends RecyclerView.ViewHolder {

  public ImageView mImageView;
  public AppCompatCheckBox mAppCompatCheckBox;

  public ImagePickerViewHolder(View itemView) {
    super(itemView);
    mImageView = (ImageView) itemView.findViewById(R.id.ui_view_item_iv);
    mAppCompatCheckBox = (AppCompatCheckBox) itemView.findViewById(R.id.ui_view_item_acb);
  }
}
