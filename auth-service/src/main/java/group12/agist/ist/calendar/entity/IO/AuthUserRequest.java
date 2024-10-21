package group12.agist.ist.calendar.entity.IO;

import lombok.NonNull;

public record AuthUserRequest(@NonNull String username, @NonNull String password) {
}