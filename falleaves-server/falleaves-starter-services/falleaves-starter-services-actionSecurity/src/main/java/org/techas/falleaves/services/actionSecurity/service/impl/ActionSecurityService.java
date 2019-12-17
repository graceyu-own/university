package org.techas.falleaves.services.actionSecurity.service.impl;

import org.techas.falleaves.services.actionSecurity.service.IActionSecurityService;

public class ActionSecurityService implements IActionSecurityService {

    @Override
    public boolean checkIp(String ip) {
        return true;
    }
}
