package dao;

import bean.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class heroDAO {
    public heroDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/how2java?serverTimezone = UTC & characterEncoding = UTF-8";
        String username = "root";
        String password = "Lin_1772815";
        return DriverManager.getConnection(url, username, password);
    }

    public int getTatal(){
        int total = 0;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();){
            String sql = "select count(*) from hero";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                total = resultSet.getInt(1);
            }
            System.out.println("total: " + total);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public void add(Hero hero){
        String sql = "insert into hero values(null, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, hero.name);
            preparedStatement.setFloat(2, hero.hp);
            preparedStatement.setInt(3, hero.damage);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                hero.id = id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Hero hero){
        String sql = "update hero set name = ?, hp = ?, damage = ? where id = ? ";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, hero.name);
            preparedStatement.setFloat(2, hero.hp);
            preparedStatement.setInt(3, hero.damage);
            preparedStatement.setInt(4, hero.id);

            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try (Connection connection = getConnection();Statement statement = connection.createStatement();){
                    String sql = "delete from hero where id = " + id;
                    statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Hero get(int id){
        Hero hero = null;

        try (Connection connection = getConnection(); Statement statement = connection.createStatement();){
            String sql = "select * from hero where id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()){
                hero = new Hero();
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                hero.id = id;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return hero;
    }

    public List<Hero> list(){
        return list(0, Short.MAX_VALUE);
    }

    public List<Hero> list(int start, int count){
        List<Hero> heros = new ArrayList<Hero>();

        String sql = "select * from hero order by id desc limit ?, ? ";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Hero hero = new Hero();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);
                hero.id = id;
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                heros.add(hero);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return heros;
    }
}
