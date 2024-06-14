package apresentacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import dados.Conteudo;
import dados.Filme;
import dados.Serie;

import javax.swing.JTextArea;

import negocio.Sistema;

public class MostrarPanel extends JPanel {
    private JTextArea mostrarTextArea = new JTextArea();
    private JScrollPane mostrarScrollPane = new JScrollPane(mostrarTextArea);
    private JButton mostrarButton = new JButton("Mostrar");
    private JButton ordenarButton = new JButton("Ordenar por gênero");

    public MostrarPanel(Sistema sistema) {
        setLayout(null);

        mostrarTextArea.setBounds(20, 45, 400, 220);
        mostrarTextArea.setEditable(false);

        mostrarScrollPane.setBounds(20, 45, 400, 220);
        add(mostrarScrollPane);

        mostrarButton.setBounds(25, 15, 150, 25);
        add(mostrarButton);

        ordenarButton.setBounds(225, 15, 150, 25);
        add(ordenarButton);

        mostrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Conteudo> conteudos = sistema.getAllConteudo();
                    StringBuilder conteudo = new StringBuilder();
                    for (Conteudo conteudoObj : conteudos) {
                        if (conteudoObj instanceof Filme) {
                            Filme filme = (Filme) conteudoObj;
                            conteudo.append("Filme:\n");
                            conteudo.append("Título: ").append(filme.getTitulo()).append("\n");
                            conteudo.append("Ano: ").append(filme.getAno()).append("\n");
                            conteudo.append("Duração: ").append(filme.getDuracao()).append("\n");
                            conteudo.append("Gênero: ").append(filme.getGenero()).append("\n");
                            conteudo.append("Descrição: ").append(filme.getDescricao()).append("\n");
                            conteudo.append("\n");
                        } else if (conteudoObj instanceof Serie) {
                            Serie serie = (Serie) conteudoObj;
                            conteudo.append("Série:\n");
                            conteudo.append("Título: ").append(serie.getTitulo()).append("\n");
                            conteudo.append("Ano: ").append(serie.getAno()).append("\n");
                            conteudo.append("Temporadas: ").append(serie.getTemporada()).append("\n");
                            conteudo.append("Gênero: ").append(serie.getGenero()).append("\n");
                            conteudo.append("Descrição: ").append(serie.getDescricao()).append("\n");
                            conteudo.append("\n");
                        }
                    }
                    mostrarTextArea.setText(conteudo.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Conteudo> conteudos = sistema.getAllConteudo();
                    List<Conteudo> conteudosOrdenados = new ArrayList<>(conteudos);
                    
                    Collections.sort(conteudosOrdenados, new Comparator<Conteudo>() {
                        public int compare(Conteudo c1, Conteudo c2) {
                            String genero1 = "";
                            String genero2 = "";
                            
                            if (c1 instanceof Filme) {
                                genero1 = ((Filme) c1).getGenero();
                            } else if (c1 instanceof Serie) {
                                genero1 = ((Serie) c1).getGenero();
                            }
                            
                            if (c2 instanceof Filme) {
                                genero2 = ((Filme) c2).getGenero();
                            } else if (c2 instanceof Serie) {
                                genero2 = ((Serie) c2).getGenero();
                            }
                            
                            return genero1.compareToIgnoreCase(genero2);
                        }
                    });
                    
                    StringBuilder conteudo = new StringBuilder();
                    for (Conteudo conteudoObj : conteudosOrdenados) {
                        if (conteudoObj instanceof Filme) {
                            Filme filme = (Filme) conteudoObj;
                            conteudo.append("Filme:\n");
                            conteudo.append("Título: ").append(filme.getTitulo()).append("\n");
                            conteudo.append("Ano: ").append(filme.getAno()).append("\n");
                            conteudo.append("Duração: ").append(filme.getDuracao()).append("\n");
                            conteudo.append("Gênero: ").append(filme.getGenero()).append("\n");
                            conteudo.append("Descrição: ").append(filme.getDescricao()).append("\n");
                            conteudo.append("\n");
                        } else if (conteudoObj instanceof Serie) {
                            Serie serie = (Serie) conteudoObj;
                            conteudo.append("Série:\n");
                            conteudo.append("Título: ").append(serie.getTitulo()).append("\n");
                            conteudo.append("Ano: ").append(serie.getAno()).append("\n");
                            conteudo.append("Temporadas: ").append(serie.getTemporada()).append("\n");
                            conteudo.append("Gênero: ").append(serie.getGenero()).append("\n");
                            conteudo.append("Descrição: ").append(serie.getDescricao()).append("\n");
                            conteudo.append("\n");
                        }
                    }
                    mostrarTextArea.setText(conteudo.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        

    }
}