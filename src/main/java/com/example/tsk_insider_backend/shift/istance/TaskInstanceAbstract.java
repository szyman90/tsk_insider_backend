package com.example.tsk_insider_backend.shift.istance;

import com.example.tsk_insider_backend.shift.TaskAbstract;

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
public abstract class TaskInstanceAbstract extends TaskAbstract {
    private String actualState;
}

