package view;

import controller.ProprietarioController;
import model.Proprietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProprietarioView extends JFrame {
    private JTextField txtNome, txtEndereco, txtTelefone, txtEmail, txtBuscaId;
    private JButton btnNovo, btnBuscar, btnSalvar, btnAlterar, btnExcluir;
    private Proprietario proprietarioAtual;

    public ProprietarioView(ProprietarioController proprietarioController) {
        setTitle("Cadastro de Proprietário");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Seção de busca
        panel.add(new JLabel("Buscar por ID:"));
        txtBuscaId = new JTextField();
        panel.add(txtBuscaId);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtBuscaId.getText());
                proprietarioAtual = proprietarioController.buscarProprietarioPorId(id);
                if (proprietarioAtual != null) {
                    preencherCampos(proprietarioAtual);
                    habilitarCampos(true);
                    btnAlterar.setEnabled(true);
                    btnExcluir.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Proprietário não encontrado.");
                    limparCampos();
                    habilitarCampos(false);
                    btnAlterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
        panel.add(btnBuscar);

        // Seção de dados do proprietário
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botão Novo
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                habilitarCampos(true);
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);
                proprietarioAtual = null;
            }
        });
        buttonPanel.add(btnNovo);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (proprietarioAtual == null) {
                    proprietarioAtual = new Proprietario();
                }
                proprietarioAtual.setNome(txtNome.getText());
                proprietarioAtual.setEndereco(txtEndereco.getText());
                proprietarioAtual.setTelefone(txtTelefone.getText());
                proprietarioAtual.setEmail(txtEmail.getText());

                if (proprietarioAtual.getId() == 0) {
                    proprietarioController.adicionarProprietario(proprietarioAtual);
                    JOptionPane.showMessageDialog(null, "Proprietário cadastrado com sucesso!");
                } else {
                    proprietarioController.atualizarProprietario(proprietarioAtual);
                    JOptionPane.showMessageDialog(null, "Proprietário atualizado com sucesso!");
                }
                limparCampos();
                habilitarCampos(false);
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);
            }
        });
        buttonPanel.add(btnSalvar);

        // Botão Alterar
        btnAlterar = new JButton("Alterar");
        btnAlterar.setEnabled(false);
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (proprietarioAtual != null) {
                    proprietarioAtual.setNome(txtNome.getText());
                    proprietarioAtual.setEndereco(txtEndereco.getText());
                    proprietarioAtual.setTelefone(txtTelefone.getText());
                    proprietarioAtual.setEmail(txtEmail.getText());

                    proprietarioController.atualizarProprietario(proprietarioAtual);
                    JOptionPane.showMessageDialog(null, "Proprietário atualizado com sucesso!");
                    limparCampos();
                    habilitarCampos(false);
                    btnAlterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
        buttonPanel.add(btnAlterar);

        // Botão Excluir
        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (proprietarioAtual != null) {
                    proprietarioController.deletarProprietario(proprietarioAtual.getId());
                    JOptionPane.showMessageDialog(null, "Proprietário deletado com sucesso!");
                    limparCampos();
                    habilitarCampos(false);
                    btnAlterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
        buttonPanel.add(btnExcluir);

        panel.add(buttonPanel);

        add(panel);
        habilitarCampos(false);  // Desabilitar campos inicialmente
    }

    private void preencherCampos(Proprietario proprietario) {
        txtNome.setText(proprietario.getNome());
        txtEndereco.setText(proprietario.getEndereco());
        txtTelefone.setText(proprietario.getTelefone());
        txtEmail.setText(proprietario.getEmail());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtBuscaId.setText("");
        proprietarioAtual = null;
    }

    private void habilitarCampos(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtEndereco.setEnabled(habilitar);
        txtTelefone.setEnabled(habilitar);
        txtEmail.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }
}
