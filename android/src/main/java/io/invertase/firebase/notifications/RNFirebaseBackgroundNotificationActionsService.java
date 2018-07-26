package io.invertase.firebase.notifications;

import android.content.Intent;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

import java.util.HashMap;

import javax.annotation.Nullable;

import static io.invertase.firebase.notifications.RNFirebaseBackgroundNotificationActionReceiver.isBackgroundNotficationIntent;

public class RNFirebaseBackgroundNotificationActionsService extends HeadlessJsTaskService {
  @Override
  protected @Nullable HeadlessJsTaskConfig getTaskConfig(Intent intent) {
    if (isBackgroundNotficationIntent(intent)) {
      HashMap<String,Object> readableNotificationOpenMap = (HashMap) intent.getExtras().getSerializable("notificationOpen");
      WritableMap notificationOpenMap = Arguments.makeNativeMap(readableNotificationOpenMap);
      return new HeadlessJsTaskConfig(
        "RNFirebaseBackgroundNotificationAction",
        notificationOpenMap,
        60000,
        true
      );
    }
    return null;
  }
}
