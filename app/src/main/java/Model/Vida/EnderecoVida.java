package Model.Vida;

import com.orm.SugarRecord;

import Enums.IluminacaoArtificial;
import Enums.TipoLocal;
import Enums.Vida.TipoVegetacao;
import Model.Endereco;

/**
 * Created by Pefoce on 15/11/2017.
 */

public class EnderecoVida extends SugarRecord<EnderecoVida>
{
   private Endereco endereco;

   private TipoLocal tipoLocal;

   private String latitude;

   private String longitude;

   private String pathAudio;

   private float temperatura;

   private IluminacaoArtificial tipoIluminacao;

   private TipoVegetacao tipoVegetacao;

   private boolean localAberto;
}
