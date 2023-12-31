package com.saartak.el.di.module;

import com.saartak.el.di.key.ViewModelKey;
import com.saartak.el.view_models.DynamicUIViewModel;
import com.saartak.el.view_models.FactoryViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DynamicUIViewModel.class)
    abstract ViewModel bindDynamicUIViewModel(DynamicUIViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
