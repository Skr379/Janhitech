package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import dataaccess.History;
import dataaccess.HistoryDao;
import lombok.RequiredArgsConstructor;
import responses.HistoryResponse;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sandeep.roy on 19/03/18.
 */
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class PatientHistoryService {
    private final HistoryDao historyDao;
    private final ObjectMapper mapper;

    public Response getPatientHistory(Long patientId,Integer page,Integer size ,Integer start) throws SQLException, JsonProcessingException {
        List<History> historyList=historyDao.getHistory(patientId,page,size);
        if(historyList==null){
            return Response.status(Response.Status.OK).entity("patient info not found").build();
        }
        List<History> list=historyList.subList(start,start+size);
        HistoryResponse responses=new HistoryResponse(list);

        return Response.status(Response.Status.OK).entity(mapper.writeValueAsString(responses)).build();
    }

    public Response insertNewRecord(History history) throws SQLException {
        historyDao.insert(history);
        return Response.status(Response.Status.OK).entity("success").build();
    }


}
