package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.domain.PredictionResult;
import gr.charos.fantasymanager.entity.PredictionResultEntity;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("/results")
@Authenticated
public class PredictionResultsRESTService {
  @Inject
  UserInfo userInfo;

  @GET
  public List<PredictionResult> getPredictionResults() {

    return PredictionResultEntity.findByPredictor(userInfo.getString("email")).stream().map(PredictionResultEntity::toPredictionResult).collect(Collectors.toList());

  }
}
