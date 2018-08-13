package com.eshaghi.poc.designpatterns.strategy.domain;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Event {

    public static final String AMOUNT = "amount";
    public static final String SOURCE = "source";
    public static final String DESTINATION = "destination";
    public static final String NOTE = "note";

    private final String id;

    private final Instant timestamp;

    private final Action action;

    private final Map<String, Object> params;

    public String getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Action getAction() {
        return action;
    }

    public Optional<Object> getParam(String key) {
        return Optional.ofNullable(params.get(key));
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", action=" + action +
                ", params=" + params +
                '}';
    }

    private Event(Builder builder) {
        this.id = builder.id;
        this.timestamp = builder.timestamp;
        this.action = builder.action;
        this.params = builder.params;
    }

    public static Event.Builder newBuilder() {
        return new Event.Builder();
    }

    public static final class Builder {

        private String id;
        private Instant timestamp;
        private Action action;
        private Map<String, Object> params;

        private Builder() {
            params = new HashMap<>();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withAction(Action action) {
            this.action = action;
            return this;
        }

        public Builder withParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public Builder withParams(Map<String, Object> params) {
            this.params.putAll(params);
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
