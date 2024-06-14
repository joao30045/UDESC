package apresentacao;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.Filme;
import negocio.Sistema;

public class FilmesPanel extends JPanel {
    private JLabel tituloFilmeLabel = new JLabel("Título:");
    private JTextField tituloFilmeField = new JTextField(20);
    private JLabel generoFilmeLabel = new JLabel("Gênero:");
    private JTextField generoFilmeField = new JTextField(20);
    private JLabel descricaoFilmeLabel = new JLabel("Descrição:");
    private JTextField descricaoFilmeField = new JTextField(20);
    private JLabel anoFilmeLabel = new JLabel("Ano:");
    private JTextField anoFilmeField = new JTextField(20);
    private JLabel duracaoFilmeLabel = new JLabel("Duração:");
    private JTextField duracaoFilmeField = new JTextField(20);
    private JButton cadastrarFilmeButton = new JButton("Cadastrar");

    public FilmesPanel(Sistema sistema) {
        setLayout(null);

        tituloFilmeLabel.setBounds(20, 20, 100, 15);
        add(tituloFilmeLabel);

        tituloFilmeField.setBounds(130, 18, 200, 20);
        add(tituloFilmeField);

        generoFilmeLabel.setBounds(20, 110, 150, 15);
        add(generoFilmeLabel);

        generoFilmeField.setBounds(130, 108, 200, 20);
        add(generoFilmeField);

        descricaoFilmeLabel.setBounds(20, 140, 100, 15);
        add(descricaoFilmeLabel);

        descricaoFilmeField.setBounds(130, 138, 200, 20);
        add(descricaoFilmeField);

        anoFilmeLabel.setBounds(20, 50, 100, 15);
        add(anoFilmeLabel);

        anoFilmeField.setBounds(130, 48, 200, 20);
        add(anoFilmeField);

        duracaoFilmeLabel.setBounds(20, 80, 100, 15);
        add(duracaoFilmeLabel);

        duracaoFilmeField.setBounds(130, 78, 200, 20);
        add(duracaoFilmeField);

        cadastrarFilmeButton.setBounds(360, 15, 150, 25);
        add(cadastrarFilmeButton);

        cadastrarFilmeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Filme filme = new Filme();
                filme.setTitulo(tituloFilmeField.getText());
                filme.setGenero(generoFilmeField.getText());
                filme.setDescricao(descricaoFilmeField.getText());
                filme.setAno(Integer.parseInt(anoFilmeField.getText()));
                filme.setDuracao(Integer.parseInt(duracaoFilmeField.getText()));
                tituloFilmeField.setText("");
                generoFilmeField.setText("");
                descricaoFilmeField.setText("");
                anoFilmeField.setText("");
                duracaoFilmeField.setText("");
                try{
                    sistema.inserirFilme(filme);
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}