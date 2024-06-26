package view;

import controller.ServicoController;
import model.Servico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicoView extends JFrame {
    private JTextField txtNome, txtDescricao, txtPreco;

    public ServicoView(ServicoController servicoController) {
        setTitle("Cadastro de Serviço");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        panel.add(txtDescricao);

        panel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        panel.add(txtPreco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Servico servico = new Servico();
                servico.setNome(txtNome.getText());
                servico.setDescricao(txtDescricao.getText());
                servico.setPreco(Double.parseDouble(txtPreco.getText()));

                servicoController.adicionarServico(servico);
                JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
                dispose();
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }
}
