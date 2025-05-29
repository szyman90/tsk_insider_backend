package com.example.tsk_insider_backend.shift.istance;

import java.util.List;

import com.example.tsk_insider_backend.shift.ShiftAbstract;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftInstance extends ShiftAbstract {

    @OneToMany(mappedBy = "shift_instance", cascade = CascadeType.ALL)
    List<BurrowTaskInstance> burrowTasks;

    @OneToMany(mappedBy = "shift_instance", cascade = CascadeType.ALL)
    List<ActionsPerCatInstance> actionsPerCat;
}
