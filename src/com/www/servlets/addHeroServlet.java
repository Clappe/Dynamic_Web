package com.www.servlets;

import bean.Hero;
import dao.heroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addHeroServlet")
public class addHeroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        float hp = Float.parseFloat(req.getParameter("hp"));
        int damage = Integer.parseInt(req.getParameter("damage"));

        Hero hero = new Hero();
        hero.setName(name);
        hero.setHp(hp);
        hero.setDamage(damage);
        new heroDAO().add(hero);

        resp.sendRedirect("/Dynamic_Web_war_exploded/listHero");
    }
}
