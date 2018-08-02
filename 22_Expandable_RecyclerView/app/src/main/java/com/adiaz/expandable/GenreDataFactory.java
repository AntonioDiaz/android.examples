package com.adiaz.expandable;

import com.adiaz.expandable.multicheck.MultiCheckGenre;
import com.adiaz.expandable.singlecheck.SingleCheckGenre;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Genre> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre(),
        makeSalsaGenre(),
        makeBluegrassGenre());
  }

  public static List<MultiCheckGenre> makeMultiCheckGenres() {
    return Arrays.asList(makeMultiCheckRockGenre(),
        makeMultiCheckJazzGenre(),
        makeMultiCheckClassicGenre(),
        makeMultiCheckSalsaGenre(),
        makeMulitCheckBluegrassGenre());
  }

  public static List<SingleCheckGenre> makeSingleCheckGenres() {
    return Arrays.asList(makeSingleCheckRockGenre(),
        makeSingleCheckJazzGenre(),
        makeSingleCheckClassicGenre(),
        makeSingleCheckSalsaGenre(),
        makeSingleCheckBluegrassGenre());
  }

  public static Genre makeRockGenre() {
    return new Genre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }

  public static MultiCheckGenre makeMultiCheckRockGenre() {
    return new MultiCheckGenre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }

  public static SingleCheckGenre makeSingleCheckRockGenre() {
    return new SingleCheckGenre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }

  public static List<Artist> makeRockArtists() {
    Artist queen = Artist.builder().name("Qeen").isFavorite(true).build();
    Artist styx = Artist.builder().name("Styx").isFavorite(true).build();
    Artist reoSpeedwagon = Artist.builder().name("REO Speedwagon").isFavorite(false).build();
    Artist boston = Artist.builder().name("Boston").isFavorite(false).build();

    return Arrays.asList(queen, styx, reoSpeedwagon, boston);
  }

  public static Genre makeJazzGenre() {
    return new Genre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }

  public static MultiCheckGenre makeMultiCheckJazzGenre() {
    return new MultiCheckGenre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }

  public static SingleCheckGenre makeSingleCheckJazzGenre() {
    return new SingleCheckGenre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }

  public static List<Artist> makeJazzArtists() {
    Artist milesDavis = Artist.builder().name("milesDavis").isFavorite(false).build();
    Artist ellaFitzgerald = Artist.builder().name("ellFitzgerald").isFavorite(false).build();
    Artist billieHoliday = Artist.builder().name("billieHoliday").isFavorite(false).build();
    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Genre makeClassicGenre() {
    return new Genre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }

  public static MultiCheckGenre makeMultiCheckClassicGenre() {
    return new MultiCheckGenre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }

  public static SingleCheckGenre makeSingleCheckClassicGenre() {
    return new SingleCheckGenre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }

  public static List<Artist> makeClassicArtists() {
    Artist beethoven = Artist.builder().name("Ludwig van Beethoven").isFavorite(false).build();
    Artist bach = Artist.builder().name("Johann Sebastian Bach").isFavorite(true).build();
    Artist brahms = Artist.builder().name("brahms").isFavorite(true).build();
    Artist puccini =Artist.builder().name("puccini").isFavorite(false).build();

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Genre makeSalsaGenre() {
    return new Genre("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }

  public static MultiCheckGenre makeMultiCheckSalsaGenre() {
    return new MultiCheckGenre("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }

  public static SingleCheckGenre makeSingleCheckSalsaGenre() {
    return new SingleCheckGenre("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }

  public static List<Artist> makeSalsaArtists() {
    Artist hectorLavoe = Artist.builder().name("hectorLavoe").isFavorite(false).build();
    Artist celiaCruz = Artist.builder().name("celiaCruz").isFavorite(false).build();
    Artist willieColon = Artist.builder().name("willieColon").isFavorite(false).build();
    Artist marcAnthony = Artist.builder().name("marcAnthony").isFavorite(false).build();

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Genre makeBluegrassGenre() {
    return new Genre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static MultiCheckGenre makeMulitCheckBluegrassGenre() {
    return new MultiCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static SingleCheckGenre makeSingleCheckBluegrassGenre() {
    return new SingleCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static List<Artist> makeBluegrassArtists() {
    Artist billMonroe = Artist.builder().name("Bill Monroe").isFavorite(true).build();
    return Arrays.asList(billMonroe);
  }

}

