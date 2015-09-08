package barqsoft.footballscores;

import android.content.Context;

/**
 * Created by Guanqing on 2015/9/8.
 */
public class App extends android.app.Application {
    private static App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context context(){
        return mApp.getApplicationContext();
    }
}
