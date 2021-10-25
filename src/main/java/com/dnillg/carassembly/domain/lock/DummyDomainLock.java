package com.dnillg.carassembly.domain.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * A simple lock that provides mutual exclusion for the provided critial section on the JVM.
 * For scaled micro-services a fenced distributed lock should be used.
 */
public class DummyDomainLock implements DomainLock {

    private static final Logger LOG = LoggerFactory.getLogger(DummyDomainLock.class);

    private final String lockName;

    public DummyDomainLock(String lockName) {
        this.lockName = lockName;
    }

    @Override
    public <T> T executeWithLock(Supplier<T> supplier) {
        LOG.debug("Acquiring lock: {}...", lockName);
        final T result;
        synchronized (this) {
            LOG.debug("Acquired lock: {}.", lockName);
            result = supplier.get();
            LOG.debug("Releasing lock: {}.", lockName);
        }
        LOG.debug("Released lock: {}.", lockName);
        return result;
    }

}
