package group12.agist.ist.calendar.entity.IO;


import lombok.NonNull;

public record CreateUserRequest(@NonNull String name, @NonNull String email) {
}