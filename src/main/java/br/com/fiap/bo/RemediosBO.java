package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.beans.Remedios;
import br.com.fiap.dao.RemediosDAO;

public class RemediosBO {

    private RemediosDAO remediosDAO;

    // Selecionar todos os remédios
    public ArrayList<Remedios> selecionarBo() throws ClassNotFoundException, SQLException {
        remediosDAO = new RemediosDAO();
        return (ArrayList<Remedios>) remediosDAO.selecionar();
    }

    // Selecionar um único remédio pelo ID
    public Remedios selecionarPorId(int id_remedio) throws ClassNotFoundException, SQLException {
        remediosDAO = new RemediosDAO();
        return remediosDAO.buscarPorId(id_remedio);
    }

    // Inserir
    public void inserirBo(Remedios remedio) throws ClassNotFoundException, SQLException {
        remediosDAO = new RemediosDAO();
        remediosDAO.inserir(remedio);
    }

    // Atualizar
    public void atualizarBo(Remedios remedio) throws ClassNotFoundException, SQLException {
        remediosDAO = new RemediosDAO();
        remediosDAO.atualizar(remedio);
    }

    // Deletar
    public void deletarBo(int id_remedio) throws ClassNotFoundException, SQLException {
        remediosDAO = new RemediosDAO();
        remediosDAO.deletar(id_remedio);
    }
}
