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

@WebServlet(name = "HeroEditServlet")
public class HeroEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));

        Hero hero = new heroDAO().get(id);

        StringBuffer format = new StringBuffer();
        resp.setContentType("text/html; charset=UTF-8");

        format.append("<!DOCTYPE html>\r\n");

        format.append("<form action='updateHero' method='post'>\r\n");
        format.append("名字 ： <input type='text' name='name' value='%s' > <br>\r\n");
        format.append("血量 ： <input type='text' name='hp'  value='%f' > <br>\r\n");
        format.append("伤害： <input type='text' name='damage'  value='%d' > <br>\r\n");
        format.append("<input type='hidden' name='id' value='%d'>\r\n");
        format.append("<input type='submit' value='更新'>\r\n");
        format.append("</form>");

        String html = String.format(format.toString(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId());

        resp.getWriter().write(html);

    }
}
