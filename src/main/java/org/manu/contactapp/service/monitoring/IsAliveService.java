package org.manu.contactapp.service.monitoring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

@Service(value = "isAliveService")
public class IsAliveService implements HttpRequestHandler {

	@Override
	public void handleRequest(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		response.getWriter().write("OK");
	}

}
