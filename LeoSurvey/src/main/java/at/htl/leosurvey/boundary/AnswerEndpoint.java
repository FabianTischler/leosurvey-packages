package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerRepository;
import at.htl.leosurvey.entities.Answer;
import at.htl.leosurvey.entities.ChosenOption;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("leosurvey")
public class AnswerEndpoint {
    @Inject
    AnswerRepository answerRepository;

    @POST
    @Path("/answer/add")
    public Response addAnswer(Answer answer, @Context UriInfo info){
        final Answer savedAnswer = answerRepository.save(answer);
        URI uri = info.getAbsolutePathBuilder().path("/leosurvey/answer/add/" + savedAnswer.getA_id()).build();
        return Response.created(uri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/answer")
    public Response findAllAnswers(){
        final List<Answer> answers = answerRepository.findAll().list();
        return Response.ok(answers).build();
    }
}
