package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * Felipe Huala
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;
  private Panel testDrawPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel();
    testNeutralPanel = new NeutralPanel();
    testDrawPanel = new DrawPanel();
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    assertEquals(new BonusPanel(), testBonusPanel);
    assertEquals(new BossPanel(), testBossPanel);
    assertEquals(new DropPanel(), testDropPanel);
    assertEquals(new EncounterPanel(), testEncounterPanel);
    assertEquals(new DrawPanel(), testDrawPanel);
    assertEquals(new HomePanel(), testHomePanel);
    assertEquals(new NeutralPanel(), testNeutralPanel);
    assertNotEquals(testBonusPanel, testDropPanel);
    assertNotEquals(testHomePanel, testDropPanel);
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel();
    final var expectedPanel2 = new NeutralPanel();
    final var expectedPanel3 = new NeutralPanel();

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals( testNeutralPanel.getNextPanels().size(),1);

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(testNeutralPanel.getNextPanels().size(),2);

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel3);
    assertEquals(3, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel3);
    assertEquals(3, testNeutralPanel.getNextPanels().size());


    LinkedList<Panel> lista = new LinkedList<Panel>();
    lista.add(expectedPanel1);
    lista.add(expectedPanel2);
    lista.add(expectedPanel3);

    assertEquals(lista, testNeutralPanel.getNextPanels());
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());

    suguri.setCurrentHP(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHP());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(500)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(500)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  // endregion


}