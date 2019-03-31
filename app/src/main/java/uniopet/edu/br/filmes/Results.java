package uniopet.edu.br.filmes;

class Results {
    private String title;
    private Integer episode_id;
    private String director;

    public Results(String title, Integer episode_id, String director) {
        this.title = title;
        this.episode_id = episode_id;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(Integer episode_id) {
        this.episode_id = episode_id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
