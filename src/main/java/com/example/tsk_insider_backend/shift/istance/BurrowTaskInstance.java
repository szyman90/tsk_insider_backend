package com.example.tsk_insider_backend.shift.istance;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "burrow_task_instance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BurrowTaskInstance extends TaskInstanceAbstract {

    @ManyToOne(optional = false)
    @JoinColumn(name = "shift_instance_id")
    ShiftInstance shiftInstance;
}
