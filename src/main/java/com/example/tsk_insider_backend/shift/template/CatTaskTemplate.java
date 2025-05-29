package com.example.tsk_insider_backend.shift.template;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cat_task_template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatTaskTemplate extends TaskTemplateAbstract {

    @ManyToOne(optional = false)
    @JoinColumn(name = "action_per_cat_template_id")
    private ActionsPerCatTemplate actionsPerCatTemplate;
}