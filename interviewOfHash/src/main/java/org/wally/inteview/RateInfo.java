package org.wally.inteview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RateInfo {
    private int maxHash;
    private int minHash;
    private int multiplier;
    private int collisionCount;
    private double collisionRate;
}
