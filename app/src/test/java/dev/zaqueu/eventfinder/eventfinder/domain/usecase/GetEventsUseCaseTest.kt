package dev.zaqueu.eventfinder.eventfinder.domain.usecase

import dev.zaqueu.eventfinder.dummy.EventTestDummy
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetEventsUseCaseTest {
    private val eventRepository = mockk<EventRepository>()
    private lateinit var getEventsUseCase: GetEventsUseCase

    @Test
    @Before
    fun `test sut must not be null`() {
        getEventsUseCase = GetEventsUseCase(
            eventRepository
        )
        Assert.assertNotNull(getEventsUseCase)
    }

    @Test
    fun `should return a Result with a list of events if success`() = runBlocking {
        val events = listOf(
            EventTestDummy.create(),
            EventTestDummy.create().copy(id = "2")
        )
        coEvery {
            eventRepository.getEvents()
        } returns events
        val expected = Result.success(events)
        val result = getEventsUseCase()
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `should return a Result failure if eventRepository throws`() = runBlocking {
        val eventException = Exception()
        coEvery {
            eventRepository.getEvents()
        } throws eventException
        val expected = Result.failure<Exception>(eventException)
        val result = getEventsUseCase()
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(expected, result)
    }
}
