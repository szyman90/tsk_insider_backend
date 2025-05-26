package com.example.tsk_insider_backend.shift;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.example.tsk_insider_backend.shift.task.CatShiftAction;
import com.example.tsk_insider_backend.shift.task.ShiftTask;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {

    @Id
    @GeneratedValue
    private UUID id;


    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatShiftAction> catActions = new ArrayList<>();

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<ShiftTask> taskPerShift;

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
