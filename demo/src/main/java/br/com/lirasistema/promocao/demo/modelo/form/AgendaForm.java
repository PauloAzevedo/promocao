package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Agenda;
import br.com.lirasistema.promocao.demo.modelo.Cliente;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.AgendaRepository;
import br.com.lirasistema.promocao.demo.repository.ClienteRepository;
import br.com.lirasistema.promocao.demo.utilidades.Util;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class AgendaForm {

    @NotNull
    @NotEmpty
    @Length(min = 10, message = "Formato aceito 'aaaa-mm-dd'")
    private String data;

    @NotNull
    private Integer situacao;

    @NotNull
    @Range(min = 0, max = 23)
    private Integer hora;

    @NotNull
    @Range(min = 0, max = 59)
    private Integer minuto;

    @NotNull
    private Integer cliente;

    @NotNull
    @Length(min = 3)
    private String obs;

    public void setData(String data) {
        this.data = data;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Agenda converter(UsuarioApi usuario, ClienteRepository clienteRepository, AgendaRepository agendaRepository) {
        Optional<Cliente> clienteOpt = clienteRepository.procurarPorIdDaEmpresaEEmpresa(this.cliente, usuario.getEmpresa().getId());
        if (clienteOpt.isPresent()) {
            Integer totalAgendas = 0;
            try {
                totalAgendas = agendaRepository.maiorCadastro(usuario.getEmpresa().getId());
                totalAgendas += 1;
                //System.out.println(totalAgendas);
            } catch (Exception ex) {
                totalAgendas = 1;
            }
            return new Agenda(Util.stringToCalendarWeb(this.data), this.situacao, this.hora, this.minuto, this.obs, totalAgendas, clienteOpt.get(), usuario.getEmpresa(), usuario);
        }
        return null;
    }

    public Agenda atualizar(Integer id, AgendaRepository agendaRepository, UsuarioApi usuario, ClienteRepository clienteRepository) {
        Optional<Agenda> agendaOpt = agendaRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
        if (agendaOpt.isPresent()) {
            Agenda agendaEditada = agendaOpt.get();
            agendaEditada.setData(Util.stringToCalendarWeb(this.data));
            agendaEditada.setHora(this.hora);
            agendaEditada.setMinuto(this.minuto);
            agendaEditada.setObs(this.obs);
            agendaEditada.setSituacao(this.situacao);
            Optional<Cliente> clienteOpt = clienteRepository.procurarPorIdDaEmpresaEEmpresa(this.cliente, usuario.getEmpresa().getId());
            if (clienteOpt.isPresent()) {
                agendaEditada.setCliente(clienteOpt.get());
            }
            return agendaEditada;
        }
        return null;
    }
}
