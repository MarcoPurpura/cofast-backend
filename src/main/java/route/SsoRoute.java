package route;
import constants.enums.RuoliUtente;
import dao.mapper.JwtResponse;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
@Slf4j
@RouteBase(produces = "application/json")
public class SsoRoute /* extends HttpRoute  */{
    //Quando avremo le dipendenze va fatto l'extends e l'override
    @Inject
    JsonWebToken jwt;
    /*@Override
    public boolean bodyContentLogged() {
        return false;
    }

     */
    @Route(methods = Route.HttpMethod.GET, path = "sso/generate/:ruoloUtente")
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Uni<JwtResponse> generate(@Param String ruoloUtente) {
        String token =
                Jwt.issuer("https://tokengen.com/issuer")
                        .upn("mark@quarkus.io")
                        .expiresIn(Duration.ofMillis(1000*60*60*24))
                        .groups(new HashSet<>(Arrays.asList(RuoliUtente.APP_NAME+ruoloUtente)))
                        .sign();

        return Uni.createFrom().item(new JwtResponse(token));
    }
}
