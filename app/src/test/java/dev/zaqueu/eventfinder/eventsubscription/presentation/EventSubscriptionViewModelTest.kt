package dev.zaqueu.eventfinder.eventsubscription.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.zaqueu.eventfinder.TestCoroutineRule
import dev.zaqueu.eventfinder.dummy.CheckInTestDummy
import dev.zaqueu.eventfinder.eventsubscription.domain.usecase.CheckInEventUseCase
import dev.zaqueu.eventfinder.utils.getOrAwaitValueTest
import io.mockk.coEvery
import io.mockk.coVerify
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
class EventSubscriptionViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val checkInEventUseCase = mockk<CheckInEventUseCase>()
    private lateinit var eventSubscriptionViewModel: EventSubscriptionViewModel

    @Test
    @Before
    fun `test sut must not be null`() {
        eventSubscriptionViewModel = EventSubscriptionViewModel(
            checkInEventUseCase
        )
        Assert.assertNotNull(eventSubscriptionViewModel)
    }

    @Test
    fun `should call checkInEventUseCase with correct values`() = runBlockingTest {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            checkInEventUseCase(any())
        } returns Result.success(Unit)
        eventSubscriptionViewModel.checkInEvent(checkIn)
        coVerify {
            checkInEventUseCase(checkIn)
        }
    }

    @Test
    fun `should set a CheckInState as success if it succeed`() {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            checkInEventUseCase(any())
        } returns Result.success(Unit)
        eventSubscriptionViewModel.checkInEvent(checkIn)
        val viewState = eventSubscriptionViewModel.checkInState.getOrAwaitValueTest()
        Assert.assertEquals(
            CheckInState.Success,
            viewState
        )
    }

    @Test
    fun `should set a CheckInState as error if it failure`() {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            checkInEventUseCase(any())
        } returns Result.failure(Exception())
        eventSubscriptionViewModel.checkInEvent(checkIn)
        val viewState = eventSubscriptionViewModel.checkInState.getOrAwaitValueTest()
        Assert.assertEquals(
            CheckInState.Error,
            viewState
        )
    }
}
