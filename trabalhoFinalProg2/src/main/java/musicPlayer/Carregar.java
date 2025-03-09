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

        AudioFile audioFile;
        Tag tag;

        // Mapear e criar artistas
        Map<String, Artista> artistas = new HashMap<>();
        for (File musica : musicas) {
            try {
                audioFile = AudioFileIO.read(musica);
                tag = audioFile.getTag();

                String nomeArtista = tag != null ? tag.getFirst(FieldKey.ARTIST) : "Artista desconhecido";
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
                audioFile = AudioFileIO.read(musica);
                tag = audioFile.getTag();

                String nomeArtista = tag != null ? tag.getFirst(FieldKey.ARTIST) : "Artista desconhecido";
                String nomeAlbum = tag != null ? tag.getFirst(FieldKey.ALBUM) : "Álbum desconhecido";

                if (!albuns.containsKey(nomeAlbum)) {
                    int tamanhoAlbum = 0;
                    for (File mp3 : musicas){
                        audioFile = AudioFileIO.read(mp3);
                        tag = audioFile.getTag();
                        String nomeAlbumMP3 = tag != null ? tag.getFirst(FieldKey.ALBUM) : "Álbum desconhecido";
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
                audioFile = AudioFileIO.read(musica);
                tag = audioFile.getTag();

                String nomeMusica = tag != null ? tag.getFirst(FieldKey.TITLE) : "Nome desconhecido";
                String nomeArtista = tag != null ? tag.getFirst(FieldKey.ARTIST) : "Artista desconhecido";
                String nomeAlbum = tag != null ? tag.getFirst(FieldKey.ALBUM) : "Álbum desconhecido";
                Generos generoMusical = extrairGeneroMusical(tag);
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

    private static Generos extrairGeneroMusical(Tag tag){
        if (tag == null){ return Generos.DESCONEHCIDO; }
        try {
            return Generos.values()[Integer.parseInt(tag.getFirst(FieldKey.GENRE))];
        } catch (IllegalArgumentException e){
            return Generos.DESCONEHCIDO;
        }
    }
}
