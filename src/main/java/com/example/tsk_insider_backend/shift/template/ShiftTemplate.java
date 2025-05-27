package com.example.tsk_insider_backend.shift.template;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftTemplate {
    List<BurrowTaskTemplate> burrowTasks;
    List<ActionPerCatTemplate> actionsPerCat;

//    @Column(name = "mopped")
//    private boolean mopped;
//
//    @Column(name = "swept")
//    private boolean swept;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "light")
//    private Light light;
//
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "roller_blind")
//    private RollerBlind rollerBlind;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "dishwasher")
//    private Dishwasher dishwasher;

}
