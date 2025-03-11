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
                throw new IllegalStateException("Limite máximo da playlist atingido.");
            }
            if (!this.musicas.contains(musica)) {
                this.musicas.add(musica);
                System.out.println("Música "+ musica.getNome() + " adicionada na playlist " + this.getNome() + " com sucesso.");
                return;
            }
            System.out.println("Música "+ musica.getNome() + " já está na playlist " + this.getNome() + ".");
        }

        public void removeMusica(Musica musica) {
            if (this.musicas.isEmpty()) {
                throw new NoSuchElementException("Esta playlist está vazia.");
            }
            this.musicas.remove(musica);
            System.out.println("Música "+ musica.getNome() + " removida da playlist " + this.getNome() + " com sucesso.");
        }

        public void exibirPlaylist() {
            if (this.musicas.isEmpty()) {
                System.out.println("Playlist " + this.getNome() + " está vazia.");
                return;
            }
            System.out.println("---- Exibindo playlist: " + this.nome + " ----");
            for (Musica musica : this.musicas) {
                System.out.println("- " + musica.toString());

            }
        }

        public Musica buscarMusica(String nomeMusica) {
            for (Musica musica : this.musicas) {
                if (musica.getNome().equalsIgnoreCase(nomeMusica)) {
                    return musica;
                }
            }
            return null;
        }
    }
