package com.jay.myportfollio.di

import android.util.Log
import com.jay.myportfollio.model.repository.experience.ExperienceRepository
import com.jay.myportfollio.model.repository.experience.ExperienceRepositoryImpl
import com.jay.myportfollio.model.repository.profile.ProfileRepository
import com.jay.myportfollio.model.repository.profile.ProfileRepositoryImpl
import com.jay.myportfollio.viewmodel.ExperienceViewModel
import com.jay.myportfollio.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProfileRepository> {
        Log.d("KoinModule", "Repository initialized")
        ProfileRepositoryImpl() }
    viewModel { ProfileViewModel(get()) }

    single<ExperienceRepository> {
        Log.d("KoinModule", "Repository initialized")
        ExperienceRepositoryImpl() }
    viewModel { ExperienceViewModel(get()) }
}