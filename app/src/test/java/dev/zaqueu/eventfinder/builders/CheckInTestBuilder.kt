package dev.zaqueu.eventfinder.builders

import dev.zaqueu.eventfinder.common.domain.model.CheckIn

class CheckInTestBuilder {
    companion object {
        fun build() = CheckIn(
            eventId = "eventdId",
            name = "name",
            email = "anyEmail"
        )
    }
}
