package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import dataaccess.History;
import lombok.RequiredArgsConstructor;
import services.PatientHistoryService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Path("/history")
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class HistoryResource {
    private final PatientHistoryService patientHistoryService;
    @GET
    @Path("/fetch")
    public Response getHistory(@QueryParam("id")Long id,@QueryParam("page")Integer page,@QueryParam("size")Integer size) throws JsonProcessingException {
        try {

            int start=(page - 1)*size;
            return patientHistoryService.getPatientHistory(id,page,size,start);


        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity("{}").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @Path("/insert")
    @POST
    public Response insertPatientRecord(History history){

        try {
            return patientHistoryService.insertNewRecord(history);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity("failed to insert").build();
        }

    }
}
