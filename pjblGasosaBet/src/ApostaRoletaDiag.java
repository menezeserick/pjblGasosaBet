import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ApostaRoletaDialog extends JDialog {
    private JTextField valorApostaField;
    private JComboBox<String> corApostaCombo;
    private Usuario usuarioAtual;
    private Runnable atualizarSaldoCallback;

    public ApostaRoletaDialog(JFrame parent, Usuario usuario, Runnable atualizarSaldoCallback) {
        super(parent, "Aposta em Roleta", true);
        this.usuarioAtual = usuario;
        this.atualizarSaldoCallback = atualizarSaldoCallback;
        setLayout(new GridLayout(3, 2));
        setSize(300, 150);

        JLabel valorApostaLabel = new JLabel("Valor da Aposta:");
        valorApostaField = new JTextField();

        JLabel corApostaLabel = new JLabel("Cor da Aposta:");
        String[] cores = {"Preto", "Vermelho", "Branco"};
        corApostaCombo = new JComboBox<>(cores);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ConfirmarButtonListener());

        add(valorApostaLabel);
        add(valorApostaField);
        add(corApostaLabel);
        add(corApostaCombo);
        add(new JLabel());
        add(confirmarButton);

        setVisible(true);
    }

    private class ConfirmarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valorAposta = Double.parseDouble(valorApostaField.getText());
                String corAposta = (String) corApostaCombo.getSelectedItem();

                if (usuarioAtual.getSaldoAtual() < valorAposta) {
                    JOptionPane.showMessageDialog(ApostaRoletaDialog.this, "Saldo insuficiente para realizar a aposta.");
                    return;
                }

                usuarioAtual.setSaldoAtual(usuarioAtual.getSaldoAtual() - valorAposta);
                ApostaRoleta minhaAposta = new ApostaRoleta(valorAposta, corAposta);
                String resultado = minhaAposta.resultadoFinal();

                double ganhoTotal = minhaAposta.getGanhoTotal();
                if (ganhoTotal > 0) {
                    usuarioAtual.setSaldoAtual(usuarioAtual.getSaldoAtual() + ganhoTotal);
                }

                JOptionPane.showMessageDialog(ApostaRoletaDialog.this, resultado + "\nSeu saldo atual: R$ " + usuarioAtual.getSaldoAtual());
                atualizarSaldoCallback.run();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ApostaRoletaDialog.this, "Valor da aposta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(ApostaRoletaDialog.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
