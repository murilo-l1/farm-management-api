package com.dev.farmmanager.service.cropcycle;

import lombok.NonNull;

public interface CropCycleControlService {

    void recalculate(@NonNull final Integer cropCycleId);

}
