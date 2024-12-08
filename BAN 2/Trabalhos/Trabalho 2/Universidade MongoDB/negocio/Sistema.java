package negocio;

import java.util.List;

import dados.*;
import persistencia.*;

public class Sistema {
    private ProfessorDAO professorDAO = ProfessorDAO.getInstance();
    private DepartamentoDAO departamentoDAO = DepartamentoDAO.getInstance();
    private EstudanteDAO estudanteDAO = EstudanteDAO.getInstance();
    private ProjetoDAO projetoDAO = ProjetoDAO.getInstance();
    private TrabalhaDAO trabalhaDAO = TrabalhaDAO.getInstance();
    private TrabalhaDepartDAO trabalhaDepartDAO = TrabalhaDepartDAO.getInstance();
    private ParticipaDAO participaDAO = ParticipaDAO.getInstance();

    public void inserirProfessor(Professor professor) {
        professorDAO.insert(professor);
    }
    public void deletarProfessor(Professor professor) {
        professorDAO.delete(professor);
    }
    public void atualizarProfessor(Professor professor) {
        professorDAO.update(professor);
    }
    public List<Professor> consultarProfessores() {
        return professorDAO.selectAll();
    }
    public void inserirDepartamento(Departamento departamento) {
        departamentoDAO.insert(departamento);
    }
    public void deletarDepartamento(Departamento departamento) {
        departamentoDAO.delete(departamento);
    }
    public void atualizarDepartamento(Departamento departamento) {
        departamentoDAO.update(departamento);
    }
    public List<Departamento> consultarDepartamentos() {
        return departamentoDAO.selectAll();
    }
    public void inserirEstudante(Estudante estudante) {
        estudanteDAO.insert(estudante);
    }
    public void atualizarEstudante(Estudante estudante) {
        estudanteDAO.update(estudante);
    }
    public void deletarEstudante(Estudante estudante) {
        estudanteDAO.delete(estudante);
    }
    public List<Estudante> consultarEstudantes() {
        return estudanteDAO.selectAll();
    }
    public void inserirProjeto(Projeto projeto) {
        projetoDAO.insert(projeto);
    }
    public void deletarProjeto(Projeto projeto) {
        projetoDAO.delete(projeto);
    }
    public void atualizarProjeto(Projeto projeto) {
        projetoDAO.update(projeto);
    }
    public List<Projeto> consultarProjeto() {
        return projetoDAO.selectAll();
    }
    public void inserirParticipa(Participa participa) {
        participaDAO.insert(participa);
    }
    public void deletarParticipa(Participa participa) {
        participaDAO.delete(participa);
    }
    public void atualizarParticipa(Participa participa) {
        participaDAO.update(participa);
    }
    public List<Participa> consultarParticipa() {
        return participaDAO.selectAll();
    }
    public void inserirTrabalha(Trabalha trabalha) {
        trabalhaDAO.insert(trabalha);
    }
    public void deletarTrabalha(Trabalha trabalha) {
        trabalhaDAO.delete(trabalha);
    }
    public void atualizarTrabalha(Trabalha trabalha) {
        trabalhaDAO.update(trabalha);
    }
    public List<Trabalha> consultarTrabalha() {
        return trabalhaDAO.selectAll();
    }
    public void inserirTrabalhaDepart(TrabalhaDepart trabalhaDepart) {
        trabalhaDepartDAO.insert(trabalhaDepart);
    }
    public void deletarTrabalhaDepart(TrabalhaDepart trabalhaDepart) {
        trabalhaDepartDAO.delete(trabalhaDepart);
    }
    public void atualizarTrabalhaDepart(TrabalhaDepart trabalhaDepart) {
        trabalhaDepartDAO.update(trabalhaDepart);
    }
    public List<TrabalhaDepart> consultarTrabalhaDepart() {
        return trabalhaDepartDAO.selectAll();
    }

}