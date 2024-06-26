package view;

import controller.AnimalController;
import model.Animal;
import model.Proprietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalView extends JFrame {
    private JTextField txtNome, txtEspecie, txtRaca, txtIdade, txtSexo, txtPeso, txtProprietarioId, txtBuscaId;
    private JButton btnNovo, btnBuscar, btnSalvar, btnAlterar, btnExcluir;
    private Animal animalAtual;

    public AnimalView(AnimalController animalController) {
        setTitle("Cadastro de Animal");
        setSize(400, 500);
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
                animalAtual = animalController.buscarAnimalPorId(id);
                if (animalAtual != null) {
                    preencherCampos(animalAtual);
                    habilitarCampos(true);
                    btnAlterar.setEnabled(true);
                    btnExcluir.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Animal não encontrado.");
                    limparCampos();
                    habilitarCampos(false);
                    btnAlterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
        panel.add(btnBuscar);

        // Seção de dados do animal
        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Espécie:"));
        txtEspecie = new JTextField();
        panel.add(txtEspecie);

        panel.add(new JLabel("Raça:"));
        txtRaca = new JTextField();
        panel.add(txtRaca);

        panel.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        panel.add(txtIdade);

        panel.add(new JLabel("Sexo:"));
        txtSexo = new JTextField();
        panel.add(txtSexo);

        panel.add(new JLabel("Peso:"));
        txtPeso = new JTextField();
        panel.add(txtPeso);

        panel.add(new JLabel("ID do Proprietário:"));
        txtProprietarioId = new JTextField();
        panel.add(txtProprietarioId);

        // Painel de botões
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
                animalAtual = null;
            }
        });
        buttonPanel.add(btnNovo);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (animalAtual == null) {
                    animalAtual = new Animal();
                }
                animalAtual.setNome(txtNome.getText());
                animalAtual.setEspecie(txtEspecie.getText());
                animalAtual.setRaca(txtRaca.getText());
                animalAtual.setIdade(Integer.parseInt(txtIdade.getText()));
                animalAtual.setSexo(txtSexo.getText().charAt(0));
                animalAtual.setPeso(Double.parseDouble(txtPeso.getText()));

                Proprietario proprietario = new Proprietario();
                proprietario.setId(Integer.parseInt(txtProprietarioId.getText()));
                animalAtual.setProprietario(proprietario);

                if (animalAtual.getId() == 0) {
                    animalController.adicionarAnimal(animalAtual);
                    JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso!");
                } else {
                    animalController.atualizarAnimal(animalAtual);
                    JOptionPane.showMessageDialog(null, "Animal atualizado com sucesso!");
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
                if (animalAtual != null) {
                    animalAtual.setNome(txtNome.getText());
                    animalAtual.setEspecie(txtEspecie.getText());
                    animalAtual.setRaca(txtRaca.getText());
                    animalAtual.setIdade(Integer.parseInt(txtIdade.getText()));
                    animalAtual.setSexo(txtSexo.getText().charAt(0));
                    animalAtual.setPeso(Double.parseDouble(txtPeso.getText()));

                    Proprietario proprietario = new Proprietario();
                    proprietario.setId(Integer.parseInt(txtProprietarioId.getText()));
                    animalAtual.setProprietario(proprietario);

                    animalController.atualizarAnimal(animalAtual);
                    JOptionPane.showMessageDialog(null, "Animal atualizado com sucesso!");
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
                if (animalAtual != null) {
                    animalController.deletarAnimal(animalAtual.getId());
                    JOptionPane.showMessageDialog(null, "Animal deletado com sucesso!");
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

    private void preencherCampos(Animal animal) {
        txtNome.setText(animal.getNome());
        txtEspecie.setText(animal.getEspecie());
        txtRaca.setText(animal.getRaca());
        txtIdade.setText(String.valueOf(animal.getIdade()));
        txtSexo.setText(String.valueOf(animal.getSexo()));
        txtPeso.setText(String.valueOf(animal.getPeso()));
        txtProprietarioId.setText(String.valueOf(animal.getProprietario().getId()));
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtIdade.setText("");
        txtSexo.setText("");
        txtPeso.setText("");
        txtProprietarioId.setText("");
        txtBuscaId.setText("");
        animalAtual = null;
    }

    private void habilitarCampos(boolean habilitar) {
        txtNome.setEnabled(habilitar);
        txtEspecie.setEnabled(habilitar);
        txtRaca.setEnabled(habilitar);
        txtIdade.setEnabled(habilitar);
        txtSexo.setEnabled(habilitar);
        txtPeso.setEnabled(habilitar);
        txtProprietarioId.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }
}
