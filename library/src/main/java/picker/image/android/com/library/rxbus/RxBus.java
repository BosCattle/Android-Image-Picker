package picker.image.android.com.library.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Kevin on 2016/7/6.
 * RxBus
 */
public class RxBus {

  private static volatile RxBus mDefaultInstance;

  private final Subject mBus;

  public RxBus() {
    mBus = new SerializedSubject<>(PublishSubject.create());
  }

  public static RxBus getDefault() {
    RxBus rxBus = mDefaultInstance;
    if (mDefaultInstance == null) {
      synchronized (RxBus.class) {
        rxBus = mDefaultInstance;
        if (mDefaultInstance == null) {
          rxBus = new RxBus();
          mDefaultInstance = rxBus;
        }
      }
    }
    return rxBus;
  }

  public void post(Object event) {
    mBus.onNext(event);
  }

  public <T> Observable toObserverable(Class<T> eventType) {
    return mBus.ofType(eventType);
  }
}
