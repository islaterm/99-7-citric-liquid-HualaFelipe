package com.github.cc3002.citricjuice.model;


/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player {
  private Character character;
  private int normaLevel;
  private int stars;

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
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    character = new Character(name,hp,atk,def, evd);
    normaLevel = 1;
    //IMPLICITO: stars = 0;
  }

  /**
   * Increases this player's star count by an amount.
   */
  public void increaseStarsBy(final int amount) {
    stars += amount;
  }

  /**
   * Returns this player's star count.
   */
  public int getStars() {
    return stars;
  }

  /**
   * Set's the seed for this player's random number generator.
   * <p>
   * The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    character.setSeed(seed);
  }

  /**
   * Returns a uniformly distributed random value in [1, 6]
   */
  public int roll() {
    return character.roll();
  }

  /**
   * Returns the character's name.
   */
  public String getName() {
    return character.getName();
  }

  /**
   * Returns the character's max hit points.
   */
  public int getMaxHP() {
    return character.getMaxHP();
  }

  /**
   * Returns the current character's attack points.
   */
  public int getAtk() {
    return character.getAtk();
  }

  /**
   * Returns the current character's defense points.
   */
  public int getDef() {
    return character.getDef();
  }

  /**
   * Returns the current character's evasion points.
   */
  public int getEvd() {
    return character.getEvd();
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

  /**
   * Returns the current hit points of the character.
   */
  public int getCurrentHP() {
    return character.getCurrentHP();
  }



  /**
   * Reduces this player's star count by a given amount.
   * <p>
   * The star count will must always be greater or equal to 0
   */
  public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
           getAtk() == player.getAtk() &&
           getDef() == player.getDef() &&
           getEvd() == player.getEvd() &&
           getNormaLevel() == player.getNormaLevel() &&
           getStars() == player.getStars() &&
           getCurrentHP() == player.getCurrentHP() &&
           getName().equals(player.getName());
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
