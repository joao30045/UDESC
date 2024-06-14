package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.Ator;
import negocio.Sistema;

public class RemoverAtorPanel extends JPanel {
    private JLabel removerLabel = new JLabel("Digite o nome do ator a ser removido:");
    private JTextField removerField = new JTextField();
    private JButton removerButton = new JButton("Remover");

    public RemoverAtorPanel(Sistema sistema) {
        setLayout(null);

        removerLabel.setBounds(20, 20, 300, 15);
        add(removerLabel);

        removerField.setBounds(20, 50, 300, 20);
        add(removerField);

        removerButton.setBounds(20, 80, 150, 25);
        add(removerButton);

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = removerField.getText();
                try {
                    for (Ator a : sistema.selectAll()) {
                        if (a.getNome().equals(nome)) {
                            sistema.deletarAtor(a);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                removerField.setText("");
            }
        });
    }
}
