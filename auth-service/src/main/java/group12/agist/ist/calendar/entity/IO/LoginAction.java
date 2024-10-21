package group12.agist.ist.calendar.entity.IO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginAction {

  private Boolean success;
  private String username;

  public LoginAction(Boolean success) {
    this.success = success;
  }

}
