package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import dataaccess.PatientDao;
import dataaccess.Patients;
import lombok.RequiredArgsConstructor;
import responses.ContactNumberResponse;
import responses.ErrorResponse;
import responses.PatientResponse;

import javax.ws.rs.core.Response;

/**
 * Created by sandeep.roy on 19/03/18.
 */
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class PatientService {
    private static final  ObjectMapper mapper= new ObjectMapper();
    private final PatientDao patientDao;
    static {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
    }


    public Response getInfo(Long id) throws JsonProcessingException {
        Patients patients=patientDao.findById(id);
        if(patients==null){
            ErrorResponse errorResponse= new ErrorResponse("Patient information not found in database.");

            return Response.status(Response.Status.OK).entity(errorResponse).build();
        }

        PatientResponse response= new PatientResponse(patients);

        return Response.status(Response.Status.OK).entity(mapper.writeValueAsString(response)).header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public Response getConactNumber(Long id) throws JsonProcessingException{
        Patients patients=patientDao.findById(id);
        if(patients==null){
            ErrorResponse errorResponse= new ErrorResponse("Patient information not found in database.");

            return Response.status(Response.Status.OK).entity(errorResponse).header("Access-Control-Allow-Origin", "*").build();
        }
        ContactNumberResponse response=new ContactNumberResponse(patients);
        return Response.status(Response.Status.OK).entity(mapper.writeValueAsString(response))
                .build();
    }

}
