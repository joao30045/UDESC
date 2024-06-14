package apresentacao;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.Serie;
import negocio.Sistema;

public class SeriesPanel extends JPanel {
    private JLabel tituloSerieLabel = new JLabel("Título:");
    private JTextField tituloSerieField = new JTextField(20);
    private JLabel generoSerieLabel = new JLabel("Gênero:");
    private JTextField generoSerieField = new JTextField(20);
    private JLabel descricaoSerieLabel = new JLabel("Descrição:");
    private JTextField descricaoSerieField = new JTextField(20);
    private JLabel anoSerieLabel = new JLabel("Ano:");
    private JTextField anoSerieField = new JTextField(20);
    private JLabel temporadaSerieLabel = new JLabel("Temporadas:");
    private JTextField temporadaSerieField = new JTextField(20);
    private JButton cadastrarSerieButton = new JButton("Cadastrar");

    public SeriesPanel(Sistema sistema) {
        setLayout(null);

        tituloSerieLabel.setBounds(20, 20, 100, 15);
        add(tituloSerieLabel);

        tituloSerieField.setBounds(130, 18, 200, 20);
        add(tituloSerieField);

        anoSerieLabel.setBounds(20, 50, 100, 15);
        add(anoSerieLabel);

        anoSerieField.setBounds(130, 48, 200, 20);
        add(anoSerieField);

        temporadaSerieLabel.setBounds(20, 80, 100, 15);
        add(temporadaSerieLabel);

        temporadaSerieField.setBounds(130, 78, 200, 20);
        add(temporadaSerieField);

        generoSerieLabel.setBounds(20, 110, 150, 15);
        add(generoSerieLabel);

        generoSerieField.setBounds(130, 108, 200, 20);
        add(generoSerieField);

        descricaoSerieLabel.setBounds(20, 140, 150, 15);
        add(descricaoSerieLabel);

        descricaoSerieField.setBounds(130, 138, 200, 20);
        add(descricaoSerieField);

        cadastrarSerieButton.setBounds(360, 15, 150, 25);
        add(cadastrarSerieButton);

        cadastrarSerieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Serie serie = new Serie();
                serie.setTitulo(tituloSerieField.getText());
                serie.setAno(Integer.parseInt(anoSerieField.getText()));
                serie.setTemporada(Integer.parseInt(temporadaSerieField.getText()));
                serie.setGenero(generoSerieField.getText());
                serie.setDescricao(descricaoSerieField.getText());
                tituloSerieField.setText("");
                anoSerieField.setText("");
                temporadaSerieField.setText("");
                generoSerieField.setText("");
                descricaoSerieField.setText("");
                try {
                    sistema.inserirSerie(serie);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
            }
        });

    }
}