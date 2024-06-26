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

public class RelatorioView extends JFrame {
    private JTextField txtClienteId, txtDataInicio, txtDataFim;
    private JTextArea textAreaRelatorio;

    public RelatorioView(RegistroServicoController registroServicoController) {
        setTitle("Geração de Relatório");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID do Cliente:"));
        txtClienteId = new JTextField();
        panel.add(txtClienteId);

        panel.add(new JLabel("Data Início (dd/MM/yyyy):"));
        txtDataInicio = new JTextField();
        panel.add(txtDataInicio);

        panel.add(new JLabel("Data Fim (dd/MM/yyyy):"));
        txtDataFim = new JTextField();
        panel.add(txtDataFim);

        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int clienteId = Integer.parseInt(txtClienteId.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataInicio = dateFormat.parse(txtDataInicio.getText());
                    Date dataFim = dateFormat.parse(txtDataFim.getText());

                    List<RegistroServico> relatorio = registroServicoController.gerarRelatorio(clienteId, dataInicio, dataFim);
                    textAreaRelatorio.setText("");
                    for (RegistroServico registro : relatorio) {
                        textAreaRelatorio.append(registro.toString() + "\n");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter data. Use o formato dd/MM/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter ID do cliente. Insira um número válido.");
                }
            }
        });
        panel.add(btnGerar);

        textAreaRelatorio = new JTextArea();
        panel.add(new JScrollPane(textAreaRelatorio));

        add(panel);
    }
}
