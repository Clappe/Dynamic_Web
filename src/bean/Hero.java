package bean;

public class Hero {
    public int id;
    public String name;
    public float hp;
    public int damage;
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public float getHp(){
        return hp;
    }
    public int getDamage(){
        return damage;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHp(float hp){
        this.hp = hp;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
}
