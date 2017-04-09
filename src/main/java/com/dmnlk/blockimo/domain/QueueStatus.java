package com.dmnlk.blockimo.domain;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
public enum  QueueStatus {
    QUEUE(0),
    DONE(1),
    SKIP(2)
    ;
    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    private QueueStatus(Integer value) {
        this.value = value;
    }

    public static QueueStatus of(Integer value) {

        for (QueueStatus q : QueueStatus.values()) {
            if (q.getValue().equals(value)) {
                return q;
            }
        }
        throw new IllegalArgumentException();
    }
}
