package com.saartak.el.di.component;

import android.app.Application;

import com.saartak.el.App;
import com.saartak.el.di.module.ActivityModule;
import com.saartak.el.di.module.AppModule;
import com.saartak.el.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
