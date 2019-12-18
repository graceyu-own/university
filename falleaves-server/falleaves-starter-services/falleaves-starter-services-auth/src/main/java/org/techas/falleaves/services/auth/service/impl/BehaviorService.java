package org.techas.falleaves.services.auth.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techas.falleaves.cache.service.ICacheService;
import org.techas.falleaves.services.auth.service.IBehaviorService;
import org.techas.falleaves.utils.DesUtils;
import org.techas.falleaves.utils.Utils;

@Service
public class BehaviorService implements IBehaviorService {

    @Override
    public boolean commonBehavior() {
        return true;
    }
}
