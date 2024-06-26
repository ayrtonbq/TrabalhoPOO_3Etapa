package controller;

import java.util.Date;
import java.util.List;

import model.RegistroServico;
import model.dao.RegistroServicoDAO;

public class RegistroServicoController {
    private RegistroServicoDAO registroServicoDAO;

    public RegistroServicoController() {
        this.registroServicoDAO = new RegistroServicoDAO();
    }

    public void adicionarRegistroServico(RegistroServico registroServico) {
        registroServicoDAO.adiciona(registroServico);
    }

    public List<RegistroServico> consultarHistoricoPorAnimal(int animalId, Date dataInicio, Date dataFim,
            String tipoServico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarHistoricoPorAnimal'");
    }

    public List<RegistroServico> gerarRelatorio(int clienteId, Date dataInicio, Date dataFim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarRelatorio'");
    }

    // Outros métodos de controle para leitura, atualização e deleção...
}
