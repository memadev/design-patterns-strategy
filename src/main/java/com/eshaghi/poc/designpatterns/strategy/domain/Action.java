package com.eshaghi.poc.designpatterns.strategy.domain;

public enum Action {

    DEPOSIT("deposit"),
    WITHDRAWAL("withdrawal"),
    TRANSFER("transfer");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
