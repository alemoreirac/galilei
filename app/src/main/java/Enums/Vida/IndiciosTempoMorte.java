package Enums.Vida;

/**
 * Created by Pefoce on 27/06/2018.
 */

public enum IndiciosTempoMorte
{
    FLACIDO_QUENTE_SEM_LIVORES("Corpo flácido, quente e ausencia de livores (menos de 2h)"),
    RIGIDEZ_INICIO_LIVORES("Rigidez da nuca e mandibula e esboço de livores (2h-4h)"),
    RIGIDEZ_MEDIO_LIVORES("Rigidez dos membros anteriores, nuca, mandibula e livores relativamente acentuados (4h-6h)"),
    RIGIDEZ_GENERALIZADA("Rigidez generalizada, manchas de hipostase (8h-36h)"),
    FLACIDEZ_INICIO_PUTREFACAO("Inicio de flacidez e da putrefação - manchas cadavéricas (24h)"),
    FLACIDEZ_PUTREFACAO("Flacidez generalizada, putrefação - enfisematosa e inicio de coliquaçao (48h)"),
    COLIQUACAO("Coliquação manifesta (72h)"),
    DESAPARECIMENTO_PARTES_MOLES("Desaparecimento das partes moles (2 a 3 anos)"),
    ESQUELETIZACAO("Esqueletizaçao completa (3+ anos)");

    String valor;

    IndiciosTempoMorte(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}

