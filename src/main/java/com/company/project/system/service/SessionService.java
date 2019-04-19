package com.company.project.system.service;


import com.company.project.system.model.UserOnline;

import java.util.List;

public interface SessionService {

	List<UserOnline> list();

	void forceLogout(String sessionId);
}
