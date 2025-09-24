package modelo;

public class Palabra {

    private int codigo_Palabra;
    private String palabra;
    private String pista_1;
    private String pista_2;
    private String pista_3;

    public Palabra() {
    }

    public Palabra(int codigo_Palabra, String palabra, String pista_1, String pista_2, String pista_3) {
        this.codigo_Palabra = codigo_Palabra;
        this.palabra = palabra;
        this.pista_1 = pista_1;
        this.pista_2 = pista_2;
        this.pista_3 = pista_3;
    }

    public int getCodigo_Palabra() {
        return codigo_Palabra;
    }

    public void setCodigo_Palabra(int codigo_Palabra) {
        this.codigo_Palabra = codigo_Palabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista_1() {
        return pista_1;
    }

    public void setPista_1(String pista_1) {
        this.pista_1 = pista_1;
    }

    public String getPista_2() {
        return pista_2;
    }

    public void setPista_2(String pista_2) {
        this.pista_2 = pista_2;
    }

    public String getPista_3() {
        return pista_3;
    }

    public void setPista_3(String pista_3) {
        this.pista_3 = pista_3;
    }
}