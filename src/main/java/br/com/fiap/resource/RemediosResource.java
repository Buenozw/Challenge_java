package br.com.fiap.resource;

import br.com.fiap.beans.Remedios;
import br.com.fiap.bo.RemediosBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("/remedios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RemediosResource {

    private final RemediosBO remediosBO = new RemediosBO();

    // Selecionar todos os remédios
    @GET
    public List<Remedios> listarTodos() throws ClassNotFoundException, SQLException {
        return remediosBO.selecionarBo();
    }

    // Selecionar um remédio específico por ID
    @GET
    @Path("/{id_remedio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Remedios listar_remedios(@PathParam("id_remedio") int id_remedio)
            throws ClassNotFoundException, SQLException {
        return remediosBO.selecionarPorId(id_remedio);
    }

    // Inserir
    @POST
    public Response inserir(Remedios remedios, @Context UriInfo uriInfo)
            throws ClassNotFoundException, SQLException {

        remediosBO.inserirBo(remedios);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(remedios.getId_remedio()));

        return Response.created(builder.build())
                .entity("Remédio cadastrado com sucesso ✅!")
                .build();
    }

    // Atualizar
    @PUT
    @Path("/{id_Remedio}")
    public Response atualizar(@PathParam("id_Remedio") int idRemedio, Remedios remedios)
            throws ClassNotFoundException, SQLException {

        remedios.setId_remedio(idRemedio);
        remediosBO.atualizarBo(remedios);

        return Response.ok("Remédio atualizado com sucesso ✅!").build();
    }

    // Deletar
    @DELETE
    @Path("/{id_Remedio}")
    public Response deletar(@PathParam("id_Remedio") int idRemedio)
            throws ClassNotFoundException, SQLException {

        remediosBO.deletarBo(idRemedio);
        return Response.ok("Remédio deletado com sucesso ✅!").build();
    }
}
