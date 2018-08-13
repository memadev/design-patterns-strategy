package com.eshaghi.poc.designpatterns.strategy.strategy;

import com.eshaghi.poc.designpatterns.strategy.domain.Event;

public interface ActionStrategy {

    void process(Event event);
}
