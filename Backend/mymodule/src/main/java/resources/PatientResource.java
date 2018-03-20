package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import responses.ErrorResponse;
import services.PatientService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Path("/patient")
@RequiredArgsConstructor( onConstructor = @_(@Inject))
public class PatientResource {
    private final PatientService patientService;
    @GET
    @Path("/fetch/{id}")
    public Response getPatientInfo(@PathParam("id") Long id) throws JsonProcessingException {
        try {
            return patientService.getInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            ErrorResponse errorResponse= new ErrorResponse("Patient information not found in database.");
            return Response.status(Response.Status.OK).entity(errorResponse).build();
        }
    }

    @GET
    @Path("/contactNumber/{id}")
    public Response getContactNumber(@PathParam("id") Long id) throws JsonProcessingException {
        try {
            return patientService.getConactNumber(id);
        }catch (Exception e){
            e.printStackTrace();
            ErrorResponse errorResponse= new ErrorResponse("Patient information not found in database.");

            return Response.status(Response.Status.OK).entity(errorResponse).build();
        }
    }



}
