package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.CentroCusto;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.CentroCustoRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class CentroCustoForm {
    
    @NotNull @NotEmpty @Length(min = 3)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public CentroCusto converter(CentroCustoRepository centroCustoRepository, UsuarioApi usuario){
        Integer totalCentrosDeCusto = 0;
        try {
            totalCentrosDeCusto = centroCustoRepository.maiorCadastro(usuario.getEmpresa().getId());
            totalCentrosDeCusto += 1;
        } catch (Exception ex){
            totalCentrosDeCusto = 1;
        }   
        return new CentroCusto(totalCentrosDeCusto, descricao, usuario.getEmpresa());
    }

    public CentroCusto atualizar(Integer id, CentroCustoRepository centroCustoRepository, UsuarioApi usuario) {
        Optional<CentroCusto> centroOpt = centroCustoRepository.procurarPorIdDaEmpresaEEmpresa(id, usuario.getEmpresa().getId());
        if(centroOpt.isPresent()){
            CentroCusto centroEditavel = centroOpt.get();
            centroEditavel.setDescricao(descricao);
            return centroEditavel;
        }
        return null;
    }
}
