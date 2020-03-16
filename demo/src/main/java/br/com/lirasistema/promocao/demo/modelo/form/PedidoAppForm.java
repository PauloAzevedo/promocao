package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.PedidoApp;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import javax.validation.constraints.NotNull;

public class PedidoAppForm {

    @NotNull
    private Integer situacao;

    @NotNull
    private Double totalValorProdutos;

    @NotNull
    private Double totalValorAcrescimos;

    @NotNull
    private Double totalValorDescontos;

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public void setTotalValorProdutos(Double totalValorProdutos) {
        this.totalValorProdutos = totalValorProdutos;
    }

    public void setTotalValorAcrescimos(Double totalValorAcrescimos) {
        this.totalValorAcrescimos = totalValorAcrescimos;
    }

    public void setTotalValorDescontos(Double totalValorDescontos) {
        this.totalValorDescontos = totalValorDescontos;
    }

    public PedidoApp converter(UsuarioApi usuario) {
//        Endereco end = null;
//        Optional<Cidade> cidadeObj = cidadeRepository.findByCodigoIBGE(cidade);
//        if (cidadeObj.isPresent()) {
//            end = new Endereco(logradouro, tpLogradouro, bairro, numero, cep, complemento, cidadeObj.get(), cidade);
//            enderecoRepository.save(end);
//        }
//        Integer totalClientes = 0;
//        try {
//            totalClientes = clienteRepository.maiorCadastro(usuario.getEmpresa().getId());
//            totalClientes += 1;
//            System.out.println(totalClientes);
//        } catch (Exception ex) {
//            totalClientes = 1;
//        }
//        if (usuario.getEmpresa() != null) {
//            return new Cliente(razaosocial, fantasia, telefone, celular, email, rg_InscricaoEstadual, cnpj, responsavel, telefoneResponsavel, observacao, tipo, pessoa, cpfResponsavel, end, usuario.getEmpresa(), totalClientes);
//        } else {
//            return new Cliente(razaosocial, fantasia, telefone, celular, email, rg_InscricaoEstadual, cnpj, responsavel, telefoneResponsavel, observacao, tipo, pessoa, cpfResponsavel, end, null, totalClientes);
//        }
        return null;
    }

}
