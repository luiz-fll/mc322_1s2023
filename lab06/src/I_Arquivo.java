import java.io.IOException;

public interface I_Arquivo {
    public boolean gravarArquivo();
    public String lerArquivo(String caminho) throws IOException;
}
