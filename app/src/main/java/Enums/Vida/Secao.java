package Enums.Vida;

/**
 * Created by Pefoce on 20/11/2017.
 */

public enum Secao
{
    SETOR_SUPERIOR_BRACO_DIREITO("Superior do braço direito"),//0
    SETOR_INFERIOR_BRACO_DIREITO("Inferior do braço direito"),//1
    COTOVELO_DIREITO("Cotovelo direito"),//2
    SETOR_SUPERIOR_ANTEBRACO_DIREITO("Superior do antebraço direito"),//3
    SETOR_INFERIOR_ANTEBRACO_DIREITO("Inferior do antebraço direito"),//4
    MAO_DIREITA("Mão direita"),//5
//0
    SETOR_SUPERIOR_BRACO_ESQUERDO("Superior do braço esquerdo"),//6
    SETOR_INFERIOR_BRACO_ESQUERDO("Inferior do braço esquerdo"),//7
    COTOVELO_ESQUERDO("Cotovelo esquerdo"),//8
    SETOR_SUPERIOR_ANTEBRACO_ESQUERDO("Superior do antebraço esquerdo"),//9
    SETOR_INFERIOR_ANTEBRACO_ESQUERDO("Inferior do antebraço esquerdo"),//10
    MAO_ESQUERDA("Mão esquerda"),//11
//0
    SETOR_SUPERIOR_COXA_DIREITA("Superior da coxa direita"),//12
    SETOR_INFERIOR_COXA_DIREITA("Inferior da coxa direita"),//13
    JOELHO_DIREITO("Joelho direito"),//14
    SETOR_SUPERIOR_PERNA_DIREITA("Superior da perna direita"),//15
    SETOR_INFERIOR_PERNA_DIREITA("Inferior da perna direita"),//16
    PE_DIREITO("Pé direito"),//17
//0
    SETOR_SUPERIOR_COXA_ESQUERDA("Superior da coxa esquerda"),//18
    SETOR_INFERIOR_COXA_ESQUERDA("inferior da coxa esquerda"),//19
    JOELHO_ESQUERDO("Joelho esquerdo"),//20
    SETOR_SUPERIOR_PERNA_ESQUERDA("Superior da perna esquerda"),//21
    SETOR_INFERIOR_PERNA_ESQUERDA("Inferior da perna esquerda"),//22
    PE_ESQUERDO("Pé esquerdo"),//23
//0
    CLAVICULAR_ANTERIOR_DIREITO("Clavicular anterior direito"),//24
    PEITORAL_DIREITO("Peitoral direito"),//25
    HIPOCONDRIO_DIREITO("Hipocôndrio direito"),//26
    FLANCO_DIREITO("Flanco direito"),//27
    ILIACO_ANTERIOR_DIREITO("Ilíaco anterior direito"),//28
//0
    CLAVICULAR_ANTERIOR_ESQUERDO("Clavicular anterior esquerdo"),//29
    PEITORAL_ESQUERDO("Peitoral esquerdo"),//30
    HIPOCONDRIO_ESQUERDO("Hipocôndrio esquerdo"),//31
    FLANCO_ESQUERDO("Flanco esquerdo"),//32
    ILIACO_ANTERIOR_ESQUERDO("Ilíaco anterior esquerdo"),//33
//0
    ESCAPULAR_DIREITO("Escapular direito"),//34
    TORACICO_DIREITO("Torácico direito"),//35
    LOMBAR_DIREITO("Lombar direita"),//36
    ILIACO_POSTERIOR_DIREITO("Ilíaco posterior direito"),//37
    GLUTEO_DIREITO("Gluteo direito"),//38
//0
    ESCAPULAR_ESQUERDO("Escapular esquerdo"),//039
    TORACICO_ESQUERDO("Torácico esquerdo"),//040
    LOMBAR_ESQUERDO("Lombar esquerda"),//041
    ILIACO_POSTERIOR_ESQUERDO("Ilíaco posterior esquerdo"),//042
    GLUTEO_ESQUERDO("Gluteo esquerdo"),//043

    PARIETAL_DIREITA("Parietal direito"),//044
    AURICULAR_DIREITA("Auricular direito"),//045
    FRONTAL_DIREITA("Frontal direito"),//046
    OCULAR_DIREITA("Ocular direito"),//047
    MALAR_DIREITA("Malar direita"),//048
    NASAL_DIREITA("Nasal direito"),//049
    BUCAL_DIREITA("Bucal direito"),//050
    MENTONIANA_DIREITA("Mentoniana direita"),//051
    CAROTIDIANA_DIREITA("Carotidiana direito"),//052
    CERVICAL_DIREITA("Cervical direito"),//053
    OCCIPITAL_DIREITA("Occipital direito"),//054

    PARIETAL_ESQUERDA("Parietal esquerdo"),//055
    AURICULAR_ESQUERDA("Auricular esquerdo"),//056
    FRONTAL_ESQUERDA("Frontal esquerdo"),//057
    OCULAR_ESQUERDA("Ocular esquerdo"),//058
    MALAR_ESQUERDA("Malar esquerdo"),//059
    NASAL_ESQUERDA("Nasal esquerdo"),//060
    BUCAL_ESQUERDA("Bucal esquerdo"),//061
    MENTONIANA_ESQUERDA("Mentoniana esquerdo"),//062
    CAROTIDIANA_ESQUERDA("Carotidiana esquerda"),//063
    CERVICAL_ESQUERDA("Cervical esquerdo"),//064
    OCCIPITAL_ESQUERDA("Occipital esquerdo"),//065

    GENITAL("Genital"),//066
    ANUS("Ânus");//067

    String valor;


    public String getValor()
    {
        return valor;
    }


    Secao(String s)
    {
        this.valor = s;

    }


}
