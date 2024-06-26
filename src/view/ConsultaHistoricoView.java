package view;

import controller.RegistroServicoController;
import model.RegistroServico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConsultaHistoricoView extends JFrame {
    private JTextField txtAnimalId, txtDataInicio, txtDataFim, txtTipoServico;
    private JTextArea textAreaHistorico;

    public ConsultaHistoricoView(RegistroServicoController registroServicoController) {
        setTitle("Consulta de Histórico de Serviços");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID do Animal:"));
        txtAnimalId = new JTextField();
        panel.add(txtAnimalId);

        panel.add(new JLabel("Data Início (dd/MM/yyyy):"));
        txtDataInicio = new JTextField();
        panel.add(txtDataInicio);

        panel.add(new JLabel("Data Fim (dd/MM/yyyy):"));
        txtDataFim = new JTextField();
        panel.add(txtDataFim);

        panel.add(new JLabel("Tipo de Serviço:"));
        txtTipoServico = new JTextField();
        panel.add(txtTipoServico);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int animalId = Integer.parseInt(txtAnimalId.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataInicio = dateFormat.parse(txtDataInicio.getText());
                    Date dataFim = dateFormat.parse(txtDataFim.getText());
                    String tipoServico = txtTipoServico.getText();
                    
                    List<RegistroServico> historico = registroServicoController.consultarHistoricoPorAnimal(animalId, dataInicio, dataFim, tipoServico);
                    textAreaHistorico.setText("");
                    for (RegistroServico registro : historico) {
                        textAreaHistorico.append(registro.toString() + "\n");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter data. Use o formato dd/MM/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter ID do animal. Insira um número válido.");
                }
            }
        });
        panel.add(btnConsultar);

        textAreaHistorico = new JTextArea();
        panel.add(new JScrollPane(textAreaHistorico));

        add(panel);
    }
}
