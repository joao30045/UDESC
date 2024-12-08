package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.*;
import persistencia.*;

public class Sistema {
    private ProfessorDAO professorDAO = ProfessorDAO.getInstance();
    private DepartamentoDAO departamentoDAO = DepartamentoDAO.getInstance();
    private EstudanteDAO estudanteDAO = EstudanteDAO.getInstance();
    private ParticipacaoDAO participacaoDAO = ParticipacaoDAO.getInstance();
    private ProjetoDAO projetoDAO = ProjetoDAO.getInstance();
    private TrabalhaDAO trabalhaDAO = TrabalhaDAO.getInstance();

    public void inserirProfessor(Professor professor) throws SQLException{
        professorDAO.insert(professor);
    }
    public void deletarProfessor(Professor professor) throws SQLException{
        professorDAO.delete(professor);
    }
    public void atualizarProfessor(Professor professor) throws SQLException{
        professorDAO.update(professor);
    }
    public List<Professor> consultarProfessores() throws SQLException {
        return professorDAO.selectAll();
    }
    public void inserirDepartamento(Departamento departamento) throws SQLException{
        departamentoDAO.insert(departamento);
    }
    public void deletarDepartamento(Departamento departamento) throws SQLException{
        departamentoDAO.delete(departamento);
    }
    public void atualizarDepartamento(Departamento departamento) throws SQLException{
        departamentoDAO.update(departamento);
    }
    public List<Departamento> consultarDepartamentos() throws SQLException{
        return departamentoDAO.selectAll();
    }
    public void inserirEstudante(Estudante estudante) throws SQLException{
        estudanteDAO.insert(estudante);
    }
    public void atualizarEstudante(Estudante estudante) throws SQLException{
        estudanteDAO.update(estudante);
    }
    public void deletarEstudante(Estudante estudante) throws SQLException{
        estudanteDAO.delete(estudante);
    }
    public List<Estudante> consultarEstudantes() throws SQLException{
        return estudanteDAO.selectAll();
    }
    public void inserirParticipacao(Participacao participacao) throws SQLException{
        participacaoDAO.insert(participacao);
    }
    public void deletarParticipacao(Participacao participacao) throws SQLException{
        participacaoDAO.delete(participacao);
    }
    public List<Participacao> consultarParticipacao() throws SQLException {
        return participacaoDAO.selectAll();
    }
    public void inserirProjeto(Projeto projeto) throws SQLException{
        projetoDAO.insert(projeto);
    }
    public void deletarProjeto(Projeto Projeto) throws SQLException{
        projetoDAO.delete(Projeto);
    }
    public void atualizarProjeto(Projeto projeto) throws SQLException{
        projetoDAO.update(projeto);
    }
    public List<Projeto> consultarProjeto() throws SQLException {
        return projetoDAO.selectAll();
    }
    public void inserirTrabalha(Trabalha trabalha) throws SQLException{
        trabalhaDAO.insert(trabalha);
    }
    public void deletarTrabalha(Trabalha trabalha) throws SQLException{
        trabalhaDAO.delete(trabalha);
    }
    public void atualizarTrabalha(Trabalha trabalha) throws SQLException{
        trabalhaDAO.update(trabalha);
    }
    public List<Trabalha> consultarTrabalha() throws SQLException {
        return trabalhaDAO.selectAll();
    }
}
