package apresentacao;

import java.util.List;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dados.Conteudo;
import dados.Episodio;
import dados.Serie;
import negocio.Sistema;
import persistencia.EpisodioDAO;

public class CatalogoEpisodiosPanel extends JPanel {
    private JTextArea mostrarEpisodiosTextArea = new JTextArea();
    private JScrollPane mostrarEpisodiosScrollPane = new JScrollPane(mostrarEpisodiosTextArea);
    private JButton mostrarEpisodioButton = new JButton("Mostrar");
    private JComboBox<String> episodioComboBox = new JComboBox<String>();


    public CatalogoEpisodiosPanel(Sistema sistema) throws SQLException {
        setLayout(null);

        
        episodioComboBox.setBounds(20, 20, 200, 20);
        add(episodioComboBox);

        mostrarEpisodiosTextArea.setBounds(20, 50, 400, 200);
        mostrarEpisodiosTextArea.setEditable(false);

        mostrarEpisodiosScrollPane.setBounds(20, 50, 400, 200);
        add(mostrarEpisodiosScrollPane);

        mostrarEpisodioButton.setBounds(240, 17, 100, 25);
        add(mostrarEpisodioButton);

        List<Conteudo> s = sistema.selectAllSeries();
        for (Conteudo serie : s) {
            episodioComboBox.addItem(serie.getTitulo());
        }
        mostrarEpisodioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedSerieIndex = episodioComboBox.getSelectedIndex();
                    Serie selectedSerie = (Serie) s.get(selectedSerieIndex);
                    
                    EpisodioDAO episodioDAO = EpisodioDAO.getInstance();
                    List<Episodio> episodios = episodioDAO.select(selectedSerie.getId());
                    
                    StringBuilder episodiosText = new StringBuilder();
                    for (Episodio episodio : episodios) {
                        episodiosText.append("Título: ").append(episodio.getTitulo()).append("\n");
                        episodiosText.append("Número do episódio: ").append(episodio.getNumeroEpisodio()).append("\n");
                        episodiosText.append("Número da temporada: ").append(episodio.getNumeroTemporada()).append("\n");
                        episodiosText.append("Duração: ").append(episodio.getDuracao()).append(" minutos").append("\n");
                        episodiosText.append("Descrição: ").append(episodio.getDescricao()).append("\n");
                        episodiosText.append("\n");

                    }
                    mostrarEpisodiosTextArea.setText(episodiosText.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
