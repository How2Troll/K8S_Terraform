package group12.agist.ist.calendar.entity.IO;

import lombok.NonNull;

import java.util.List;

public record AddParticipantsRequest(@NonNull List<Long> userIds) {
}