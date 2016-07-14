package picker.image.android.com.library.option;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * kevin
 */
public class MediaOptions implements Parcelable {
  private boolean canSelectMultiPhoto;
  private boolean isCropped;
  private boolean canSelectPhoto;
  private File photoCaptureFile;
  private int aspectX;
  private int aspectY;
  private boolean fixAspectRatio;
  private File croppedFile;
  private int imageSize;
  private List<MediaItem> mediaListSelected = new ArrayList<MediaItem>();
  private boolean showWarningVideoDuration;

  public boolean isShowWarningVideoDuration() {
    return showWarningVideoDuration;
  }

  public int getImageSize() {
    return imageSize;
  }

  public List<MediaItem> getMediaListSelected() {
    return mediaListSelected;
  }

  public File getCroppedFile() {
    return croppedFile;
  }

  public int getAspectX() {
    return aspectX;
  }

  public int getAspectY() {
    return aspectY;
  }

  public boolean isFixAspectRatio() {
    return fixAspectRatio;
  }

  public boolean canSelectMultiPhoto() {
    return canSelectMultiPhoto;
  }

  public boolean isCropped() {
    return isCropped;
  }

  public boolean canSelectPhoto() {
    return canSelectPhoto;
  }


  public File getPhotoFile() {
    return photoCaptureFile;
  }

  private MediaOptions(Builder builder) {
    this.canSelectMultiPhoto = builder.canSelectMultiPhoto;
    this.isCropped = builder.isCropped;
    this.canSelectPhoto = builder.canSelectPhoto;
    this.photoCaptureFile = builder.photoFile;
    this.aspectX = builder.aspectX;
    this.aspectY = builder.aspectY;
    this.fixAspectRatio = builder.fixAspectRatio;
    this.croppedFile = builder.croppedFile;
    this.imageSize = builder.imageSize;
    this.mediaListSelected = builder.mediaListSelected;
    this.showWarningVideoDuration = builder.showWarningBeforeRecord;
  }

  /**
   *
   * @return Create a default object {@link MediaOptions}
   */
  public static MediaOptions createDefault() {
    return new Builder().build();
  }

  /**
   * Builder for {@link MediaOptions}
   */
  public static class Builder {
    private boolean canSelectMultiPhoto = false;
    private boolean isCropped = false;
    private boolean canSelectPhoto = true;
    private File photoFile;
    private int aspectX = 1;
    private int aspectY = 1;
    private boolean fixAspectRatio = true;
    private File croppedFile;
    private int imageSize;
    private List<MediaItem> mediaListSelected;
    private boolean showWarningBeforeRecord = false;

    public Builder() {
    }

    /**
     * Set media list that already selected before.
     *
     * @param mediaSelecteds Media list selected before.
     */
    public Builder setMediaListSelected(List<MediaItem> mediaSelecteds) {
      this.mediaListSelected = mediaSelecteds;
      return this;
    }

    public Builder setCroppedFile(File croppedFile) {
      this.croppedFile = croppedFile;
      return this;
    }

    /**
     * Sets whether the aspect ratio is fixed or not; true fixes the aspect
     * ratio, while false allows it to be changed.
     *
     * @param fixAspectRatio Default is true.
     */
    public Builder setFixAspectRatio(boolean fixAspectRatio) {
      this.fixAspectRatio = fixAspectRatio;
      return this;
    }

    /**
     * @param aspectX Default is 1.
     */
    public Builder setAspectX(int aspectX) {
      this.aspectX = aspectX;
      return this;
    }

    /**
     * @param aspectY Default is 1.
     */
    public Builder setAspectY(int aspectY) {
      this.aspectY = aspectY;
      return this;
    }

    /**
     * Crop photo before return back photo. Not effective for video or set
     * {@link MediaOptions.Builder#canSelectMultiPhoto(boolean)} method's
     * parameter =true.
     */
    public Builder setIsCropped(boolean isCropped) {
      this.isCropped = isCropped;
      return this;
    }

    /**
     * Set can select multiple photos or not. If true then
     * {@link #setIsCropped(boolean)} option will be ignored.
     */
    public Builder canSelectMultiPhoto(boolean canSelectMulti) {
      this.canSelectMultiPhoto = canSelectMulti;
      return this;
    }

    /**
     * Only choose video. This method override {@link #selectPhoto()} if set
     * before. Default only choose photo.
     */
    public Builder selectVideo() {
      canSelectPhoto = false;
      return this;
    }

    /**
     * Only choose photo. This method override {@link #selectVideo()} if set
     * before.
     */
    public Builder selectPhoto() {
      canSelectPhoto = true;
      return this;
    }

    public Builder setPhotoCaptureFile(File file) {
      photoFile = file;
      return this;
    }

    public Builder setImageSize(int size) {
      imageSize = size;
      return this;
    }

    /**
     * Build configured {@link MediaOptions} object.
     */
    public MediaOptions build() {
      return new MediaOptions(this);
    }
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(canSelectMultiPhoto ? 1 : 0);
    dest.writeInt(canSelectPhoto ? 1 : 0);
    dest.writeInt(isCropped ? 1 : 0);
    dest.writeInt(fixAspectRatio ? 1 : 0);
    dest.writeInt(showWarningVideoDuration ? 1 : 0);
    dest.writeInt(aspectX);
    dest.writeInt(aspectY);
    dest.writeSerializable(photoCaptureFile);
    dest.writeSerializable(croppedFile);
    dest.writeInt(imageSize);
    dest.writeTypedList(mediaListSelected);
  }

  public MediaOptions(Parcel in) {
    canSelectMultiPhoto = in.readInt() != 0;
    canSelectPhoto = in.readInt() != 0;
    isCropped = in.readInt() != 0;
    fixAspectRatio = in.readInt() != 0;
    showWarningVideoDuration = in.readInt() != 0;
    aspectX = in.readInt();
    aspectY = in.readInt();
    this.photoCaptureFile = (File) in.readSerializable();
    this.croppedFile = (File) in.readSerializable();
    this.imageSize = in.readInt();
    in.readTypedList(this.mediaListSelected, MediaItem.CREATOR);
  }

  public static final Creator<MediaOptions> CREATOR = new Creator<MediaOptions>() {

    @Override public MediaOptions[] newArray(int size) {
      return new MediaOptions[size];
    }

    @Override public MediaOptions createFromParcel(Parcel source) {
      return new MediaOptions(source);
    }
  };

  @Override public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (canSelectMultiPhoto ? 1231 : 1237);
    result = prime * result + (canSelectPhoto ? 1231 : 1237);
    result = prime * result + (isCropped ? 1231 : 1237);
    return result;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MediaOptions other = (MediaOptions) obj;
    if (canSelectMultiPhoto != other.canSelectMultiPhoto) return false;
    if (canSelectPhoto != other.canSelectPhoto) return false;
    if (isCropped != other.isCropped) return false;
    return true;
  }
}