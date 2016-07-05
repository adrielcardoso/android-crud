package adrielcardoso.com.br.aulaphoto;

/**
 * Created by adriel on 14/06/16.
 */
public class RangoEntity {

    public String stDescricao;
    public String stTipo;
    public String stPonto;
    public String pathFoto;
    public String lon;
    public String lat;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public RangoEntity(){}
    public RangoEntity(String stDescricao, String stTipo, String stPonto, String pathFoto, String lat, String lon)
    {
        this.stDescricao = stDescricao;
        this.stTipo = stTipo;
        this.stPonto = stPonto;
        this.pathFoto = pathFoto;
        this.lat = lat;
        this.lon = lon;
    }

    public String getStDescricao() {
        return stDescricao;
    }

    public void setStDescricao(String stDescricao) {
        this.stDescricao = stDescricao;
    }

    public String getStTipo() {
        return stTipo;
    }

    public void setStTipo(String stTipo) {
        this.stTipo = stTipo;
    }

    public String getStPonto() {
        return stPonto;
    }

    public void setStPonto(String stPonto) {
        this.stPonto = stPonto;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }
}
