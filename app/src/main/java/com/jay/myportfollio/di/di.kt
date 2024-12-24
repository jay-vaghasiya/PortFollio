package com.jay.myportfollio.di

import com.jay.myportfollio.model.repository.ProfileRepository
import com.jay.myportfollio.model.repository.ProfileRepositoryImpl
import com.jay.myportfollio.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl() }
    viewModel { ProfileViewModel(get()) }
}