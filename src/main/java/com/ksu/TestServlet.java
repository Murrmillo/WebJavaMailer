package com.ksu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import sender.Mailer;


@WebServlet("/confirmation")
public class TestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        //getParameter() uses ISO-8559-1 instead of UTF-8.
        request.setCharacterEncoding("UTF-8"); // решение проблемы с  кодировкой
        String output = request.getParameter("output");
        String password = request.getParameter("password");
        String input = request.getParameter("input");
        String theme = request.getParameter("theme");
        String message = request.getParameter("message");
        String domain = request.getParameter("domain");



        try
        {

            Mailer mailer = new Mailer(output,domain,password,input,theme,message);
            mailer.SendMail();
            writer.println("Сообщение отправлено");

        }
        catch (Exception e)
        {
            writer.println("Ой, что-то пошло не так... "+ e);
        }
        finally {

            writer.close();
        }
    }
}