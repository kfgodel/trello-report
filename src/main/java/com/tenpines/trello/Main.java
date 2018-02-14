package com.tenpines.trello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    ExportadoDeTrello exportado = new ObjectMapper().readValue(new File("/home/tenpines/git/trello-report/src/main/resources/export_tablero.json"), ExportadoDeTrello.class);
    exportado.completarDatosCruzados();

    List<String> listasExcluidas = Lists.newArrayList(
//      "Iteracion 1 - 29/11 a 13/12",
//      "Iteracion 2 - 14/12 a 27/12",
//      "Iteracion 3 - 28/12 a 10/01",
//      "Iteracion 4 - 11/01 a 24/01"
    );

    List<String> labelsIncluidos = Lists.newArrayList(
      "Fuera de propuesta"
    );

    List<CardDeTrello> cards = exportado.getCards();

    PrintWriter pw = new PrintWriter("/home/tenpines/git/trello-report/src/main/resources/tareas.csv", "UTF-8");

    String lineaDeTitulo = "id, iteracion, estimado , titulo, descripcion";
    pw.println(lineaDeTitulo);
    System.out.println(lineaDeTitulo);
    cards.stream()
      .filter(CardDeTrello::isNotClosed)
//      .limit(10)
      .filter(card -> card.perteneceAUnaListaNoIncluidaEn(listasExcluidas))
      .filter(card -> labelsIncluidos.isEmpty() || card.tieneUnLabelIncluidoEn(labelsIncluidos)) // El empty es por si no usamos el filtro
      .sorted(OrdenarPorListaYPosicion.create())
      .forEach(card -> {
        String lineaCsv = card.getIdShort() + ", " + comoTextoCsv(card.getNombreDeLista()) + ", " + card.getEstimado() + ", " + comoTextoCsv(card.getName()) + ", " + comoTextoCsv(card.getDesc());
        System.out.println(lineaCsv);
        pw.println(lineaCsv);
      });

    pw.close();
  }

  private static String comoTextoCsv(String texto) {
    String unaLinea = texto
      .replaceAll("\\n", "\\\\n")
      .replaceAll("\"","'");
    return "\"" + unaLinea + "\"";
  }

}
