package com.example.tsk_insider_backend.shift.istance;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cat_task_instance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatTaskInstance extends TaskInstanceAbstract {

    @ManyToOne(optional = false)
    @JoinColumn(name = "action_per_cat_instance_id")
    private ActionsPerCatInstance taskPerCatInstance;
}
