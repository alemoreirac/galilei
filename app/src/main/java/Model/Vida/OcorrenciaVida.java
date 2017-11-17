package Model.Vida;
import com.orm.SugarRecord;
import java.util.Date;
import Enums.AreaIntegradaSeguranca;
import Enums.PreservacaoLocal;
import Enums.Vida.TipoOcorrenciaVida;
import Model.DocumentoOcorrencia;

/**
 * Created by Pefoce on 15/11/2017.
 */

public class OcorrenciaVida extends SugarRecord<OcorrenciaVida>
{
    private Long ocorrenciaID;

    private Date dataOcorrencia;

    private Date dataAtendimento;

    private TipoOcorrenciaVida tipoOcorrenciaVida;

    private DocumentoOcorrencia documento;

    private String numIncidencia;

    private String Viatura;

    private String Comandante;

    private AreaIntegradaSeguranca ais;

    private PreservacaoLocal preservacaoLocal;

}
