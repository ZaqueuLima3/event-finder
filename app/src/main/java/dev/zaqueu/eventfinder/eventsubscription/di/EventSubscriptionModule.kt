package dev.zaqueu.eventfinder.eventsubscription.di

import dev.zaqueu.eventfinder.eventsubscription.domain.usecase.CheckInEventUseCase
import dev.zaqueu.eventfinder.eventsubscription.presentation.EventSubscriptionFragment
import dev.zaqueu.eventfinder.eventsubscription.presentation.EventSubscriptionViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object EventSubscriptionModule {
    fun load() {
        loadKoinModules(presentationModules() + domainModules())
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
                    checkInEventUseCase = get()
                )
            }
        }
    }

    private fun domainModules(): Module {
        return  module {
            factory {
                CheckInEventUseCase(
                    eventRepository = get()
                )
            }
        }
    }
}
