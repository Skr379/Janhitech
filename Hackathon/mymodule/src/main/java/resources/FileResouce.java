package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Path("/file")
public class FileResouce {

    @GET
    @Path("/{id}")
    public Response getFile(@PathParam("id") Long fileId){
        String path="/Users/sandeep.roy/Downloads/invoice.pdf";
        File file= new File(path);
        return Response.status(Response.Status.OK).entity(file).header("Content-Disposition", "attachment;  filename=\"" + file.getName() + "\"").header("Access-Control-Allow-Origin","*").build();
    }
}
