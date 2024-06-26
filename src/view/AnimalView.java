package view;

import controller.AnimalController;
import model.Animal;
import model.Proprietario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalView extends JFrame {
    private JTextField txtNome, txtEspecie, txtRaca, txtIdade, txtSexo, txtPeso, txtProprietarioId;

    public AnimalView(AnimalController animalController) {
        setTitle("Cadastro de Animal");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Animal animal = new Animal();
                animal.setNome(txtNome.getText());
                animal.setEspecie(txtEspecie.getText());
                animal.setRaca(txtRaca.getText());
                animal.setIdade(Integer.parseInt(txtIdade.getText()));
                animal.setSexo(txtSexo.getText().charAt(0));
                animal.setPeso(Double.parseDouble(txtPeso.getText()));
                
                Proprietario proprietario = new Proprietario();
                proprietario.setId(Integer.parseInt(txtProprietarioId.getText()));
                animal.setProprietario(proprietario);
                
                animalController.adicionarAnimal(animal);
                JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso!");
                dispose();
            }
        });
        panel.add(btnSalvar);

        add(panel);
    }
}
