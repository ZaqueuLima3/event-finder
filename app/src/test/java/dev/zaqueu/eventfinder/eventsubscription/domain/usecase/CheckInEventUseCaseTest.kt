package dev.zaqueu.eventfinder.eventsubscription.domain.usecase

import dev.zaqueu.eventfinder.dummy.CheckInTestDummy
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CheckInEventUseCaseTest {
    private val eventRepository = mockk<EventRepository>()
    private lateinit var checkInEventUseCase: CheckInEventUseCase

    @Test
    @Before
    fun `test sut must not be null`() {
        checkInEventUseCase = CheckInEventUseCase(
            eventRepository
        )
        Assert.assertNotNull(checkInEventUseCase)
    }

    @Test
    fun `should call checkInEvent with correct values`() = runBlocking {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            eventRepository.checkInEvent(any())
        } just Runs
        checkInEventUseCase(checkIn)
        coVerify {
            eventRepository.checkInEvent(checkIn)
        }
    }

    @Test
    fun `should return Result success if succeed`() = runBlocking {
        val checkIn = CheckInTestDummy.create()
        coEvery {
            eventRepository.checkInEvent(any())
        } just Runs
        val result = checkInEventUseCase(checkIn)
        Assert.assertTrue(result.isSuccess)
    }

    @Test
    fun `should Result error id checkInEvent throws an Exception`() = runBlocking {
        val checkIn = CheckInTestDummy.create()
        val exception = Exception()
        coEvery {
            eventRepository.checkInEvent(any())
        } throws exception
        val expected = Result.failure<Exception>(exception)
        val result = checkInEventUseCase(checkIn)
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(expected, result)
    }
}
