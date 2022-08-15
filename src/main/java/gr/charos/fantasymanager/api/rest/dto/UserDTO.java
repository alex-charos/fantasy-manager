package gr.charos.fantasymanager.api.rest.dto;

import io.quarkus.oidc.UserInfo;
import io.vertx.ext.auth.User;

public record UserDTO(String name, String firstName, String lastName) {

  public static UserDTO toUserDTO(UserInfo userInfo) {
    return new UserDTO( userInfo.getString("name"),
                        userInfo.getString("given_name"),
                        userInfo.getString("family_name"));
  }

}
