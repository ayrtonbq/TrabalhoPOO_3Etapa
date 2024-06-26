package controller;

import model.dao.RegistroServicoDAO;
import model.RegistroServico;

import java.util.Date;
import java.util.List;

public class RelatorioController {
    private RegistroServicoDAO registroServicoDAO;

    public RelatorioController() {
        this.registroServicoDAO = new RegistroServicoDAO();
    }

    public List<RegistroServico> gerarRelatorioCliente(int proprietarioId, Date dataInicio, Date dataFim) {
        // Implementar lógica para gerar relatório
        return registroServicoDAO.getHistoricoPorAnimal(proprietarioId, dataInicio, dataFim);
    }
}
