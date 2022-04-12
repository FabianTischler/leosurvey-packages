package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.S_TransactionRepository;
import at.htl.leosurvey.entities.S_Transaction;
import at.htl.leosurvey.entities.Survey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leosurvey")
public class S_TransactionEndpoint {
    @Inject
    S_TransactionRepository transactionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transactioncode")
    public Response findAllCodes(){
        final List<S_Transaction> codes = transactionRepository.listAll();
        return Response.ok(codes).build();
    }
}
