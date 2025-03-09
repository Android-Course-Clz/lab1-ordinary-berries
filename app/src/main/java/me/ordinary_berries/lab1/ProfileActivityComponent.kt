package me.ordinary_berries.lab1

import dagger.Component

@Component(modules = [ProfileModule::class])
interface ProfileActivityComponent {
    fun inject(activity: MainActivity)
}