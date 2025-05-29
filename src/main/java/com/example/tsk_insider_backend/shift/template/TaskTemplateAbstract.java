package com.example.tsk_insider_backend.shift.template;

import java.util.List;

import com.example.tsk_insider_backend.shift.TaskAbstract;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class TaskTemplateAbstract extends TaskAbstract {

    private boolean mandatory;

    @ElementCollection
    @CollectionTable(name = "task_template_states", joinColumns = @JoinColumn(name = "task_id"))
    @Column(nullable = false, name = "possible_state")
    private List<String> possibleStates;
}

