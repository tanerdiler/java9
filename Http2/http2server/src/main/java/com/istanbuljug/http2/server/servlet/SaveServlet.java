package com.istanbuljug.http2.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by taner on 10.04.2017.
 */
public class SaveServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Map<String, String> attributes = convertBodyToAttributes(req);
		resp.setContentType("text/plain");
		resp.getWriter().write(format("Hello %s %s!!!", attributes.get("firstname"), attributes.get("lastname")));
	}

	private Map<String, String> convertBodyToAttributes(HttpServletRequest req) throws IOException
	{
		Map<String, String> attributes = new HashMap<>();
		req.getReader().lines().forEach(l -> {
			Pattern.compile(",")
					.splitAsStream(l)
					.map(s -> s.split("=", 2))
					.forEach(a->attributes.put(a[0], a[1]));
		});
		return attributes;
	}
}
