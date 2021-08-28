package dev.zaqueu.eventfinder.eventfinder.di

import dev.zaqueu.eventfinder.eventfinder.domain.usecase.GetEventsUseCase
import dev.zaqueu.eventfinder.eventfinder.presentation.EventFinderFragment
import dev.zaqueu.eventfinder.eventfinder.presentation.EventFinderViewModel
import dev.zaqueu.eventfinder.eventfinder.presentation.EventsAdapter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object EventFinderModule {
    fun load() {
        loadKoinModules(presentationModules() + domainModules())
    }

    private fun domainModules(): Module {
        return module {
            factory {
                GetEventsUseCase(
                    eventRepository = get()
                )
            }
        }
    }

    private fun presentationModules(): Module {
        return module {
            single {
                EventsAdapter(
                    glide = get()
                )
            }

            fragment {
                EventFinderFragment(
                    eventFinderViewModel = get(),
                    eventsAdapter = get()
                )
            }

            viewModel {
                EventFinderViewModel(
                    getEventsUseCase = get()
                )
            }
        }
    }
}
