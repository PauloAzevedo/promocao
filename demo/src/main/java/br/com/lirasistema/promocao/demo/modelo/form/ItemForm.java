package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.GrupoRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ItemForm {
    
    @NotNull
    private Integer idDaEmpresa;
    
    @NotNull @NotEmpty @Length(min = 3)
    private String descricao;
    
    @NotNull @NotEmpty @Length(min = 3)
    private String composicao;
    
    @NotNull @NotEmpty @Length(min = 3)
    private Long referencia;
    
    @NotNull @NotEmpty @Length(min = 3)
    private String unidadeComercial;
    
    
    private String caminhoImagem;
    
    @NotNull @NotEmpty @Length(min = 10)
    private String hashTexto;
    
    @NotNull
    private Integer situacao;
    
    @NotNull
    private Double valor;   
    
    @NotNull
    private Integer grupo;

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public void setIdDaEmpresa(Integer idDaEmpresa) {
        this.idDaEmpresa = idDaEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public Long getReferencia() {
        return referencia;
    }

    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

    

    public String getUnidadeComercial() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(String unidadeComercial) {
        this.unidadeComercial = unidadeComercial;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Item converter(UsuarioApi usuario, GrupoRepository grupoRepository) {
        Optional<Grupo> grupoO = grupoRepository.findById(this.grupo);
        if (grupoO.isPresent()) {
            return new Item(this.idDaEmpresa, this.descricao, this.referencia, this.composicao, this.valor, this.unidadeComercial, this.caminhoImagem, this.hashTexto, this.situacao, usuario.getEmpresa(), grupoO.get());
        }
        return null;
    }

}
