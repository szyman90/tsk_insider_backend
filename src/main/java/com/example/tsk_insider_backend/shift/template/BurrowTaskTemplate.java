package com.example.tsk_insider_backend.shift.template;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "burrow_task_template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BurrowTaskTemplate extends TaskTemplateAbstract {

    @ManyToOne(optional = false)
    @JoinColumn(name = "shift_template_id")
    private ShiftTemplate shiftTemplate;
}
