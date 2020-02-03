package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Item;
import org.springframework.data.domain.Page;


public class ItemDto {
    private Long id;
    private Integer idDaEmpresa;
    private String descricao;
    private String composicao;
    private Double valor;
    private String caminhoImagem;
    private String unidadeComercial;
    private String hashTexto;
    private Integer situacao;
    
    public ItemDto(Item item ){
        this.id = item.getId();
        this.idDaEmpresa = item.getIdDaEmpresa();
        this.descricao = item.getDescricao();
        this.composicao = item.getComposicao();
        this.valor = item.getValor();
        this.caminhoImagem = item.getCaminhoImagem();
        this.unidadeComercial = item.getUnidadeComercial();
        this.hashTexto = item.getHashTexto();
        this.situacao = item.getSituacao();
    }

    public Long getId() {
        return id;
    }

    public Integer getIdDaEmpresa() {
        return idDaEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getComposicao() {
        return composicao;
    }

    public Double getValor() {
        return valor;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public String getUnidadeComercial() {
        return unidadeComercial;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public Integer getSituacao() {
        return situacao;
    }
    
    public static Page<ItemDto> converter(Page<Item> itens){
        return itens.map(ItemDto::new);
    }
    
    
}
