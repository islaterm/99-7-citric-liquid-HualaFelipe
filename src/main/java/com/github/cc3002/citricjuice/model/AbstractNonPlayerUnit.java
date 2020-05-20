package com.github.cc3002.citricjuice.model;

import java.util.Objects;

public abstract class AbstractNonPlayerUnit extends AbstractUnit{


    public AbstractNonPlayerUnit(final String name, final int hp,  int atk,  int def,
                                 int evd) {
        super(name, hp,atk,def,evd);
    }

    public void setAtk(int atk) {
        System.out.println("It cant set atk points");
    }

    public void setDef(int def) {
        System.out.println("It cant set def points");
    }

    public void setEvd(int evd) {
        System.out.println("It cant set evd points");
    }






}
