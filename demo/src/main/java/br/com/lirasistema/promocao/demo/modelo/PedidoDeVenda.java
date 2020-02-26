package br.com.lirasistema.promocao.demo.modelo;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class PedidoDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer numeroNota;
    private Integer serieNota;
    private String chaveNota;
    private Integer tipoEmissao; // 1=Emissão normal (não em contingência);2=Contingência FS-IA, com impressão do DANFE em formulário e segurança;3=Contingência SCAN (Sistema de Contingência do Ambiente Nacional);4=Contingência DPEC (Declaração Prévia da Emissão em Contingência); 5=Contingência FS-DA, com impressão do DANFE em formulário de segurança; 6=Contingência SVC-AN (SEFAZ Virtual de Contingência do AN); 7=Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
    private Integer tipoImpressao;
    private Integer cNF_codigoNotaFical;// Codigo identificado da nota para o emitente
    private Integer tipoOperacao; // 0 Entrada, 1 - saida
    private String versaoLeioute;
    private Integer modeloNota; //55 NFe, 65 NFCe
    private Integer finalidadeEmissaoNFe; // 1=NF-e normal; 2=NF-e complementar; 3=NF-e de ajuste; 4=Devolução de mercadoria. 
    @OneToOne
    private OperacaoNotaFiscal operacaoNotaFiscal;
    private Integer idDest; //1=Operação interna; 2=Operação interestadual;3 = Operação com exterior.
    private Integer tipoAmbiente; //0 - Homolagacao, 1 - Producao
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataEmissao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataAutorizacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataSaidaNota;
    private String horaSaida;

    private Integer indicadorPresencial;
    private Integer indicadorConsumidor;

    private String situacaoNota;
    @Column(columnDefinition = "text")
    private String retornoDaReceita; //Para rejeições e semelhantes;
    @Column(columnDefinition = "text")
    private String protocoloAutorizacaoUso;
    private String protocoloAutorizacaoNumero;
    @Column(columnDefinition = "text")
    private String motivoCancelamento;
    @Column(columnDefinition = "text")
    private String protocoloCancelamento;
    @Column(columnDefinition = "text")
    private String xmlCancelamento;
    private String situacaoCancelamento;
    private String motivoRetornoCancelamento;
    @Column(columnDefinition = "text")
    private String textoCartaCorrecao;
    @Column(columnDefinition = "text")
    private String protocoloCartaCorrecao;
    @Column(columnDefinition = "text")
    private String situacaoCartaCorrecao;
    private Integer sequenciaCartaCorrecao;
    @Column(columnDefinition = "text")
    private String xmlCartaCorrecao;
    @Column(columnDefinition = "text")
    private String xmlInutilizacao;
    @Column(columnDefinition = "text")
    private String protocoloInutilizacao;
    private String statusInutilizacao;
    private String motivoInutilizacao;

    private String[] chaveNotasReferenciadas;

    @OneToOne
    private CondicaoDePagamento condicao;

    @OneToOne
    private Empresa emitente;

    @OneToOne
    private Cliente remetenteDestinatario;

    @OneToOne
    private Cliente transportadora; // 

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
    @Column(length = 2000)
    private String informacoesInteressesFisco;
    @Column(columnDefinition = "text")
    private String xml;
    private String recibo;

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
    @Column(columnDefinition = "text")
    private String QRCode;
    @Column(columnDefinition = "text")
    private String digestValue;
    
    private String hashTexto;
    
    @ManyToOne
    private UsuarioApi vendedor;
    
    
    public PedidoDeVenda(){        
    }
    
    public PedidoDeVenda(String cpf){
        this.cpfCliente = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public Integer getSerieNota() {
        return serieNota;
    }

    public void setSerieNota(Integer serieNota) {
        this.serieNota = serieNota;
    }

    public String getChaveNota() {
        return chaveNota;
    }

    public void setChaveNota(String chaveNota) {
        this.chaveNota = chaveNota;
    }

    public Integer getTipoEmissao() {
        return tipoEmissao;
    }

    public void setTipoEmissao(Integer tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public Integer getTipoImpressao() {
        return tipoImpressao;
    }

    public void setTipoImpressao(Integer tipoImpressao) {
        this.tipoImpressao = tipoImpressao;
    }

    public Integer getcNF_codigoNotaFical() {
        return cNF_codigoNotaFical;
    }

    public void setcNF_codigoNotaFical(Integer cNF_codigoNotaFical) {
        this.cNF_codigoNotaFical = cNF_codigoNotaFical;
    }

    public Integer getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Integer tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getVersaoLeioute() {
        return versaoLeioute;
    }

    public void setVersaoLeioute(String versaoLeioute) {
        this.versaoLeioute = versaoLeioute;
    }

    public Integer getModeloNota() {
        return modeloNota;
    }

    public void setModeloNota(Integer modeloNota) {
        this.modeloNota = modeloNota;
    }

    public Integer getFinalidadeEmissaoNFe() {
        return finalidadeEmissaoNFe;
    }

    public void setFinalidadeEmissaoNFe(Integer finalidadeEmissaoNFe) {
        this.finalidadeEmissaoNFe = finalidadeEmissaoNFe;
    }

    public OperacaoNotaFiscal getOperacaoNotaFiscal() {
        return operacaoNotaFiscal;
    }

    public void setOperacaoNotaFiscal(OperacaoNotaFiscal operacaoNotaFiscal) {
        this.operacaoNotaFiscal = operacaoNotaFiscal;
    }

    public Integer getIdDest() {
        return idDest;
    }

    public void setIdDest(Integer idDest) {
        this.idDest = idDest;
    }

    public Integer getTipoAmbiente() {
        return tipoAmbiente;
    }

    public void setTipoAmbiente(Integer tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public Calendar getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Calendar dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Calendar getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Calendar dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public Calendar getDataSaidaNota() {
        return dataSaidaNota;
    }

    public void setDataSaidaNota(Calendar dataSaidaNota) {
        this.dataSaidaNota = dataSaidaNota;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Integer getIndicadorPresencial() {
        return indicadorPresencial;
    }

    public void setIndicadorPresencial(Integer indicadorPresencial) {
        this.indicadorPresencial = indicadorPresencial;
    }

    public Integer getIndicadorConsumidor() {
        return indicadorConsumidor;
    }

    public void setIndicadorConsumidor(Integer indicadorConsumidor) {
        this.indicadorConsumidor = indicadorConsumidor;
    }

    public String getSituacaoNota() {
        return situacaoNota;
    }

    public void setSituacaoNota(String situacaoNota) {
        this.situacaoNota = situacaoNota;
    }

    public String getRetornoDaReceita() {
        return retornoDaReceita;
    }

    public void setRetornoDaReceita(String retornoDaReceita) {
        this.retornoDaReceita = retornoDaReceita;
    }

    public String getProtocoloAutorizacaoUso() {
        return protocoloAutorizacaoUso;
    }

    public void setProtocoloAutorizacaoUso(String protocoloAutorizacaoUso) {
        this.protocoloAutorizacaoUso = protocoloAutorizacaoUso;
    }

    public String getProtocoloAutorizacaoNumero() {
        return protocoloAutorizacaoNumero;
    }

    public void setProtocoloAutorizacaoNumero(String protocoloAutorizacaoNumero) {
        this.protocoloAutorizacaoNumero = protocoloAutorizacaoNumero;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getProtocoloCancelamento() {
        return protocoloCancelamento;
    }

    public void setProtocoloCancelamento(String protocoloCancelamento) {
        this.protocoloCancelamento = protocoloCancelamento;
    }

    public String getXmlCancelamento() {
        return xmlCancelamento;
    }

    public void setXmlCancelamento(String xmlCancelamento) {
        this.xmlCancelamento = xmlCancelamento;
    }

    public String getSituacaoCancelamento() {
        return situacaoCancelamento;
    }

    public void setSituacaoCancelamento(String situacaoCancelamento) {
        this.situacaoCancelamento = situacaoCancelamento;
    }

    public String getMotivoRetornoCancelamento() {
        return motivoRetornoCancelamento;
    }

    public void setMotivoRetornoCancelamento(String motivoRetornoCancelamento) {
        this.motivoRetornoCancelamento = motivoRetornoCancelamento;
    }

    public String getTextoCartaCorrecao() {
        return textoCartaCorrecao;
    }

    public void setTextoCartaCorrecao(String textoCartaCorrecao) {
        this.textoCartaCorrecao = textoCartaCorrecao;
    }

    public String getProtocoloCartaCorrecao() {
        return protocoloCartaCorrecao;
    }

    public void setProtocoloCartaCorrecao(String protocoloCartaCorrecao) {
        this.protocoloCartaCorrecao = protocoloCartaCorrecao;
    }

    public String getSituacaoCartaCorrecao() {
        return situacaoCartaCorrecao;
    }

    public void setSituacaoCartaCorrecao(String situacaoCartaCorrecao) {
        this.situacaoCartaCorrecao = situacaoCartaCorrecao;
    }

    public Integer getSequenciaCartaCorrecao() {
        return sequenciaCartaCorrecao;
    }

    public void setSequenciaCartaCorrecao(Integer sequenciaCartaCorrecao) {
        this.sequenciaCartaCorrecao = sequenciaCartaCorrecao;
    }

    public String getXmlCartaCorrecao() {
        return xmlCartaCorrecao;
    }

    public void setXmlCartaCorrecao(String xmlCartaCorrecao) {
        this.xmlCartaCorrecao = xmlCartaCorrecao;
    }

    public String getXmlInutilizacao() {
        return xmlInutilizacao;
    }

    public void setXmlInutilizacao(String xmlInutilizacao) {
        this.xmlInutilizacao = xmlInutilizacao;
    }

    public String getProtocoloInutilizacao() {
        return protocoloInutilizacao;
    }

    public void setProtocoloInutilizacao(String protocoloInutilizacao) {
        this.protocoloInutilizacao = protocoloInutilizacao;
    }

    public String getStatusInutilizacao() {
        return statusInutilizacao;
    }

    public void setStatusInutilizacao(String statusInutilizacao) {
        this.statusInutilizacao = statusInutilizacao;
    }

    public String getMotivoInutilizacao() {
        return motivoInutilizacao;
    }

    public void setMotivoInutilizacao(String motivoInutilizacao) {
        this.motivoInutilizacao = motivoInutilizacao;
    }

    public String[] getChaveNotasReferenciadas() {
        return chaveNotasReferenciadas;
    }

    public void setChaveNotasReferenciadas(String[] chaveNotasReferenciadas) {
        this.chaveNotasReferenciadas = chaveNotasReferenciadas;
    }

    public CondicaoDePagamento getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoDePagamento condicao) {
        this.condicao = condicao;
    }

    public Empresa getEmitente() {
        return emitente;
    }

    public void setEmitente(Empresa emitente) {
        this.emitente = emitente;
    }

    public Cliente getRemetenteDestinatario() {
        return remetenteDestinatario;
    }

    public void setRemetenteDestinatario(Cliente remetenteDestinatario) {
        this.remetenteDestinatario = remetenteDestinatario;
    }

    public Cliente getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Cliente transportadora) {
        this.transportadora = transportadora;
    }

    public Integer getModalidadeFrete() {
        return modalidadeFrete;
    }

    public void setModalidadeFrete(Integer modalidadeFrete) {
        this.modalidadeFrete = modalidadeFrete;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getRegistroANTT() {
        return registroANTT;
    }

    public void setRegistroANTT(String registroANTT) {
        this.registroANTT = registroANTT;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Integer getQuatidadeVolumes() {
        return quatidadeVolumes;
    }

    public void setQuatidadeVolumes(Integer quatidadeVolumes) {
        this.quatidadeVolumes = quatidadeVolumes;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Double getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(Double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDadosAdicionais() {
        return dadosAdicionais;
    }

    public void setDadosAdicionais(String dadosAdicionais) {
        this.dadosAdicionais = dadosAdicionais;
    }

    public String getInformacoesInteressesFisco() {
        return informacoesInteressesFisco;
    }

    public void setInformacoesInteressesFisco(String informacoesInteressesFisco) {
        this.informacoesInteressesFisco = informacoesInteressesFisco;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public Double getBaseCalculoICMS() {
        return baseCalculoICMS;
    }

    public void setBaseCalculoICMS(Double baseCalculoICMS) {
        this.baseCalculoICMS = baseCalculoICMS;
    }

    public Double getValorTotalICMS() {
        return valorTotalICMS;
    }

    public void setValorTotalICMS(Double valorTotalICMS) {
        this.valorTotalICMS = valorTotalICMS;
    }

    public Double getValorTotalICMSDesonerado() {
        return ValorTotalICMSDesonerado;
    }

    public void setValorTotalICMSDesonerado(Double ValorTotalICMSDesonerado) {
        this.ValorTotalICMSDesonerado = ValorTotalICMSDesonerado;
    }

    public Double getBaseCalculoICMS_ST() {
        return baseCalculoICMS_ST;
    }

    public void setBaseCalculoICMS_ST(Double baseCalculoICMS_ST) {
        this.baseCalculoICMS_ST = baseCalculoICMS_ST;
    }

    public Double getValorTotalICMS_ST() {
        return valorTotalICMS_ST;
    }

    public void setValorTotalICMS_ST(Double valorTotalICMS_ST) {
        this.valorTotalICMS_ST = valorTotalICMS_ST;
    }

    public Double getValorTotalProdutosEServicos() {
        return valorTotalProdutosEServicos;
    }

    public void setValorTotalProdutosEServicos(Double valorTotalProdutosEServicos) {
        this.valorTotalProdutosEServicos = valorTotalProdutosEServicos;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorAcrescimos() {
        return valorAcrescimos;
    }

    public void setValorAcrescimos(Double valorAcrescimos) {
        this.valorAcrescimos = valorAcrescimos;
    }

    public Double getValorTotalII() {
        return valorTotalII;
    }

    public void setValorTotalII(Double valorTotalII) {
        this.valorTotalII = valorTotalII;
    }

    public Double getValorTotalIPI() {
        return valorTotalIPI;
    }

    public void setValorTotalIPI(Double valorTotalIPI) {
        this.valorTotalIPI = valorTotalIPI;
    }

    public Double getValorTotalPIS() {
        return valorTotalPIS;
    }

    public void setValorTotalPIS(Double valorTotalPIS) {
        this.valorTotalPIS = valorTotalPIS;
    }

    public Double getValorTotalCofins() {
        return valorTotalCofins;
    }

    public void setValorTotalCofins(Double valorTotalCofins) {
        this.valorTotalCofins = valorTotalCofins;
    }

    public Double getValorTotalOutrasDespesasAcessorias() {
        return valorTotalOutrasDespesasAcessorias;
    }

    public void setValorTotalOutrasDespesasAcessorias(Double valorTotalOutrasDespesasAcessorias) {
        this.valorTotalOutrasDespesasAcessorias = valorTotalOutrasDespesasAcessorias;
    }

    public Double getValorTotalNotaFiscal() {
        return valorTotalNotaFiscal;
    }

    public void setValorTotalNotaFiscal(Double valorTotalNotaFiscal) {
        this.valorTotalNotaFiscal = valorTotalNotaFiscal;
    }

    public Double getValorTotalTributos() {
        return valorTotalTributos;
    }

    public void setValorTotalTributos(Double valorTotalTributos) {
        this.valorTotalTributos = valorTotalTributos;
    }

    public Double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    public String getHashTexto() {
        return hashTexto;
    }

    public void setHashTexto(String hashTexto) {
        this.hashTexto = hashTexto;
    }

    public UsuarioApi getVendedor() {
        return vendedor;
    }

    public void setVendedor(UsuarioApi vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
