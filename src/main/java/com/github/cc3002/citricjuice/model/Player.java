package com.github.cc3002.citricjuice.model;


import java.util.Objects;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends Character{
  private int normaLevel;


  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def, final int evd){
    super(name,hp,atk,def, evd);
    normaLevel = 1;
  }

  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;
    if (!super.equals(o)) return false;
    Player player = (Player) o;
    return getNormaLevel() == player.getNormaLevel() &&
            getStars() == player.getStars();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getNormaLevel(), getStars());
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
