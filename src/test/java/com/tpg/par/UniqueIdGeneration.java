package com.tpg.par;

import java.util.UUID;

public interface UniqueIdGeneration {
    default String newId() { return UUID.randomUUID().toString().substring(0, 10); }
}
