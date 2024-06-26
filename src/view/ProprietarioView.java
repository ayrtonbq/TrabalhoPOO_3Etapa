package view;

import controller.ProprietarioController;
import model.Proprietario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProprietarioView extends JFrame {
    private JTextField txtNome, txtEndereco, txtTelefone, txtEmail;

    public ProprietarioView(ProprietarioController proprietarioController) {
        setTitle("Cadastro de Proprietário");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        panel.add(txtEndereco);

        panel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        panel.add(txtTelefone);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Proprietario proprietario = new Proprietario();
                proprietario.setNome(txtNome.getText());
                proprietario.setEndereco(txtEndereco.getText());
                proprietario.setTelefone(txtTelefone.getText());
                proprietario.setEmail(txtEmail.getText());

                proprietarioController.adicionarProprietario(proprietario);
                JOptionPane.showMessageDialog(null, "Proprietário cadastrado com sucesso!");
                dispose();
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }
}
