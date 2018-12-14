package android.shiny.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pokemon_table")
public class Pokemon {

    @PrimaryKey
    private int id;
    private String name_en;
    private String name_fr;
    private String url_artwork;
    private String url_sprite;
    private String url_shiny;

    private String type1;
    private String type2;

    private int speed;
    private int attack;
    private int defense;
    private int attack_sp;
    private int defense_sp;

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack_sp() {
        return attack_sp;
    }

    public void setAttack_sp(int attack_sp) {
        this.attack_sp = attack_sp;
    }

    public int getDefense_sp() {
        return defense_sp;
    }

    public void setDefense_sp(int defense_sp) {
        this.defense_sp = defense_sp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private int health;


    public Pokemon(int id, String name_en, String name_fr) {
        this.id = id;
        this.name_en = name_en;
        this.name_fr = name_fr;
        this.url_artwork = "https://www.pokebip.com/pokedex-images/artworks/"+id+".png";
        this.url_sprite = "https://www.pokebip.com/pokedex-images/sprites-7G/"+id+".png";
        this.url_shiny = "https://www.pokebip.com/pokedex-images/sprites-shinies-7G/"+id+".png";
    }

    // Setters
    public void setName_fr(String name_fr) {
        this.name_fr = name_fr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl_artwork(String url_artwork) {
        this.url_artwork = url_artwork;
    }

    public void setUrl_sprite(String url_sprite) {
        this.url_sprite = url_sprite;
    }

    public void setUrl_shiny(String url_shiny) {
        this.url_shiny = url_shiny;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName_en() {
        return name_en;
    }

    public String getName_fr() {
        return name_fr;
    }

    public String getUrl_artwork() {
        return url_artwork;
    }

    public String getUrl_sprite() {
        return url_sprite;
    }

    public String getUrl_shiny() {
        return url_shiny;
    }
}
