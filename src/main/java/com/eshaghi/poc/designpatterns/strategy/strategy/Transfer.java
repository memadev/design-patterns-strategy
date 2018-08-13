package com.eshaghi.poc.designpatterns.strategy.strategy;

import com.eshaghi.poc.designpatterns.strategy.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.eshaghi.poc.designpatterns.strategy.domain.Event.AMOUNT;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.DESTINATION;
import static com.eshaghi.poc.designpatterns.strategy.domain.Event.SOURCE;

@Component
public final class Transfer implements ActionStrategy {

    private static final Logger logger = LoggerFactory.getLogger(Deposit.class);

    @Override
    public void process(Event event) {
        logger.info("Transfer {} from {} to {}.", event.getParam(AMOUNT),
                event.getParam(SOURCE), event.getParam(DESTINATION));
    }
}
