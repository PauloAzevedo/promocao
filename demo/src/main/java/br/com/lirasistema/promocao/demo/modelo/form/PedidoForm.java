package br.com.lirasistema.promocao.demo.modelo.form;

import br.com.lirasistema.promocao.demo.modelo.PedidoDeVenda;
import br.com.lirasistema.promocao.demo.modelo.UsuarioApi;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class PedidoForm {
    
        
    //private Integer numeroNota;
    //private Integer serieNota;
    //private String chaveNota;
    private Integer tipoEmissao; // 1=Emissão normal (não em contingência);2=Contingência FS-IA, com impressão do DANFE em formulário e segurança;3=Contingência SCAN (Sistema de Contingência do Ambiente Nacional);4=Contingência DPEC (Declaração Prévia da Emissão em Contingência); 5=Contingência FS-DA, com impressão do DANFE em formulário de segurança; 6=Contingência SVC-AN (SEFAZ Virtual de Contingência do AN); 7=Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
   
    private Integer cNF_codigoNotaFical;// Codigo identificado da nota para o emitente
    private Integer modeloNota; //55 NFe, 65 NFCe
    private Integer finalidadeEmissaoNFe; // 1=NF-e normal; 2=NF-e complementar; 3=NF-e de ajuste; 4=Devolução de mercadoria. 
    
    private Integer operacaoNotaFiscal;
    private Integer idDest; //1=Operação interna; 2=Operação interestadual;3 = Operação com exterior.
    private Integer tipoAmbiente; //0 - Homolagacao, 1 - Producao
    
    private String dataEmissao;

         
    private Integer condicao;

    private Integer remetenteDestinatario;

    
    private Integer transportadora; // 

    private Integer modalidadeFrete;// 0=Por conta do emitente;1=Por conta do destinatário/remetente;2=Por conta de terceiros;9=Sem frete. (V2.0)
    private String placaVeiculo;
    private String registroANTT;
    private String UF;
    private Integer quatidadeVolumes;
    private String especie;
    private Double pesoBruto;
    private Double pesoLiquido;
    private String marca;


    @Column(length = 5000)
    private String dadosAdicionais;

    private Double baseCalculoICMS;
    private Double valorTotalICMS;
    private Double ValorTotalICMSDesonerado;
    private Double baseCalculoICMS_ST;
    private Double valorTotalICMS_ST;
    private Double valorTotalProdutosEServicos;
    private Double valorFrete;
    private Double valorSeguro;
    private Double valorDesconto;
    private Double valorAcrescimos;
    private Double valorTotalII;
    private Double valorTotalIPI;
    private Double valorTotalPIS;
    private Double valorTotalCofins;
    private Double valorTotalOutrasDespesasAcessorias;
    private Double valorTotalNotaFiscal;
    private Double valorTotalTributos;
    private Double valorEntrada;

    
    private String cpfCliente;
    
    private String hashTexto;
    
    @ManyToOne
    private UsuarioApi vendedor;
    
    public PedidoDeVenda converter(UsuarioApi usuario){
        return new PedidoDeVenda();
    }
}
