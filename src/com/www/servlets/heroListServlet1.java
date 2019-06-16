package com.www.servlets;

import bean.Hero;
import dao.heroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "heroListServlet1")
public class heroListServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");

        List<Hero> heroes = new heroDAO().list();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table align = 'center' border = '1' cellspacing = '0'>\r\n");
        stringBuffer.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>delete</td></tr>\r\n");

        String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td><td><a href='deleteHero?id=%d'>" +
                "delete</a></td></tr>\r\n";
        //注意deleteHero?id=%d语句中?两边不要留空，id=%d中=两边也不要留空，否则会出错
        for (Hero hero : heroes
             ) {
            String tr = String.format(trFormat, hero.getId(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId());
            stringBuffer.append(tr);
        }

        stringBuffer.append("</table>");

        resp.getWriter().write(stringBuffer.toString());
    }
}
