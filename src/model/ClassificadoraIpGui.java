package model;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;

	public class ClassificadoraIpGui extends JFrame {

	    private JTextField campoIp;
	    private JTextField campoCidr;
	    private JTextArea resultadoArea;

	    public ClassificadoraIpGui() {
	        setTitle("Classificador de IP");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Centraliza a janela
	        initComponents();
	        setVisible(true);
	    }

	    private void initComponents() {
	        campoIp = new JTextField(15);
	        campoCidr = new JTextField(3);
	        resultadoArea = new JTextArea(6, 30);
	        resultadoArea.setEditable(false);

	        JButton btnClassificar = new JButton("Classificar");

	        btnClassificar.addActionListener((ActionEvent e) -> classificar());

	        JPanel painel = new JPanel();
	        painel.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();

	        c.insets = new Insets(5, 5, 5, 5);
	        c.gridx = 0; c.gridy = 0;
	        painel.add(new JLabel("Endereço IP:"), c);

	        c.gridx = 1;
	        painel.add(campoIp, c);

	        c.gridx = 0; c.gridy = 1;
	        painel.add(new JLabel("CIDR (ex: 24):"), c);

	        c.gridx = 1;
	        painel.add(campoCidr, c);

	        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
	        painel.add(btnClassificar, c);

	        c.gridy = 3;
	        painel.add(new JScrollPane(resultadoArea), c);

	        add(painel);
	    }

	    private void classificar() {
	        String ip = campoIp.getText();
	        String cidrText = campoCidr.getText();

	        try {
	            int cidr = Integer.parseInt(cidrText);
	            ClassificadoraIp classificadora = new ClassificadoraIp(ip, cidr);

	            StringBuilder sb = new StringBuilder();
	            sb.append("Classe do IP: ").append(classificadora.getIpClass()).append("\n");
	            sb.append("Máscara (binário): ").append(classificadora.getMascaraBinaria()).append("\n");
	            sb.append("Máscara (decimal): ").append(classificadora.getMascaraDecimal()).append("\n");
	            sb.append("IPs disponíveis: ").append(classificadora.getIpsDisponiveis()).append("\n");

	            resultadoArea.setText(sb.toString());
	        } catch (Exception e) {
	            resultadoArea.setText("Erro: Verifique os valores inseridos.");
	        }
	    }
	}