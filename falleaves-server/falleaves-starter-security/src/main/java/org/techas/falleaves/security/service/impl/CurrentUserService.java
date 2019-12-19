package org.techas.falleaves.security.service.impl;

import org.apache.shiro.SecurityUtils;
import org.techas.falleaves.security.service.ICurrentUserService;

public class CurrentUserService implements ICurrentUserService {

    @Override
    public boolean logout() {
        SecurityUtils.getSubject().logout();

        return true;
    }
}
