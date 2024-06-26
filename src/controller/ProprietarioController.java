package controller;

import model.Proprietario;
import model.dao.ProprietarioDAO;

public class ProprietarioController {
    private ProprietarioDAO proprietarioDAO;

    public ProprietarioController() {
        this.proprietarioDAO = new ProprietarioDAO();
    }

    public void adicionarProprietario(Proprietario proprietario) {
        proprietarioDAO.adiciona(proprietario);
    }

    public void deletarProprietario(int id) {
        proprietarioDAO.deleta(id);
    }

    public void atualizarProprietario(Proprietario proprietarioAtual) {
        proprietarioDAO.atualiza(proprietarioAtual);
    }

    public Proprietario buscarProprietarioPorId(int id) {
        return proprietarioDAO.buscaPorId(id);
    }

    // Outros métodos de controle para leitura, atualização e deleção...
}
