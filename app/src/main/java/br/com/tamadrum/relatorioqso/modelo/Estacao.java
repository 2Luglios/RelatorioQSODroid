package br.com.tamadrum.relatorioqso.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ettoreluglio on 03/05/17.
 */

public class Estacao implements Serializable {

    private Long id;

    private String estacao;
    private String indicatico;
    private String frequencia;
    private String subTon;
    private String offset;

    private Calendar inicioQSO;
    private Calendar terminoQSO;

    private String cidade;
    private String estado;
    private String pais;

    private Double latitude;
    private Double longitude;
    private Double altitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getIndicatico() {
        return indicatico;
    }

    public void setIndicatico(String indicatico) {
        this.indicatico = indicatico;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getSubTon() {
        return subTon;
    }

    public void setSubTon(String subTon) {
        this.subTon = subTon;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Calendar getInicioQSO() {
        return inicioQSO;
    }

    public void setInicioQSO(Calendar inicioQSO) {
        this.inicioQSO = inicioQSO;
    }

    public Calendar getTerminoQSO() {
        return terminoQSO;
    }

    public void setTerminoQSO(Calendar terminoQSO) {
        this.terminoQSO = terminoQSO;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
}
