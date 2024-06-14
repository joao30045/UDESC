package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dados.Usuario;
import negocio.Sistema;

public class UsuarioPanel extends JPanel {
    private JLabel nomeUsuarioLabel = new JLabel("Nome de usuário:");
    private JTextField nomeUsuarioField = new JTextField(20);
    private JButton cadastrarUsuarioButton = new JButton("Cadastrar");
    private JLabel senhaUsuarioLabel = new JLabel("Senha:");
    private JPasswordField senhaUsuarioField = new JPasswordField(20);
    private JLabel dataNascimentoLabel = new JLabel("Data de nascimento:");
    private JTextField dataNascimentoField = new JTextField(20);

    public UsuarioPanel(Sistema sistema) {
        setLayout(null);

        nomeUsuarioLabel.setBounds(20, 20, 120, 15);
        add(nomeUsuarioLabel);

        nomeUsuarioField.setBounds(160, 18, 200, 20);
        add(nomeUsuarioField);

        dataNascimentoLabel.setBounds(20, 50, 150, 15);
        add(dataNascimentoLabel);

        dataNascimentoField.setBounds(160, 48, 200, 20);
        add(dataNascimentoField);

        senhaUsuarioLabel.setBounds(20, 80, 120, 15);
        add(senhaUsuarioLabel);

        senhaUsuarioField.setBounds(160, 78, 200, 20);
        add(senhaUsuarioField);

        cadastrarUsuarioButton.setBounds(160, 108, 150, 25);
        add(cadastrarUsuarioButton);

        cadastrarUsuarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Usuario usuario = new Usuario();
                usuario.setNome(nomeUsuarioField.getText());
                usuario.setDataNascimento(dataNascimentoField.getText());
                char[] senhaChars = senhaUsuarioField.getPassword();
                String senha = new String(senhaChars);
                usuario.setSenha(senha);       
                nomeUsuarioField.setText("");
                dataNascimentoField.setText("");
                senhaUsuarioField.setText("");
                try{
                    sistema.inserirUsuario(usuario);
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });   
    }
}

