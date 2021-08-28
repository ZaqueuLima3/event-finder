package dev.zaqueu.eventfinder.common.di

import com.bumptech.glide.Glide
import dev.zaqueu.eventfinder.common.data.remote.repository.ApiEventRepository
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object CommonModule {
    fun load() {
        loadKoinModules(repositoryModules() + utils() + NetworkModule.modules)
    }

    private fun repositoryModules(): Module {
        return module {
            single<EventRepository> {
                ApiEventRepository(
                    eventApi = get()
                )
            }
        }
    }

    private fun utils(): Module {
        return module {
            factory {
                Glide.with(androidContext())
            }
        }
    }
}
