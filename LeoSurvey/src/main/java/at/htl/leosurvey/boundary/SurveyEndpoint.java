package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.control.S_TransactionRepository;
import at.htl.leosurvey.control.SurveyRepository;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.entities.S_Transaction;
import at.htl.leosurvey.entities.Survey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("leosurvey")
public class SurveyEndpoint {
    @Inject
    SurveyRepository surveyRepository;

    @Inject
    S_TransactionRepository transactionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/survey")
    public Response findAllSurveys(){
        final List<Survey> surveys = surveyRepository.findAllSurveys();
        return Response.ok(surveys).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createSurvey/{amount}")
    public Response createTransactions(@PathParam("amount")int amount){
        Questionnaire questionnaire = questionnaireRepository.findById(1);
        Survey survey1 = new Survey(LocalDate.now(), questionnaire);
        System.out.println(survey1);
        survey1 = surveyRepository.save(survey1);
        final List<S_Transaction> transactions = this.transactionRepository.generateTransactionCode(survey1, amount);
        return Response
                .ok()
                .entity(transactions)
                .build();
    }

}
