package com.example.Kanban.Controller;


import com.example.Kanban.Modal.Tarefa;
import com.example.Kanban.Service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.findAllByPriorityAndDate();
    }


    @PostMapping // criar tarefa
    public Tarefa postTarefa(@RequestBody  Tarefa tarefa){
        return tarefaService.insertTarefa(tarefa);
    }

    @DeleteMapping("/{id}") // deletar tarefa
    public Tarefa deleteTarefa(@PathVariable Integer id) {
        if(tarefaService.searchById(id)!=null){
            return tarefaService.deleteTarefa(id);
        }
        return tarefaService.searchById(id);
    }

    @PutMapping("/{id}") // atualizar tarefa
    public Tarefa putTarefa(@PathVariable Integer id,  @RequestBody Tarefa tarefa){
        if(tarefaService.searchById(id)!=null){
            return tarefaService.putTarefa(id,tarefa);
        }
        return tarefaService.searchById(id);
    }

    @PutMapping("/{id}/move") // atualizar status tarefa
    public Tarefa moveTarefa(@PathVariable Integer id){
        if(tarefaService.searchById(id)!=null){
            return tarefaService.moveTarefa(id);
        }
        return tarefaService.searchById(id);
    }


}
