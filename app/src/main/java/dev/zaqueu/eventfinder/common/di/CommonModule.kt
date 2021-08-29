package dev.zaqueu.eventfinder.common.di

import android.location.Geocoder
import com.bumptech.glide.Glide
import dev.zaqueu.eventfinder.common.data.remote.repository.ApiEventRepository
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import dev.zaqueu.eventfinder.common.utils.DateHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

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
            factory {
                Geocoder(
                    androidContext(),
                    Locale.getDefault()
                )
            }
            factory {
                DateHelper()
            }
        }
    }
}
