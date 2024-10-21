package group12.agist.ist.calendar.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserDTO {
  private Long id;
  private String name;
  private String email;

}
