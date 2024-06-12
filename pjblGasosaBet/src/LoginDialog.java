import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField emailField;
    private JPasswordField senhaField;
    private Usuario usuario;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        setLayout(new GridLayout(3, 2));
        setSize(400, 150);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        add(emailLabel);
        add(emailField);
        add(senhaLabel);
        add(senhaField);
        add(new JLabel());
        add(loginButton);

        setLocationRelativeTo(parent);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(LoginDialog.this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuario = Usuario.realizarLogin(email, senha);
            if (usuario == null) {
                JOptionPane.showMessageDialog(LoginDialog.this, "Email ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(LoginDialog.this, "Login realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
