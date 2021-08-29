package dev.zaqueu.eventfinder.eventsubscription.di

import dev.zaqueu.eventfinder.eventsubscription.presentation.EventSubscriptionFragment
import dev.zaqueu.eventfinder.eventsubscription.presentation.EventSubscriptionViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object EventSubscriptionModule {
    fun load() {
        loadKoinModules(presentationModules())
    }

    private fun presentationModules(): Module {
        return module {
            fragment {
                EventSubscriptionFragment(
                    eventSubscriptionViewModel = get()
                )
            }

            viewModel {
                EventSubscriptionViewModel(

                )
            }
        }
    }
}
