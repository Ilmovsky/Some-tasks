package com.alex.problem.di;

import com.alex.problem.data.DataModule;
import com.alex.problem.domain.DomainModule;
import com.alex.problem.presentation.View;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        DataModule.class,
        DomainModule.class
})
public interface AppComponent {

    void inject(View view);
}
