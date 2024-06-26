package controller;

import model.Servico;
import model.dao.ServicoDAO;

public class ServicoController {
    private ServicoDAO servicoDAO;

    public ServicoController() {
        this.servicoDAO = new ServicoDAO();
    }

    public void adicionarServico(Servico servico) {
        servicoDAO.adiciona(servico);
    }

    public Servico buscarServicoPorId(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarServicoPorId'");
    }

    // Outros métodos de controle para leitura, atualização e deleção...
}
