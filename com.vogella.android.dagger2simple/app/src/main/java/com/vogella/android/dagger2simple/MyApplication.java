    package com.vogella.android.dagger2simple;

    import android.app.Application;

    import com.vogella.android.dagger2simple.components.DaggerDiComponent;
    import com.vogella.android.dagger2simple.components.DiComponent;

    public class MyApplication extends Application {
        DiComponent component;

        @Override
        public void onCreate() {
            super.onCreate();
            component = DaggerDiComponent.builder().build();
        }

        public DiComponent getComponent() {
            return component;
        }
    }