package com.tenpines.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PluginDataDeTrello {

  public static final String PLUGIN_DE_POKER = "597cbecff4fe5f1d91d4b614";

  private String idPlugin;
  private String value;

  public String getIdPlugin() {
    return idPlugin;
  }

  public void setIdPlugin(String idPlugin) {
    this.idPlugin = idPlugin;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getEstimado() {
    String value = getValue();
    try {
      DataDeEstimacion estimacion = new ObjectMapper().readValue(value, DataDeEstimacion.class);
      return estimacion.getEstimate();
    } catch (IOException e) {
      throw new RuntimeException("Fallo el parse del estimate["+value+"]");
    }
  }

  public boolean esDelPluginDePoker() {
    return PLUGIN_DE_POKER.equals(getIdPlugin());
  }
}
