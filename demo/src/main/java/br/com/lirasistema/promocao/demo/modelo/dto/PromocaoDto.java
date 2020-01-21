
package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Promocao;
import org.springframework.data.domain.Page;


public class PromocaoDto {
    private Long id;    
    private String titulo;
    private Double valor;
    private String autor;
    private String hashTexto;
    private Integer situacao;
    
    public PromocaoDto(Promocao promo){
        this.id = promo.getId();
        this.titulo = promo.getTitulo();
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
    
        
    public static Page<PromocaoDto> converter(Page<Promocao> promocoes) {
        return promocoes.map(PromocaoDto::new);
    }
    
}
