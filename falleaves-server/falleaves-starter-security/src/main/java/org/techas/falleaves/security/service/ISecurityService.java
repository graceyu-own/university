package org.techas.falleaves.security.service;

public interface ISecurityService {

    boolean hasNickname(String nickname);

    boolean hasEmail(String email);

    boolean setLastSendMailTimeBySession(String sessionId);

    boolean setLastSendMailTimeByEmail(String email);

    boolean isFreqMailBySession(String sessionId);

    boolean isFreqMailByEmail(String email);
}
