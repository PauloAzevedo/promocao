package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Promocao;


public class DetalhesPromocaoDto {
    private Long id;    
    private String titulo;
    private String descricao;
    private Double valor;
    private String autor;
    private String hashTexto;
    private Integer situacao;
    
    public DetalhesPromocaoDto(Promocao promo){
        this.id = promo.getId();
        this.titulo = promo.getTitulo();
        this.descricao = promo.getDescricao();                
        this.valor = promo.getValor();
        this.autor = promo.getAutor().toString();
        this.hashTexto = promo.getHashTexto();
        this.situacao = promo.getSituacao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public String getAutor() {
        return autor;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public Integer getSituacao() {
        return situacao;
    }
    
        
    
}
