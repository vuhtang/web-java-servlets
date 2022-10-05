package com.vuhtang.lab2.controllers;

import com.vuhtang.lab2.repository.DataManagerImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ControllerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().setAttribute("dataManager", new DataManagerImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        if (params.containsKey("req_shots") && params.get("req_shots")[0].equals("1")) {
            getServletContext().getNamedDispatcher("HistoryServlet").forward(req, resp);
        } else if (params.containsKey("clear") && params.get("clear")[0].equals("1")) {
            getServletContext().getNamedDispatcher("ClearServlet").forward(req, resp);
        } else if (params.containsKey("x") && params.containsKey("y") && params.containsKey("r")) {
            getServletContext().getNamedDispatcher("AreaCheckServlet").forward(req, resp);
        } else {
            resp.sendError(400, "Bad request: not enough data to process the request");
        }
    }
}