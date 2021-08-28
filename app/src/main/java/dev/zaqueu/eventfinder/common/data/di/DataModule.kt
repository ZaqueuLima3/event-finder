package dev.zaqueu.eventfinder.common.data.di

import dev.zaqueu.eventfinder.common.data.remote.repository.SicrediEventRepository
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {
    fun load() {
        loadKoinModules(repositoryModules() + NetworkModule.modules)
    }

    private fun repositoryModules(): Module {
        return module {
            single<EventRepository> {
                SicrediEventRepository(
                    eventApi = get()
                )
            }
        }
    }
}
