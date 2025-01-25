package com.jay.myportfollio.di

import android.util.Log
import com.jay.myportfollio.model.repository.experience.ExperienceRepository
import com.jay.myportfollio.model.repository.experience.ExperienceRepositoryImpl
import com.jay.myportfollio.model.repository.profile.ProfileRepository
import com.jay.myportfollio.model.repository.profile.ProfileRepositoryImpl
import com.jay.myportfollio.model.repository.project.ProjectRepository
import com.jay.myportfollio.model.repository.project.ProjectRepositoryImpl
import com.jay.myportfollio.model.repository.skills.SkillsRepository
import com.jay.myportfollio.model.repository.skills.SkillsRepositoryImpl
import com.jay.myportfollio.viewmodel.ExperienceViewModel
import com.jay.myportfollio.viewmodel.ProfileViewModel
import com.jay.myportfollio.viewmodel.ProjectViewModel
import com.jay.myportfollio.viewmodel.SkillsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ProfileRepository> {
        Log.d("KoinModule", "Profile Repository initialized")
        ProfileRepositoryImpl() }
    viewModel { ProfileViewModel(get()) }

    single<ExperienceRepository> {
        Log.d("KoinModule", "Experience Repository initialized")
        ExperienceRepositoryImpl() }
    viewModel { ExperienceViewModel(get()) }

    single<ProjectRepository> {
        Log.d("KoinModule", "Projects Repository initialized")
        ProjectRepositoryImpl()
    }
    viewModel { ProjectViewModel(get()) }

    single<SkillsRepository> {
        Log.d("KoinModule", "Skills Repository initialized")
        SkillsRepositoryImpl()
    }
    viewModel { SkillsViewModel(get()) }
}