package org.techas.falleaves.services.auth.service.impl;

import org.springframework.stereotype.Service;
import org.techas.falleaves.services.auth.service.IBehaviorService;

@Service
public class BehaviorService implements IBehaviorService {

    @Override
    public boolean commonBehavior() {
        return true;
    }
}
