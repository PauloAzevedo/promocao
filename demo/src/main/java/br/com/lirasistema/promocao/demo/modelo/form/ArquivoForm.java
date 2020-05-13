package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.Arquivo;
import br.com.lirasistema.promocao.demo.modelo.Item;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import br.com.lirasistema.promocao.demo.repository.ArquivoRepository;
import br.com.lirasistema.promocao.demo.repository.ItemRepository;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author paulo
 */
public class ArquivoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String caminho;

    private Long item;

    @NotNull
    private Integer tipo;

    public Arquivo converter(ItemRepository itemRepository, UsuarioApi usuario) {

        if (usuario.getEmpresa() != null) {
            Item itemInterno = null;
            if (item != null) {
                Optional<Item> itemObj = itemRepository.findById(item);
                if (itemObj.isPresent()) {
                    itemInterno = itemObj.get();
                }
            }
            if (itemInterno != null) {
                return new Arquivo(caminho, usuario.getEmpresa(), itemInterno, tipo);
            } else {
                return new Arquivo(caminho, usuario.getEmpresa(), null, tipo);
            }
        }
        return null;
    }

    public Arquivo atualizar(Long id, ItemRepository itemRepository, ArquivoRepository arquivoRepository, UsuarioApi usuario) {
        Arquivo arquivoE = arquivoRepository.getOne(id);
        arquivoE.setCaminho(caminho);
        arquivoE.setTipo(tipo);
        return arquivoE;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

}
