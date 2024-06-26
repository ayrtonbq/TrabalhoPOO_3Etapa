package view;

import controller.ServicoController;
import model.Servico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicoView extends JFrame {
    private JTextField txtNome, txtDescricao, txtPreco, txtBuscaId;
    private JButton btnNovo, btnSalvar, btnAlterar, btnExcluir, btnBuscar;
    private Servico servicoAtual;

    public ServicoView(ServicoController servicoController) {
        setTitle("Cadastro de Serviço");
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
                servicoAtual = servicoController.buscarServicoPorId(id);
                if (servicoAtual != null) {
                    preencherCampos(servicoAtual);
                    habilitarCampos(true);
                    btnAlterar.setEnabled(true);
                    btnExcluir.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Serviço não encontrado.");
                    limparCampos();
                    habilitarCampos(false);
                    btnAlterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
        panel.add(btnBuscar);

        // Seção de dados do serviço
        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        panel.add(txtDescricao);

        panel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        panel.add(txtPreco);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botão Novo
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                habilitarCampos(true);
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);
                servicoAtual = null;
            }
        });
        buttonPanel.add(btnNovo);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (servicoAtual == null) {
                    servicoAtual = new Servico();
                }
                servicoAtual.setNome(txtNome.getText());
                servicoAtual.setDescricao(txtDescricao.getText());
                servicoAtual.setPreco(Double.parseDouble(txtPreco.getText()));

                if (servicoAtual.getId() == 0) {
                    servicoController.adicionarServico(servicoAtual);
                    JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
                } else {
                    servicoController.atualizarServico(servicoAtual);
                    JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
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
                if (servicoAtual != null) {
                    servicoAtual.setNome(txtNome.getText());
                    servicoAtual.setDescricao(txtDescricao.getText());
                    servicoAtual.setPreco(Double.parseDouble(txtPreco.getText()));

                    servicoController.atualizarServico(servicoAtual);
                    JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
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
                if (servicoAtual != null) {
                    servicoController.deletarServico(servicoAtual.getId());
                    JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
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

    private void preencherCampos(Servico servico) {
        txtNome.setText(servico.getNome());
        txtDescricao.setText(servico.getDescricao());
        txtPreco.setText(String.valueOf(servico.getPreco()));
    }

    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtBuscaId.setText("");
        servicoAtual = null;
    }

    private void habilitarCampos(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtDescricao.setEnabled(habilitar);
        txtPreco.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }
}
