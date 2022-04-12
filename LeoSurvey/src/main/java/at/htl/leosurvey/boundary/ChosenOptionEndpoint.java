package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.ChosenOptionRepository;
import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.control.S_TransactionRepository;
import at.htl.leosurvey.dtos.DisplayResultDTO;
import at.htl.leosurvey.entities.*;
import com.sun.tools.jconsole.JConsoleContext;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Path("leosurvey")
public class ChosenOptionEndpoint {
    @Inject
    ChosenOptionRepository chosenOptionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    S_TransactionRepository s_transactionRepository;

    @Inject
    QuestionRepository questionRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions")
    public Response findAllChosenOptions(){
        final List<ChosenOption> options = chosenOptionRepository.findAllOptions();
        return Response.ok(options).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chosenoptions/{id}")
    public Response findChosenOptionsByQuestionnaire(@PathParam("id") long id){
        final List<ChosenOption> chosenOptions = chosenOptionRepository.findChosenOptionsByQuestionnaire(id);
        return Response.ok(chosenOptions).build();
    }

    @POST
    @Path("/chosenoptions/add")
    public Response addChosenOption(ChosenOption chosenOption, @Context UriInfo info){
        if(chosenOption.getCo_q().getQ_type().equals("FREETEXT") == false) {
            AnswerOption ao = answerOptionRepository.findAllOptions().get(Math.toIntExact(chosenOption.getCo_ao().getAo_id() - 1));
            ao.setAo_how_often(ao.getAo_how_often() + 1);
            chosenOption.setCo_ao(ao);
        }
        final ChosenOption savedChosenOption = chosenOptionRepository.save(chosenOption);
        URI uri = info.getAbsolutePathBuilder().path("/leosurvey/chosenoptions/add/" + savedChosenOption.getCo_id()).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/chosenoptions/getDTO/{trid}")
    public Response getDisplayDTOs(@PathParam("trid") long trid){
        List<DisplayResultDTO> displayResultDTOS = new LinkedList<>();
        HashMap<AnswerOption, Integer> hashMap = new HashMap<>();
        S_Transaction t = s_transactionRepository.findById(trid);
        List<ChosenOption> coList = chosenOptionRepository.getByTrCode(t.getT_transactioncode());

        for (var item : coList) {
            var ao = answerOptionRepository.findById(item.getCo_ao().getAo_id());
            var aoCount = 0;
            hashMap.put(ao, aoCount);
            displayResultDTOS.add(new DisplayResultDTO(questionRepository.findById(item.getCo_q().getQ_id()), hashMap));
        }

        return Response.ok().entity(displayResultDTOS).build();
    }
}
