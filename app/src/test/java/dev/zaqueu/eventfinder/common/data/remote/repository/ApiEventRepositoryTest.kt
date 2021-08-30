package dev.zaqueu.eventfinder.common.data.remote.repository

import dev.zaqueu.eventfinder.dummy.CheckInTestDummy
import dev.zaqueu.eventfinder.dummy.EventResponseTestDummy
import dev.zaqueu.eventfinder.common.data.remote.mapper.mapToModel
import dev.zaqueu.eventfinder.common.data.remote.services.EventApi
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiEventRepositoryTest {
    private val eventApi = mockk<EventApi>()
    private lateinit var eventRepository: EventRepository

    @Test
    @Before
    fun `test sut must not be null`() {
        eventRepository = ApiEventRepository(
            eventApi
        )
        Assert.assertNotNull(eventRepository)
    }

    @Test
    fun `should return a list of events if success`() = runBlocking {
        val events = listOf(
            EventResponseTestDummy.create(),
            EventResponseTestDummy.create().copy(id = "2")
        )
        coEvery {
            eventApi.getEvents()
        } returns events
        val expected = events.map { it.mapToModel() }
        val response = eventRepository.getEvents()
        Assert.assertEquals(expected, response)
    }

    @Test(expected = Exception::class)
    fun `should throws an exception if the eventApi throws`() {
        runBlocking {
            coEvery {
                eventApi.getEvents()
            } throws Exception()
            eventRepository.getEvents()
        }
    }

    @Test
    fun `should call eventApi when invoke checkInEvent with correct values`() = runBlocking {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            eventApi.checkInEvent(any())
        } just Runs
        eventRepository.checkInEvent(checkIn)
        coVerify {
            eventApi.checkInEvent(checkIn)
        }
    }
}
