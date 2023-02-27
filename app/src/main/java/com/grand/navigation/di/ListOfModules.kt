package com.grand.navigation.di

import com.grand.navigation.ui.home.fragments.food.ProductViewModel
import com.grand.navigation.ui.home.fragments.food.ProductsRepo
import com.grand.navigation.network.ApiClient
import com.grand.navigation.ui.onBoarding.FirstUsing
import com.grand.navigation.ui.onBoarding.OnboardingActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {
    viewModel{ ProductViewModel(repo=get()) }
}
val repo:Module= module {
    single { ProductsRepo(api=get()) }
}
val api:Module= module {
    single { ApiClient.getRetrofit() }
}

val onBoarding:Module= module {
    single { FirstUsing(get()) }
    factory { OnboardingActivity() }
}

