package com.eshaghi.poc.designpatterns.strategy;

import com.eshaghi.poc.designpatterns.strategy.domain.Event;
import com.eshaghi.poc.designpatterns.strategy.strategy.ActionStrategyContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Stream;

import static com.eshaghi.poc.designpatterns.strategy.domain.Action.DEPOSIT;
import static com.eshaghi.poc.designpatterns.strategy.domain.Action.TRANSFER;
import static com.eshaghi.poc.designpatterns.strategy.domain.Action.WITHDRAWAL;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.AMOUNT;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.DESTINATION;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.NOTE;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.SOURCE;

public interface Bootstrap {

    static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.eshaghi.poc.designpatterns.strategy");
        ActionStrategyContext actionStrategyContext = context.getBean(ActionStrategyContext.class);

        Stream.of(Event.newBuilder()
                        .withId(UUID.randomUUID().toString())
                        .withTimestamp(Instant.now())
                        .withAction(TRANSFER)
                        .withParam(AMOUNT, new BigDecimal(15000))
                        .withParam(SOURCE, "355-25-333")
                        .withParam(DESTINATION, "355-26-334")
                        .withParam(NOTE, "august 2018 rent")
                        .build(),
                Event.newBuilder()
                        .withId(UUID.randomUUID().toString())
                        .withTimestamp(Instant.now())
                        .withAction(DEPOSIT)
                        .withParam(AMOUNT, new BigDecimal(1000))
                        .withParam(DESTINATION, "355-25-334")
                        .build(),
                Event.newBuilder()
                        .withId(UUID.randomUUID().toString())
                        .withTimestamp(Instant.now())
                        .withAction(WITHDRAWAL)
                        .withParam(AMOUNT, new BigDecimal(1200))
                        .withParam(SOURCE, "355-25-334")
                        .build())
                .forEach(actionStrategyContext::process);
    }
}
