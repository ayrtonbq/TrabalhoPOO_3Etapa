package view;

import controller.AnimalController;
import controller.RegistroServicoController;
import controller.ServicoController;
import model.Animal;
import model.RegistroServico;
import model.Servico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroServicoView extends JFrame {
    private JTextField txtAnimalId, txtServicoId, txtValor, txtObservacoes, txtData;

    public RegistroServicoView(RegistroServicoController registroServicoController, AnimalController animalController, ServicoController servicoController) {
        setTitle("Lançamento de Serviço");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID do Animal:"));
        txtAnimalId = new JTextField();
        panel.add(txtAnimalId);

        panel.add(new JLabel("ID do Serviço:"));
        txtServicoId = new JTextField();
        panel.add(txtServicoId);

        panel.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        panel.add(txtValor);

        panel.add(new JLabel("Observações:"));
        txtObservacoes = new JTextField();
        panel.add(txtObservacoes);

        panel.add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField();
        panel.add(txtData);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    RegistroServico registroServico = new RegistroServico();
                    Animal animal = animalController.buscarAnimalPorId(Integer.parseInt(txtAnimalId.getText()));
                    Servico servico = servicoController.buscarServicoPorId(Integer.parseInt(txtServicoId.getText()));
                    registroServico.setAnimal(animal);
                    registroServico.setServico(servico);
                    registroServico.setValor(Double.parseDouble(txtValor.getText()));
                    registroServico.setObservacoes(txtObservacoes.getText());

                    // Conversão da string da data para o formato Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = dateFormat.parse(txtData.getText());
                    registroServico.setData(data);

                    registroServicoController.adicionarRegistroServico(registroServico);
                    JOptionPane.showMessageDialog(null, "Serviço registrado com sucesso!");
                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter data. Use o formato dd/MM/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter ID ou valor. Insira um número válido.");
                }
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }
}
