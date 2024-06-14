package apresentacao;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import dados.Conteudo;
import dados.Filme;
import dados.Serie;
import negocio.Sistema;

public class RemoverPanel extends JPanel {
    private JLabel removerLabel = new JLabel("Digite o título a ser removido:");
    private JTextField removerField = new JTextField();
    private JButton removerButton = new JButton("Remover");

    public RemoverPanel(Sistema sistema) {
        setLayout(null);

        removerLabel.setBounds(20, 20, 300, 15);
        add(removerLabel);

        removerField.setBounds(20, 50, 300, 20);
        add(removerField);

        removerButton.setBounds(20, 80, 150, 25);
        add(removerButton);

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = removerField.getText();
                try {
                    for (Conteudo c : sistema.getAllConteudo()) {
                        if (c.getTitulo().equals(titulo)) {
                            if (c instanceof Filme) {
                                sistema.deletarFilme((Filme) c);
                            } else {
                                sistema.deletarSerie((Serie) c);
                            }
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