package dev.zaqueu.eventfinder.eventfinder.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.zaqueu.eventfinder.TestCoroutineRule
import dev.zaqueu.eventfinder.builders.EventTestBuilder
import dev.zaqueu.eventfinder.eventfinder.domain.usecase.GetEventsUseCase
import dev.zaqueu.eventfinder.utils.getOrAwaitValueTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class EventFinderViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getEventsUseCase = mockk<GetEventsUseCase>()
    private lateinit var eventFinderViewModel: EventFinderViewModel

    @Test
    @Before
    fun `test sut must not be null`() {
        eventFinderViewModel = EventFinderViewModel(
            getEventsUseCase
        )
        Assert.assertNotNull(eventFinderViewModel)
    }

    @Test
    fun `should eventViewState receive a list of events if success`() = runBlockingTest {
        val events = listOf(
            EventTestBuilder.build(),
            EventTestBuilder.build().copy(id = "2")
        )
        coEvery {
            getEventsUseCase()
        } returns Result.success(events)
        eventFinderViewModel.getEvents()
        val eventViewState = eventFinderViewModel.eventViewState.getOrAwaitValueTest()
        Assert.assertEquals(
            EventFinderViewState.Success(events),
            eventViewState
        )
    }

    @Test
    fun `should eventViewState receive error view states if failure`() = runBlockingTest {
        val eventException = Exception()
        coEvery {
            getEventsUseCase()
        } returns Result.failure(eventException)
        eventFinderViewModel.getEvents()
        val eventViewState = eventFinderViewModel.eventViewState.getOrAwaitValueTest()
        Assert.assertEquals(
            EventFinderViewState.Error(eventException),
            eventViewState
        )
    }
}
