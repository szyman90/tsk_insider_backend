package com.example.tsk_insider_backend.shift.task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shift_task")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTask {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "required")
    private boolean required;

    @ElementCollection
    @CollectionTable(name = "shift_task_states", joinColumns = @JoinColumn(name = "shift_task_id"))
    @Column(name = "states")
    private List<String> possibleStates = new ArrayList<>();
}

