package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import negocio.*;

public class Main extends JFrame {
    private static Sistema sistema = new Sistema();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private UsuarioPanel usuarioPanel;
    private FilmesPanel filmesPanel;
    private SeriesPanel seriesPanel;
    private EpisodioPanel episodioPanel;
    private MostrarPanel mostrarPanel;
    private CatalogoEpisodiosPanel catalogoEpisodiosPanel;
    private RemoverPanel removerPanel;
    private AtorPanel atorPanel;
    private MostrarElencoPanel mostrarElencoPanel;
    private RemoverAtorPanel removerAtorPanel;

    public static void main(String[] args) throws SQLException {
        Main frame = new Main();
        frame.setVisible(true);
    }

    public Main() throws SQLException {
        setTitle("Pooflix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 1000, 350);
        setContentPane(tabbedPane);

        usuarioPanel = new UsuarioPanel(sistema);
        filmesPanel = new FilmesPanel(sistema);
        seriesPanel = new SeriesPanel(sistema);
        episodioPanel = new EpisodioPanel(sistema);
        mostrarPanel = new MostrarPanel(sistema);
        catalogoEpisodiosPanel = new CatalogoEpisodiosPanel(sistema);
        removerPanel = new RemoverPanel(sistema);
        atorPanel = new AtorPanel(sistema);
        mostrarElencoPanel = new MostrarElencoPanel(sistema);
        removerAtorPanel = new RemoverAtorPanel(sistema);

        tabbedPane.addTab("Login", usuarioPanel);
        tabbedPane.addTab("Filmes", filmesPanel);
        tabbedPane.addTab("Séries", seriesPanel);
        tabbedPane.addTab("Episódios", episodioPanel);
        tabbedPane.addTab("Catálogo", mostrarPanel);
        tabbedPane.addTab("Lista Episódios", catalogoEpisodiosPanel);
        tabbedPane.addTab("Elenco", atorPanel);
        tabbedPane.addTab("Lista Elenco", mostrarElencoPanel);
        tabbedPane.addTab("Remover Ator", removerAtorPanel);
        tabbedPane.addTab("Remover conteúdo", removerPanel);

    }
}
