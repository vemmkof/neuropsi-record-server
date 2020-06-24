package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.user.UserPasswordDto;
import com.ipn.escom.neuropsi.commons.exception.InvalidPasswordReset;
import com.ipn.escom.neuropsi.commons.exception.UserNotFoundException;

public interface UserService {
    void createPassword(UserPasswordDto userPasswordDto) throws UserNotFoundException, InvalidPasswordReset;
}
