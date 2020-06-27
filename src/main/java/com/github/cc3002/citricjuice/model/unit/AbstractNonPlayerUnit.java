package com.github.cc3002.citricjuice.model.unit;

import java.util.Objects;

public abstract class AbstractNonPlayerUnit extends AbstractUnit{


    public AbstractNonPlayerUnit(final String name, final int hp,  int atk,  int def,
                                 int evd) {
        super(name, hp,atk,def,evd);
    }




}
