package musicPlayer;

import models.Musica;
import models.Album;
import models.Artista;
import enums.Generos;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    public static Catalogo carregarCatalogo(String diretorio) {
        Catalogo catalogo = new Catalogo();
        File pasta = new File(diretorio);

        // Verifica se o diretório existe e é uma pasta
        if (!pasta.exists() || !pasta.isDirectory()) {
            throw new IllegalArgumentException("O diretório especificado não existe ou não é uma pasta.");
        }

        // Mapa para armazenar artistas e álbuns já criados
        Map<String, Artista> artistas = new HashMap<>();
        Map<String, Album> albuns = new HashMap<>();

        // Lista todos os arquivos MP3 no diretório
        File[] arquivosMP3 = pasta.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".mp3"));

        if (arquivosMP3 != null) {
            for (File arquivoMP3 : arquivosMP3) {
                try {
                    // Extrai metadados do arquivo MP3
                    AudioFile audioFile = AudioFileIO.read(arquivoMP3);
                    Tag tag = audioFile.getTag();

                    String nomeMusica = tag.getFirst(FieldKey.TITLE);
                    String artistaNome = tag.getFirst(FieldKey.ARTIST);
                    String albumNome = tag.getFirst(FieldKey.ALBUM);
                    int genero = Integer.parseInt(tag.getFirst(FieldKey.GENRE));
                    int duracaoSegundos = audioFile.getAudioHeader().getTrackLength();

                    // Converte o gênero para o enum Generos
                    Generos generoMusical = Generos.values()[genero];

                    // Verifica se o artista já existe, caso contrário, cria um novo
                    Artista artista = artistas.get(artistaNome);
                    if (artista == null) {
                        artista = new Artista(artistaNome);
                        artistas.put(artistaNome, artista);
                    }

                    // Verifica se o álbum já existe, caso contrário, cria um novo
                    Album album = albuns.get(albumNome);
                    if (album == null) {
                        album = new Album(albumNome, artistaNome, 20); // Tamanho do álbum pode ser ajustado
                        albuns.put(albumNome, album);
                        artista.adicionarAlbum(album); // Associa o álbum ao artista
                    }

                    // Cria uma instância de Musica
                    Musica musica = new Musica(
                            nomeMusica,
                            artistaNome,
                            albumNome,
                            generoMusical,
                            arquivoMP3.getAbsolutePath(),
                            duracaoSegundos
                    );

                    // Adiciona a música ao álbum
                    album.adicionarMusica(musica);

                    // Adiciona a música ao catálogo
                    catalogo.adicionarMusica(musica);

                } catch (Exception e) {
                    System.out.println("Erro ao processar o arquivo: " + arquivoMP3.getName());
                    e.printStackTrace();
                }
            }
        }

        return catalogo;
    }
}