package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.api.rest.dto.UserDTO;
import io.quarkus.oidc.UserInfo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/users/me")
public class UserRESTService {
  @Inject
  UserInfo userInfo;

  @GET
  public UserDTO getUserInfo(){
    return UserDTO.toUserDTO(userInfo);
  }
}
