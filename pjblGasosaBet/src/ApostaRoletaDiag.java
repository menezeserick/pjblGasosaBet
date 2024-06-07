import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ApostaRoletaDialog extends JDialog {
    private JTextField valorApostaField;
    private JTextField corApostaField;

    public ApostaRoletaDialog(JFrame parent) {
        super(parent, "Aposta em Roleta", true);
        setLayout(new GridLayout(3, 2));
        setSize(300, 150);

        JLabel valorApostaLabel = new JLabel("Valor da Aposta:");
        valorApostaField = new JTextField();

        JLabel corApostaLabel = new JLabel("Cor da Aposta:");
        corApostaField = new JTextField();

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ConfirmarButtonListener());

        add(valorApostaLabel);
        add(valorApostaField);
        add(corApostaLabel);
        add(corApostaField);
        add(confirmarButton);

        setVisible(true);
    }

    private class ConfirmarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double valorAposta = Double.parseDouble(valorApostaField.getText());
            String corAposta = corApostaField.getText();

            ApostaRoleta minhaAposta = new ApostaRoleta();
            minhaAposta.definirValorAposta(valorAposta);
            minhaAposta.resultadoFinal(corAposta);
            dispose();
        }
    }
}