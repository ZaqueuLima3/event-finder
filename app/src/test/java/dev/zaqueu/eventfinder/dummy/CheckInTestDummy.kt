package dev.zaqueu.eventfinder.dummy

import dev.zaqueu.eventfinder.common.domain.model.CheckIn

class CheckInTestDummy {
    companion object {
        fun create() = CheckIn(
            eventId = "eventdId",
            name = "name",
            email = "anyEmail"
        )
    }
}
