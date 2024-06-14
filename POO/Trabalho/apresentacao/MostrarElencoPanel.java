package apresentacao;

import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dados.Ator;
import negocio.Sistema;

public class MostrarElencoPanel extends JPanel {
    private JTextArea mostrarTextArea = new JTextArea();
    private JScrollPane mostrarScrollPane = new JScrollPane(mostrarTextArea);
    private JButton mostrarButton = new JButton("Mostrar");

    public MostrarElencoPanel(Sistema sistema) {
        setLayout(null);

        mostrarTextArea.setBounds(20, 45, 400, 200);
        mostrarTextArea.setEditable(false);

        mostrarScrollPane.setBounds(20, 45, 400, 200);
        add(mostrarScrollPane);

        mostrarButton.setBounds(125, 15, 150, 25);
        add(mostrarButton);

        mostrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Ator> atores = sistema.selectAll();
                    StringBuilder conteudo = new StringBuilder();
                    for (Ator ator : atores) {
                        conteudo.append("Ator:\n");
                        conteudo.append("Nome: ").append(ator.getNome()).append("\n");
                        conteudo.append("Data de Nascimento: ").append(ator.getDataNascimento()).append("\n");
                        conteudo.append("Sexo: ").append(ator.getSexo()).append("\n");
                        conteudo.append("\n");
                    }
                    mostrarTextArea.setText(conteudo.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

