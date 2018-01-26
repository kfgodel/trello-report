package com.tenpines.trello;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    ExportadoDeTrello exportado = new ObjectMapper().readValue(new File("/home/tenpines/git/trello-report/src/main/resources/export_tablero.json"), ExportadoDeTrello.class);

    System.out.println("id, iteracion, estimado , titulo, descripcion");
    List<CardDeTrello> cards = exportado.getCards();
    cards.stream()
      .filter(card -> card.isNotClosed())
      .limit(10)
      .forEach(card -> {
      System.out.println(card.getIdShort() + ", "+ card.getIdList() + ", "+ card.getEstimado() + ", "+ card.getName() + ", "+ card.getDesc());
    });
  }

}
