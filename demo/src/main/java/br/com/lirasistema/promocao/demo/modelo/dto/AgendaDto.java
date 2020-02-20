/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo.dto;


import br.com.lirasistema.promocao.demo.modelo.Agenda;
import br.com.lirasistema.promocao.demo.utilidades.Util;
import org.springframework.data.domain.Page;

/**
 *
 * @author paulo
 */
public class AgendaDto {
    //private Integer id;
    private Integer idDaEmpresa;
    private String cliente;
    private String data;
    private String horaEMinuto;
    private String situacao;
    private String obs;
    
    public AgendaDto(Agenda agenda){
       // this.id = agenda.getId();
        this.idDaEmpresa = agenda.getIdDaEmpresa();
        this.cliente = agenda.getCliente().toString();
        this.data = Util.calendarToString(agenda.getData());
        this.horaEMinuto = agenda.retornaHoraEMinuto();
        this.situacao = agenda.retornaSituacaoString();
        this.obs = agenda.getObs();
    }

//    public Integer getId() {
//        return id;
//    }

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


    public String getObs() {
        return obs;
    }
        
    public String retornaSituacaoString() {
        return situacao;
    }    
    public String retornaHoraEMinuto() {
        return horaEMinuto;
    }
    
    public static Page<AgendaDto> converter(Page<Agenda> agendas) {
        return agendas.map(AgendaDto::new);
    }

    public String getSituacao() {
        return situacao;
    }

    
    
}
