package com.dnillg.carassembly.domain.lock;

import java.util.function.Supplier;

public interface DomainLock {

    <T> T executeWithLock(Supplier<T> supplier);

}
