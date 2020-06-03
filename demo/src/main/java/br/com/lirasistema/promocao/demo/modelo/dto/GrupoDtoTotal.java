/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lirasistema.promocao.demo.modelo.dto;

import br.com.lirasistema.promocao.demo.modelo.Grupo;

/**
 *
 * @author paulo
 */
public class GrupoDtoTotal {
    private Integer id;
    private String descricao;
    private Integer total;
    
    public GrupoDtoTotal(Grupo grupo, Integer total) {
        this.id = grupo.getId();
        this.descricao = grupo.getDescricao();
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getTotal() {
        return total;
    }
    
    
}
