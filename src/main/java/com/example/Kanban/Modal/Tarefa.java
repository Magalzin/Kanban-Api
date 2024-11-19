package com.example.Kanban.Modal;

import com.example.Kanban.Enums.TarefaPriorityEnum;
import com.example.Kanban.Enums.TarefaStatusEnum;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String description;
    private LocalDate createDate = LocalDate.now(); // Data de criação automática
    private TarefaStatusEnum status = TarefaStatusEnum.AFAZER;
    private TarefaPriorityEnum priority;
    private LocalDate dueDate; // Data de vencimento definida pelo usuário




    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TarefaStatusEnum getStatus() {
        return status;
    }

    public String getStatusFormated(){
        String statusGet;
        switch (status){
            case AFAZER :
                statusGet = "A Fazer";
                break;
            case EMPROGRESSO :
                statusGet = "Em Progresso";
                break;
            case CONCLUIDO :
                statusGet = "Concluido";
                break;
            default:
                statusGet = "";
        }

        return statusGet;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TarefaPriorityEnum getPriority() {
        return priority;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TarefaStatusEnum status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TarefaPriorityEnum priority) {
        this.priority = priority;
    }

    public void setCreateDate(LocalDate createDate) {this.createDate = createDate;}
}
