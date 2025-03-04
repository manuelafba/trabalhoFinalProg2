    package models;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.NoSuchElementException;

    public class Playlist {
        private static final int MAX_MUSICAS = 50;
        private String nome;
        private List<Musica> listaMusicas;

        public Playlist(String nome) {
            this.nome = nome;
            this.listaMusicas = new ArrayList<>();
        }

        public String getNome() {
            return nome;
        }

        public List<Musica> getListaMusicas() {
            return listaMusicas;
        }

        public void adicionaMusica(Musica musica) {
            if (listaMusicas.size() >= MAX_MUSICAS) {
                throw new IllegalStateException("Limite máximo da playlist atingido");
            }
            listaMusicas.add(musica);
        }

        public void removeMusica(Musica musica) {
            if (listaMusicas.isEmpty()) {
                throw new NoSuchElementException("Esta playlist está vazia");
            }
            listaMusicas.remove(musica);
        }

        public void exibirPlaylist() {
            System.out.println("Exibindo playlist: " + this.nome);
            for (Musica musica : listaMusicas) {
                System.out.println(musica.toString());
            }
        }
    }
