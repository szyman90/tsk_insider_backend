package com.example.tsk_insider_backend.shift.template;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.cat.Cat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "action_per_cat_template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionsPerCatTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shift_template_id")
    private ShiftTemplate shiftTemplate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @OneToMany(mappedBy = "action_per_cat_template", cascade = CascadeType.ALL)
    private List<CatTaskTemplate> catTasks;
}
