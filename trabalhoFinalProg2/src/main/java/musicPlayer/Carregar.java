package musicPlayer;

import enums.Generos;
import models.Album;
import models.Artista;
import models.Musica;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Carregar {
    public static Catalogo carregarCatalog(String diretorio){
        Catalogo catalogo = Catalogo.getInstance(); // Criar o catálogo vazio
        File pastaMusicas = new File(diretorio); // Ler o diretório passado

        // Verificar se o diretório existe
        if (!pastaMusicas.exists() || !pastaMusicas.isDirectory()){
            throw new IllegalArgumentException("O diretório não existe ou não é uma pasta.");
        }

        // Armazenar as músicas do diretório
        File[] musicas = pastaMusicas.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".wav"));
        if (musicas == null || musicas.length == 0){
            throw new IllegalArgumentException("Nenhum arquivo MP3 encontrado no diretório.");
        }



        // Mapear e criar artistas
        Map<String, Artista> artistas = new HashMap<>();
        for (File musica : musicas) {
            try {
                String nomeArquivo = musica.getName();
                nomeArquivo = nomeArquivo.replace(".wav", "");
                String[] partes = nomeArquivo.split("-");
                // Verifica se o nome do arquivo está no formato correto
                if (partes.length != 4) {
                    throw new IllegalArgumentException("Formato do nome do arquivo inválido. Esperado: ARTISTA-NOME-ALBUM-GENERO.wav");
                }

                String nomeArtista = partes[0];
                if (!artistas.containsKey(nomeArtista)) {
                    artistas.put(nomeArtista, new Artista(nomeArtista));
                }

            } catch (Exception e) {
                System.err.println("Erro ao processar artista: " + musica.getName());
                e.printStackTrace();
            }
        }

        // Mapear e criar albuns
        Map<String, Album> albuns = new HashMap<>();
        for (File musica : musicas) {
            try {
                String nomeArquivo = musica.getName();
                nomeArquivo = nomeArquivo.replace(".wav", "");
                String[] partes = nomeArquivo.split("-");
                // Verifica se o nome do arquivo está no formato correto
                if (partes.length != 4) {
                    throw new IllegalArgumentException("Formato do nome do arquivo inválido. Esperado: ARTISTA-NOME-ALBUM-GENERO.wav");
                }

                String nomeArtista = partes[0];
                String nomeAlbum = partes[2];

                if (!albuns.containsKey(nomeAlbum)) {
                    int tamanhoAlbum = 0;
                    for (File mp3 : musicas){
                        String nomeArquivoMP3 = mp3.getName();
                        nomeArquivoMP3 = nomeArquivoMP3.replace(".wav", "");
                        String[] partesArquivoMP3 = nomeArquivoMP3.split("-");
                        // Verifica se o nome do arquivo está no formato correto
                        if (partesArquivoMP3.length != 4) {
                            throw new IllegalArgumentException("Formato do nome do arquivo inválido. Esperado: ARTISTA-NOME-ALBUM-GENERO.wav");
                        }
                        String nomeAlbumMP3 = partesArquivoMP3[2];
                        if (nomeAlbumMP3.equals(nomeAlbum)){ tamanhoAlbum++;}
                    }
                    albuns.put(nomeAlbum, new Album(nomeAlbum, artistas.get(nomeArtista), tamanhoAlbum));
                }

            } catch (Exception e) {
                System.err.println("Erro ao processar álbum: " + musica.getName());
                e.printStackTrace();
            }
        }
        
        // Criar músicas e adicionar ao Catálogo
        for (File musica : musicas) {
            try {
                String nomeArquivo = musica.getName();
                nomeArquivo = nomeArquivo.replace(".wav", "");
                String[] partes = nomeArquivo.split("-");
                // Verifica se o nome do arquivo está no formato correto
                if (partes.length != 4) {
                    throw new IllegalArgumentException("Formato do nome do arquivo inválido. Esperado: ARTISTA-NOME-ALBUM-GENERO.wav");
                }
                AudioFile audioFile = AudioFileIO.read(musica);

                String nomeMusica = partes[1];
                String nomeArtista = partes[0];
                String nomeAlbum = partes[2];
                Generos generoMusical = extrairGeneroMusical(partes[3]);
                String diretorioMusica = musica.getAbsolutePath();
                int duracaoMusica = audioFile.getAudioHeader().getTrackLength();

                catalogo.adicionarMusica(new Musica(nomeMusica, artistas.get(nomeArtista), albuns.get(nomeAlbum), generoMusical, diretorioMusica, duracaoMusica));

            } catch (Exception e) {
                System.err.println("Erro ao processar música: " + musica.getName());
                e.printStackTrace();
            }
        }
        return catalogo;
    }

    private static Generos extrairGeneroMusical(String indiceGenero){
        if (indiceGenero == null){ return Generos.DESCONEHCIDO; }
        try {
            return Generos.values()[Integer.parseInt(indiceGenero)];
        } catch (IllegalArgumentException e){
            return Generos.DESCONEHCIDO;
        }
    }
}
