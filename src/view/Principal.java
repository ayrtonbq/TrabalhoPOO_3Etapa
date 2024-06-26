package view;

import controller.AnimalController;
import controller.ProprietarioController;
import controller.ServicoController;
import controller.RegistroServicoController;

import javax.swing.*;
import java.awt.*;


public class Principal {
    private AnimalController animalController;
    private ProprietarioController proprietarioController;
    private ServicoController servicoController;
    private RegistroServicoController registroServicoController;

    public Principal() {
        this.animalController = new AnimalController();
        this.proprietarioController = new ProprietarioController();
        this.servicoController = new ServicoController();
        this.registroServicoController = new RegistroServicoController();
    }

    public void iniciar() {
        JFrame frame = new JFrame("Sistema de Gerenciamento de Pet Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Aumentei o tamanho da janela principal
        frame.setLocationRelativeTo(null);

        // Painel principal com gradiente de fundo
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = Color.decode("#88c5e7");
                Color color2 = Color.decode("#ffffff");
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        frame.add(panel);

        // Logo no centro da janela
        ImageIcon logoIcon = new ImageIcon("Pet.png"); 
        JLabel lblLogo = new JLabel(logoIcon);
        panel.add(lblLogo, BorderLayout.CENTER);        

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.decode("#f0f0f0")); // Cor de fundo do menu
        panel.add(menuBar, BorderLayout.NORTH); // Adicionando à parte superior

        // Menu Cadastro
        JMenu menuCadastro = new JMenu("Cadastro");
        menuCadastro.setForeground(Color.decode("#333333")); // Cor do texto do menu
        menuCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fonte do menu
        menuBar.add(menuCadastro);

        JMenuItem menuItemAnimal = new JMenuItem("Animais", new ImageIcon("icons/animal.png"));
        JMenuItem menuItemProprietario = new JMenuItem("Proprietários", new ImageIcon("icons/owner.png"));
        JMenuItem menuItemServico = new JMenuItem("Serviços", new ImageIcon("icons/service.png"));

        menuItemAnimal.addActionListener(e -> new AnimalView(animalController).setVisible(true));
        menuItemProprietario.addActionListener(e -> new ProprietarioView(proprietarioController).setVisible(true));
        menuItemServico.addActionListener(e -> new ServicoView(servicoController).setVisible(true));

        menuCadastro.add(menuItemAnimal);
        menuCadastro.add(menuItemProprietario);
        menuCadastro.add(menuItemServico);

        // Menu Serviços
        JMenu menuServico = new JMenu("Serviços");
        menuServico.setForeground(Color.decode("#333333"));
        menuServico.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(menuServico);

        JMenuItem menuItemLancamentoServico = new JMenuItem("Lançamento", new ImageIcon("icons/service.png"));
        JMenuItem menuItemConsultaHistorico = new JMenuItem("Histórico", new ImageIcon("icons/history.png"));

        menuItemLancamentoServico.addActionListener(e -> new RegistroServicoView(registroServicoController, animalController, servicoController).setVisible(true));
        menuItemConsultaHistorico.addActionListener(e -> new ConsultaHistoricoView(registroServicoController).setVisible(true));

        menuServico.add(menuItemLancamentoServico);
        menuServico.add(menuItemConsultaHistorico);

        // Menu Relatórios
        JMenu menuRelatorio = new JMenu("Relatórios");
        menuRelatorio.setForeground(Color.decode("#333333"));
        menuRelatorio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(menuRelatorio);

        JMenuItem menuItemRelatorio = new JMenuItem("Gerar Relatório", new ImageIcon("icons/report.png"));
        menuItemRelatorio.addActionListener(e -> new RelatorioView(registroServicoController).setVisible(true));

        menuRelatorio.add(menuItemRelatorio);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Principal().iniciar();
        });
    }
}
