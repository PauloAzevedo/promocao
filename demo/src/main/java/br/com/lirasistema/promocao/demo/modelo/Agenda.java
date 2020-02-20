package br.com.lirasistema.promocao.demo.modelo;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar data;
    private Integer hora;
    private Integer minuto;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private UsuarioApi usuario;

    private Integer situacao;

    private String obs;

    @ManyToOne
    private Empresa empresa;

    private Integer idDaEmpresa;
    
    public Agenda(){
        
    }
    

    public Agenda(Calendar stringToCalendarWeb, Integer situacao, Integer hora, Integer minuto, String obs, Integer totalAgendas, Cliente cliente, Empresa empresa, UsuarioApi usuario) {
        this.data = stringToCalendarWeb;
        this.situacao = situacao;
        this.hora = hora;
        this.minuto = minuto;
        this.obs = obs;
        this.cliente = cliente;
        this.empresa = empresa;
        this.idDaEmpresa = totalAgendas;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }
    
    public String retornaSituacaoString() {
        if (situacao == 0) {
            return "Agendado";
        } else if (situacao == 1) {
            return "Compareceu";
        } else {
            return "Cancelado";
        }
    }
    
    public String retornaHoraEMinuto() {
        return hora + ":" + minuto;
    }

    public UsuarioApi getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioApi usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
