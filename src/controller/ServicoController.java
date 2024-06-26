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

    public Servico buscarServicoPorId(int id) {
        return servicoDAO.buscaPorId(id);
    }

    public void deletarServico(int id) {
        servicoDAO.deleta(id);
    }

    public void atualizarServico(Servico servicoAtual) {
        servicoDAO.atualiza(servicoAtual);
    }

    // Outros métodos de controle para leitura, atualização e deleção...
}
