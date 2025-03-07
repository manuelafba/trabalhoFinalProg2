    package models;

    import java.util.ArrayList;
    import java.util.NoSuchElementException;

    public class Playlist {
        private static final int MAX_MUSICAS = 50;
        private String nome;
        private ArrayList<Musica> musicas;

        public Playlist(String nome) {
            this.nome = nome;
            this.musicas = new ArrayList<Musica>();
        }

        public String getNome() {
            return this.nome;
        }

        public ArrayList<Musica> getMusicas() {
            return this.musicas;
        }

        public void adicionaMusica(Musica musica) {
            if (this.musicas.size() >= MAX_MUSICAS) {
                throw new IllegalStateException("Limite máximo da playlist atingido");
            }
            this.musicas.add(musica);
        }

        public void removeMusica(Musica musica) {
            if (this.musicas.isEmpty()) {
                throw new NoSuchElementException("Esta playlist está vazia");
            }
            this.musicas.remove(musica);
        }

        public void exibirPlaylist() {
            System.out.println("Exibindo playlist: " + this.nome);
            for (Musica musica : this.musicas) {
                System.out.println(musica.toString());
            }
        }
    }
