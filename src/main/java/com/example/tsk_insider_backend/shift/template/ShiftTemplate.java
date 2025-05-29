package com.example.tsk_insider_backend.shift.template;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import com.example.tsk_insider_backend.shift.ShiftAbstract;

@Entity(name = "shift_template")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftTemplate extends ShiftAbstract {

    @Column(nullable = false, name = "active")
    private boolean active;

    @Column(nullable = false, name = "version")
    private int version;

    @OneToMany(mappedBy = "shift_template", cascade = CascadeType.ALL)
    List<BurrowTaskTemplate> burrowTasks;

    @OneToMany(mappedBy = "shift_template", cascade = CascadeType.ALL)
    List<ActionsPerCatTemplate> actionsPerCat;
}
