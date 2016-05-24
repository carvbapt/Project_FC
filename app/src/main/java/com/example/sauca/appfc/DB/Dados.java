package com.example.sauca.appfc.DB;

/**
 * Created by Sauca on 03-06-2015.
 */
public class Dados {

    public static String[] Comerciais = {
            "Ana", "João", "Manuel", "Maria", "Duarte", "Carlos", "Filipe", "André", "Andreia", "Carolina", "Carlota"
    };

    public static String[] Email = {"saucarv@gmail.com", "carvbapt@gmail.com", "sauca@iol.pt", "carvbapt@gmail.com",
            "saucarv@gmail.com", "carvbapt@gmail.com", "sauca@iol.pt", "carvbapt@gmail.com",
            "saucarv@gmail.com", "carvbapt@gmail.com"};

    //    {Empresas,Comerciais,data_comercial}
    public static String[][] com_empresa = {
            {"0", "1", "2016.06.12"},
            {"0", "2", "2016.03.20"},
            {"0", "3", "2016.05.05"},
            {"0", "2", "2016.09.05"},
            {"0", "6", "2016.07.05"},
            {"0", "2", "2016.10.05"},
            {"0", "4", "2016.12.02"},
            {"0", "5", "2016.12.02"},
            {"4", "2", "2016.06.12"},
            {"8", "2", "2016.09.05"},
            {"42", "2", "2016.12.02"},
            {"2", "3", "2016.06.12"}};
    //    {Empresas, valores, data_val};

    public static String[][] fin_empresa = {
            {"0", "345.00", "2015.06.12"},
            {"6", "20345.00", "2015.06.12"},
            {"9", "-245.00", "2015.06.12"},
            {"0", "-1345.00", "2015.06.01"},
            {"0", "-945.00", "2015.05.15"},
            {"0", "-45.00", "2015.05.01"},
            {"0", "1245.00", "2015.04.15"},

    };

    //Dados a funcionar

    public static String[] Empresas = {
            "TU, Unipessoal, Lda.", "A Industrial Farense, Lda.", "A. Pontes & N. Mealha, Lda.", "Acabe com o Vicio, Lda.",
            "ACL - Algarve Comércio e Prestação de Serviços, Lda.", "Acral - Supermercados do Algarve S A", "ADMC - Electromecânica, Lda.",
            "AEV - Associação dos Empresários de Vilamoura", "Águas do Algarve, S.A.", "AIDA - Associação Interprofissional para o Desenv. da Produção e Valorização da Alfarroba",
            "Ala Sul - Comunicação, Audio-visuais e Produção de Eventos, Lda.", "Albombas - Sociedade Bombas Sul, Lda.", "Alcione - Comércio Alimentar, Unipessoal, Lda.",
            "Alexandre Pedro Rodrigues de Jesus", "Algardata - Sistemas Informáticos, S. A.", "Algarweb Studio Creative", "Alicoop - Cooperativa de Produtos Alimentares do Algarve, CRL",
            "Aline Decor - Mobiliário e Decoração, Lda.", "Alisuper - Exploração de Supermercados do Algarve, Lda.", "Alvarsol - Soc. Lavandarias do Algarve, Lda.",
            "AMBILEVE-Com. Equipamentos e Prod. de Limpeza, lda", "Ana Paula Cunha Fernandes Pistel", "Andrade & Andrade, Lda.", "Andrade & Filhos, Lda.",
            "Andrés Lluis Bós, Herdeiros, Lda.", "Plasfaro - Recuperação de Plásticos Lda", "Pluristel - Construções e Telecomunicações Lda",
            "Pontautos - Comércio de Automóveis Lda", "PortiBroBa International Lda.", "Prévia - Saúde Ocupacional, Higiene e Segurança Lda",
            "Primevents - Produção e Realização de Eventos", "Publirádio - Publicidade Exterior, S.A.", "Publivinhos - Publicidade e Comércio de Vinhos Lda",
            "QBL, Unipessoal, Lda.", "QC - Investimentos Turísticos Quinta da Caldeira, S. A.", "Qualigénese - Investigação e Formação, Lda.",
            "Quimotel - Comércio e Indústria de Produtos Químicos e Detergentes Lda", "Quinta da Balaia - Empreendimentos Turísticos, S.A.",
            "Quinta Formosa - Produções Aquícolas Lda", "Real Estate Practice, Unipessoal, Lda.", "Refrigeração Contreras, Lda.",
            "Refrisete - Sociedade Distribuidora de Bebidas Lda", "Rolear.ON - Soluções de Engenharia, S.A.", "Rotatur Operadores Turisticos Lda",
            "Rusgarbe - Construção, Compra e Venda de Imóveis Lda", "Safe Solutions - Projectos de Consultoria, Lda.", "Salalimpa - Serviços de Limpeza Lda",
            "Seltra - Serviços, Línguas e Traduções Lda", "Serigra - Companhia Indústrial e Comerc"
    };

    //    {Empresas, "Morada","Localidade","Contacto","Representante","Data_manutenção"}
    public static String[][] det_empresa = {
            {"0", "Rua das Laranjeiras, Nº 33 Conceição", "8005-446 FARO", "289289289", "Sr. Eurico", "2015.05.05"},
            {"1", "Av. 25 Abril, Nº 10", "8500-446 PORTIMÃO", "282345992", "Sr. Jorge", "2015.05.05"},
            {"2", "Praça dos Pescadores, nº 20", "8200-099 ALBUFEIRA", "289541225", "Sra. Ana", "2015.05.05"},
            {"3", "Dr José de Matos, 1 - 3", "8000-000 FARO", "282282282", "Sr. Daniel", "2015.10.10"},
            {"4", "Rua Liberdade nº5", "8400-100 LAGOA", "282705455", "Sr. Vasco", "2015.05.05"},
            {"5", "Rua Gil Eanes, Nº 60", "8700-123 OLLHÂO", "289899254", "Sra. Manuela", "2015.05.05"},
            {"6", "Zona Ind Lt 41", "8100-000 LOULÉ", "289888888", "Sr. José", "2015.06.12"}};

    //    {OT};
    public static  String[] Ordens={"OT_23544","OT_24795","OT_27244","OT_56745","OT_59563","OT_12345","OT_21341"};

    //    {Empresas, Data, Hora, Estado, Inicio, Fim};
    public static String[][] Janela= {
            {"3","2016.05.24","9:00","Fechado","9:15","10:50"},
            {"1","2016.05.25","14:00","Aberto","",""},
            {"2","2016.05.25","16:00","Aberto","",""},
            {"0", "2016.05.24","11:00","Aberto","",""},
            {"4", "2016.05.24","14:00","Aberto","",""},
            {"5", "2016.05.15","10:30","Fechado","10:40","11:00"},
            {"6", "2016.05.15","15:00","Fechado","15:00","16:00"}

    };

    // Dados Utilizador
    public static String[][] Utilizador={
            {"0","Sandro","Carvalho","sandro","sandro.carvalho@fastcall.pt","Fastcall"},
            {"1","Zé","Teste","teste","ze.teste@fastcall.pt","Fieldservices"}
    };


    //      {OT,Material,Modelo,Serial,Estado}
    public static String[][] Material= {
            {"0","Router","B593S","123456789","Instalado"},
            {"5","Iad","PB6Plug","098765432","Stock"},
            {"3","Acta","MP4X","098765432","Tecnico"},
            {"0", "IAD","PB6Plug","57532108231","Instalado"},
            {"0","Router", "TG585","4824762984","Recolhido"}
    };

}
