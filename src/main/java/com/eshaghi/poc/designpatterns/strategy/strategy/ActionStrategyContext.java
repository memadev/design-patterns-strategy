package com.eshaghi.poc.designpatterns.strategy.strategy;

import com.eshaghi.poc.designpatterns.strategy.domain.Action;
import com.eshaghi.poc.designpatterns.strategy.domain.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Arrays;
import java.util.EnumMap;

@Component
public final class ActionStrategyContext implements ApplicationContextAware {

    private ApplicationContext context;
    private EnumMap<Action, ActionStrategy> strategies;

    public void process(Event event) {
        strategies.get(event.getAction()).process(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.strategies = new EnumMap<>(Action.class);
        Arrays.stream(Action.values())
                .forEach(action -> strategies.put(action, (ActionStrategy) context.getBean(action.getName())));
    }
}
