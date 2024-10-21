package group12.agist.ist.calendar.entity.IO;

import lombok.NonNull;

import java.time.LocalDateTime;

public record CreateEventRequest(
    @NonNull String title, String description, @NonNull LocalDateTime startTime,
    @NonNull LocalDateTime endTime, @NonNull Long userId) {
}
