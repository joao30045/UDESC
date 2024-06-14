package apresentacao;

import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.Conteudo;
import dados.Episodio;
import dados.Serie;
import negocio.Sistema;
import persistencia.EpisodioDAO;

public class EpisodioPanel extends JPanel {
    private JLabel tituloEpisodioLabel = new JLabel("Título:");
    private JTextField tituloEpisodioField = new JTextField(20);
    private JLabel numeroTemporadaEpisodioLabel = new JLabel("Temporada:");
    private JTextField numeroTemporadaEpisodioField = new JTextField(20);
    private JLabel duracaoEpisodioLabel = new JLabel("Duração:");
    private JTextField duracaoEpisodioField = new JTextField(20);
    private JLabel descricaoEpisodioLabel = new JLabel("Descrição:");
    private JTextField descricaoEpisodioField = new JTextField(20);   
    private JLabel numeroEpisodioLabel = new JLabel("Episódio:");
    private JTextField numeroEpisodioField = new JTextField(20);
    private JButton cadastrarEpisodioButton = new JButton("Cadastrar");
    private JComboBox<String> serieComboBox = new JComboBox<String>();


    public EpisodioPanel(Sistema sistema) throws SQLException {
        setLayout(null);

        tituloEpisodioLabel.setBounds(20, 20, 150, 15);
        add(tituloEpisodioLabel);

        tituloEpisodioField.setBounds(130, 18, 200, 20);
        add(tituloEpisodioField);

        numeroEpisodioLabel.setBounds(20, 50, 150, 15);
        add(numeroEpisodioLabel);
                   
        numeroEpisodioField.setBounds(130, 48, 200, 20);
        add(numeroEpisodioField);

        numeroTemporadaEpisodioLabel.setBounds(20, 80, 150, 15);
        add(numeroTemporadaEpisodioLabel);
                   
        numeroTemporadaEpisodioField.setBounds(130, 78, 200, 20);
        add(numeroTemporadaEpisodioField);

        duracaoEpisodioLabel.setBounds(20, 110, 150, 15);
        add(duracaoEpisodioLabel);
                   
        duracaoEpisodioField.setBounds(130, 108, 200, 20);
        add(duracaoEpisodioField);

        descricaoEpisodioLabel.setBounds(20, 140, 150, 15);
        add(descricaoEpisodioLabel);
                   
        descricaoEpisodioField.setBounds(130, 138, 200, 20);
        add(descricaoEpisodioField);

        cadastrarEpisodioButton.setBounds(360, 45, 150, 25);
        add(cadastrarEpisodioButton);

        serieComboBox.setBounds(360, 15, 200, 20);
        add(serieComboBox);

        List<Conteudo> series = sistema.selectAllSeries();
        for (Conteudo serie : series) {
            serieComboBox.addItem(serie.getTitulo());
        }
        cadastrarEpisodioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Episodio episodio = new Episodio();
                int selectedSerieIndex = serieComboBox.getSelectedIndex();
                Serie selectedSerie = (Serie) series.get(selectedSerieIndex);
                
                episodio.setId_serie(selectedSerie.getId());
                episodio.setTitulo(tituloEpisodioField.getText());
                episodio.setNumeroEpisodio(Integer.parseInt(numeroEpisodioField.getText()));
                episodio.setNumeroTemporada(Integer.parseInt(numeroTemporadaEpisodioField.getText()));
                episodio.setDuracao(Integer.parseInt(duracaoEpisodioField.getText()));
                episodio.setDescricao(descricaoEpisodioField.getText());
                tituloEpisodioField.setText("");
                numeroEpisodioField.setText("");
                numeroTemporadaEpisodioField.setText("");
                duracaoEpisodioField.setText("");
                descricaoEpisodioField.setText("");
                
                try {
                    EpisodioDAO episodioDAO = EpisodioDAO.getInstance();
                    episodioDAO.insert(episodio);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}