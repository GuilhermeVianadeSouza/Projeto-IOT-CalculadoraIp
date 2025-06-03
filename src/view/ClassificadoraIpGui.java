package view;

import model.ClassificadoraIp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassificadoraIpGui extends JFrame {

    private JTextField ipField;
    private JTextField cidrField;
    private JTextArea resultArea;

    public ClassificadoraIpGui() {
        setTitle("Classificador de IP");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarComponentes();
    }

    private void criarComponentes() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel ipLabel = new JLabel("Digite o IP (Ex: 192.168.0.1): ");
        ipField = new JTextField();

        JLabel cidrLabel = new JLabel("Digite o CIDR (Ex: 24): ");
        cidrField = new JTextField();

        JButton calcularButton = new JButton("Calcular");

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularInformacoes();
            }
        });

        panel.add(ipLabel);
        panel.add(ipField);
        panel.add(cidrLabel);
        panel.add(cidrField);
        panel.add(new JLabel());
        panel.add(calcularButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void calcularInformacoes() {
        String ip = ipField.getText();
        String cidrText = cidrField.getText();

        try {
            String[] octetos = ip.split("\\.");
            if (octetos.length != 4) {
                throw new IllegalArgumentException("IP inválido. Deve ter 4 octetos.");
            }

            int cidr = Integer.parseInt(cidrText);

            ClassificadoraIp classificadora = new ClassificadoraIp();
            classificadora.setPrimeiroOcteto(octetos[0]);
            classificadora.setSegundoOcteto(octetos[1]);
            classificadora.setTerceiroOcteto(octetos[2]);
            classificadora.setQuartoOcteto(octetos[3]);
            classificadora.setIp(ip);
            classificadora.setCidr(cidr);

            classificadora.mostrarDados();

            StringBuilder resultado = new StringBuilder();
            resultado.append("IP informado: ").append(ip).append("/").append(cidr).append("\n");
            resultado.append("Classe do IP: ").append(classificadora.getIpClasse()).append("\n");
            resultado.append("Máscara binária: ").append(classificadora.getMascaraBinaria()).append("\n");
            resultado.append("Máscara decimal: ").append(classificadora.getMascaraDecimal()).append("\n");
            resultado.append("IPs por sub-rede: ").append((long) classificadora.getSubClasse()).append("\n");
            resultado.append("Quantidade de sub-redes: ").append(classificadora.getSubRedes()).append("\n\n");

            resultArea.setText(resultado.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "CIDR deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClassificadoraIpGui().setVisible(true);
        });
    }
}
