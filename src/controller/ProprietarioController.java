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

    // Outros métodos de controle para leitura, atualização e deleção...
}
