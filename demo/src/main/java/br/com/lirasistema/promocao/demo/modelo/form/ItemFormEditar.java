package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Grupo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.repository.GrupoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ItemFormEditar {
    
    @NotNull
    private Integer idDaEmpresa;
    
    @NotNull @NotEmpty @Length(min = 3)
    private String descricao;
    
    @NotNull @NotEmpty @Length(min = 3)
    private String composicao;
    
    @NotNull
    private Long referencia;
    
    @NotNull @NotEmpty @Length(min = 2)
    private String unidadeComercial;
    
    
    private String caminhoImagem;    
        
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

    public Item atualizar(Long id, ItemRepository itemRepository, GrupoRepository grupoRepository) {
        Optional<Item> opt = itemRepository.findById(id);
        if(opt.isPresent()){
            Item itemE =  opt.get();
            itemE.setDescricao(descricao);
            itemE.setComposicao(composicao);
            itemE.setValor(valor);
            itemE.setIdDaEmpresa(idDaEmpresa);
            itemE.setReferencia(referencia);
            itemE.setCaminhoImagem(caminhoImagem);  
            itemE.setUnidadeComercial(unidadeComercial);
            itemE.setSituacao(situacao);
            Optional<Grupo> grupoO = grupoRepository.findById(this.grupo);
            if(grupoO.isPresent()){
                itemE.setGrupo(grupoO.get());
            }
            return itemE;
        }
        return null;
    }

}
