package dev.zaqueu.eventfinder.eventfinder.di

import dev.zaqueu.eventfinder.eventdescription.presentation.EventDescriptionFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object EventDescriptionModule {
    fun load() {
        loadKoinModules(presentationModules())
    }

    private fun presentationModules(): Module {
        return module {
            fragment {
                EventDescriptionFragment(
                    glide = get()
                )
            }
        }
    }
}
