package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Agenda;
import br.com.lirasistema.promocao.demo.utilidades.Util;

public class DetalhesDaAgendaDto {

    //private Integer id;
    private Integer idDaEmpresa;
    private String cliente;
    private String data;
    private String horaEMinuto;
    private String situacao;
    private String obs;
    private String usuario;
    private String empresa;

    public DetalhesDaAgendaDto(Agenda agenda) {
        //this.id = agenda.getId();
        this.idDaEmpresa = agenda.getIdDaEmpresa();
        this.cliente = agenda.getCliente().toString();
        this.data = Util.calendarToString(agenda.getData());
        this.horaEMinuto = agenda.retornaHoraEMinuto();
        this.situacao = agenda.retornaSituacaoString();
        this.obs = agenda.getObs();
        if (agenda.getUsuario() != null) {
            this.usuario = agenda.getUsuario().toString();
        }
        if(agenda.getEmpresa()!=null){
            this.empresa= agenda.getEmpresa().toString();
        }
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public String getCliente() {
        return cliente;
    }

    public String getData() {
        return data;
    }

    public String getHoraEMinuto() {
        return horaEMinuto;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getObs() {
        return obs;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEmpresa() {
        return empresa;
    }
    
    

}
