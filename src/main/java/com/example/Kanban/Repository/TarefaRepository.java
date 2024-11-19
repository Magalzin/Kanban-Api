package com.example.Kanban.Repository;

import com.example.Kanban.Modal.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TarefaRepository extends JpaRepository<Tarefa, Integer> { }
