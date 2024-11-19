package com.example.Kanban.Service;

import com.example.Kanban.Enums.TarefaStatusEnum;
import com.example.Kanban.Modal.Tarefa;
import com.example.Kanban.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {

        List<Tarefa> todasTarefas = tarefaRepository.findAll();

        return todasTarefas;
    }

    // Para organizar todos por prioridade
    public List<Tarefa> findAllByPriorityAndDate() {
        List<Tarefa> tarefasOrdenadas = this.findAll();

        LocalDate now = LocalDate.now();

        // Ordena por prioridade e, em seguida, pela proximidade com a data limite
        return tarefasOrdenadas.stream()
                .sorted(Comparator
                        .comparing(Tarefa::getPriority)
                        .thenComparing(tarefa -> Math.abs(now.until(tarefa.getDueDate()).getDays())))
                .toList();
    }

    // Para organizar todos por status
    public List<Tarefa> findAllByStatus() {
        // Ordena as tarefas por prioridade numa nova lista
        List<Tarefa> tarefasOrdenadas = this.findAll();
        tarefasOrdenadas.sort(Comparator.comparing(Tarefa::getStatus));
        return tarefasOrdenadas;
    }

    public Tarefa insertTarefa(Tarefa tarefa){

        LocalDate now = LocalDate.now();

        if(now.isBefore(tarefa.getDueDate())){
            return tarefaRepository.save(tarefa);
        }

        throw  new RuntimeException("Problema ao criar tarefa");
    }


    public Tarefa searchById(int id){
        Optional<Tarefa>  tarefa = tarefaRepository.findById(id);
        if(tarefa.isEmpty()){
            throw  new RuntimeException("Tarefa nao encontrada");
        }
        return  tarefa.get();
    }

    public Tarefa deleteTarefa(int id) {
        Tarefa tarefa = this.searchById(id);
        if(tarefa != null) {
            tarefaRepository.deleteById(id);
            return tarefa;
        }
        throw  new RuntimeException("Tarefa nao encontrada");
    }

    public Tarefa putTarefa(Integer id, Tarefa tarefaUpdated) {
        Tarefa tarefa = this.searchById(id);
        if (tarefa != null && tarefaUpdated.getId() == tarefa.getId()) {
            LocalDate now = LocalDate.now();

            // Verifica se algum atributo da nova tabela é nulo se for nulo os atributos antigos são mantidos

            if (tarefaUpdated.getTitle() != null) {tarefa.setTitle(tarefaUpdated.getTitle());}

            if (tarefaUpdated.getDescription() != null) {tarefa.setDescription(tarefaUpdated.getDescription());}

            if (tarefaUpdated.getPriority() != null) {tarefa.setPriority(tarefaUpdated.getPriority());}

            if (tarefaUpdated.getCreateDate() != null && tarefaUpdated.getCreateDate().isBefore(now)) {tarefa.setCreateDate(tarefaUpdated.getCreateDate());}

            if (tarefaUpdated.getDueDate() != null && tarefaUpdated.getDueDate().isAfter(tarefa.getCreateDate())) {tarefa.setDueDate(tarefaUpdated.getDueDate());}

            // Salva as alterações na tarefa
            tarefaRepository.save(tarefa);
            return tarefa;
        }

        throw new RuntimeException("Tarefa não encontrada");
    }

    public Tarefa moveTarefa(Integer id){
        Tarefa tarefa = this.searchById(id);
        if(tarefa != null){
            switch (tarefa.getStatus()){
                case AFAZER:
                    tarefa.setStatus(TarefaStatusEnum.EMPROGRESSO);
                    break;
                case EMPROGRESSO:
                    tarefa.setStatus(TarefaStatusEnum.CONCLUIDO);
                    break;
                default:
                    throw  new RuntimeException("Tarefa concluída");
            }
            return tarefaRepository.save(tarefa);
        }
        throw  new RuntimeException("Tarefa nao encontrada");
    }
}
