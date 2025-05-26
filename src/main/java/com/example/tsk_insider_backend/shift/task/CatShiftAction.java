package com.example.tsk_insider_backend.shift.task;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.example.tsk_insider_backend.cat.Cat;
import com.example.tsk_insider_backend.shift.Shift;

@Entity
@Table(name = "cat_shift_action")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatShiftAction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @OneToMany(mappedBy = "cat_shift_action", cascade = CascadeType.ALL)
    List<ShiftTask> taskPerCat;
}