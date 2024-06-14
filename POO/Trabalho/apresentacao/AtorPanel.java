package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Sistema;
import dados.Ator;

public class AtorPanel extends JPanel {
    private JLabel nomeAtorLabel = new JLabel("Nome:");
    private JTextField nomeAtorField = new JTextField(20);
    private JLabel sexoAtorLabel = new JLabel("Sexo:");
    private JTextField sexoAtorField = new JTextField(20);
    private JLabel dataNascimentoLabel = new JLabel("Data de nascimento:");
    private JTextField dataNascimentoField = new JTextField(20);
    private JButton cadastrarAtorButton = new JButton("Cadastrar");

    public AtorPanel(Sistema sistema) {
        setLayout(null);

        nomeAtorLabel.setBounds(20, 20, 100, 15);
        add(nomeAtorLabel);

        nomeAtorField.setBounds(160, 18, 200, 20);
        add(nomeAtorField);

        dataNascimentoLabel.setBounds(20, 50, 150, 15);
        add(dataNascimentoLabel);

        dataNascimentoField.setBounds(160, 48, 200, 20);
        add(dataNascimentoField);

        sexoAtorLabel.setBounds(20, 80, 150, 15);
        add(sexoAtorLabel);

        sexoAtorField.setBounds(160, 78, 200, 20);
        add(sexoAtorField);

        cadastrarAtorButton.setBounds(380, 15, 150, 25);
        add(cadastrarAtorButton);

        cadastrarAtorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Ator ator = new Ator();
                ator.setNome(nomeAtorField.getText());
                ator.setDataNascimento(dataNascimentoField.getText());
                ator.setSexo(sexoAtorField.getText());
                nomeAtorField.setText("");
                dataNascimentoField.setText("");
                sexoAtorField.setText("");
                try {
                    sistema.inserirAtor(ator);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
            }
        });
    }
}
