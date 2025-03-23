package musicPlayer;

import enums.Generos;
import models.Album;
import models.Artista;
import models.Musica;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Carregar {
    public static Catalogo carregarCatalog(String diretorio) throws IllegalArgumentException {
        Catalogo catalogo = Catalogo.getInstancia(); // Criar o catálogo vazio
        File pastaMusicas = new File(diretorio); // Ler o diretório passado

        // Verificar se o diretório existe
        if (!pastaMusicas.exists() || !pastaMusicas.isDirectory()){
            throw new IllegalArgumentException("O diretório não existe ou não é uma pasta.");
        }

        // Armazenar as músicas do diretório
        File[] musicas = pastaMusicas.listFiles((_, nome) -> nome.toLowerCase().endsWith(".wav"));
        if (musicas == null || musicas.length == 0){
            throw new IllegalArgumentException("Nenhum arquivo MP3 encontrado no diretório.");
        }

        String nomeMusica, nomeArtista, nomeAlbum, diretorioMusica;
        Generos generoMusical;
        int duracaoMusica;


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

                nomeArtista = partes[0];
                if (!artistas.containsKey(nomeArtista)) {
                    // Criar o artista, se ele ainda não existir
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

                nomeArtista = partes[0];
                nomeAlbum = partes[2];

                if (!albuns.containsKey(nomeAlbum)) {
                    int tamanhoAlbum = 0;
                    // Laço para verificar quantas músicas tem naquele álbum
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
                    // Criar o álbum, se ele ainda não existir
                    albuns.put(nomeAlbum, new Album(nomeAlbum, artistas.get(nomeArtista), tamanhoAlbum));
                    // Atribuir o álbum ao seu artista
                    artistas.get(nomeArtista).adicionarAlbum(albuns.get(nomeAlbum));
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

                nomeMusica = partes[1];
                nomeArtista = partes[0];
                nomeAlbum = partes[2];
                generoMusical = extrairGeneroMusical(partes[3]);
                diretorioMusica = musica.getAbsolutePath();
                duracaoMusica = audioFile.getAudioHeader().getTrackLength();

                // Adicionar a música no Catálogo
                Musica novaMusica = new Musica(nomeMusica, artistas.get(nomeArtista), albuns.get(nomeAlbum), generoMusical, diretorioMusica, duracaoMusica);
                catalogo.adicionarMusica(novaMusica);
                // Adicionar a música no álbum que ela pertence
                for (Album album : artistas.get(nomeArtista).getAlbuns()) {
                    if (album.getNome().equals(nomeAlbum)) {
                        album.adicionarMusica(novaMusica);
                    }
                }
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
