import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroDialog extends JDialog {
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private Usuario usuario;

    public RegistroDialog(JFrame parent) {
        super(parent, "Registro", true);
        setLayout(new GridLayout(5, 2));
        setSize(400, 200);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        JButton registroButton = new JButton("Registrar");
        registroButton.addActionListener(new RegistroButtonListener());

        add(nomeLabel);
        add(nomeField);
        add(emailLabel);
        add(emailField);
        add(cpfLabel);
        add(cpfField);
        add(senhaLabel);
        add(senhaField);
        add(new JLabel());
        add(registroButton);

        setLocationRelativeTo(parent);
    }

    private class RegistroButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String cpf = cpfField.getText();
            String senha = new String(senhaField.getPassword());

            if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(RegistroDialog.this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuario = Usuario.criarUsuario(nome, cpf, email, senha, 1000.0);
            if (usuario == null) {
                JOptionPane.showMessageDialog(RegistroDialog.this, "Email já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(RegistroDialog.this, "Usuário criado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
