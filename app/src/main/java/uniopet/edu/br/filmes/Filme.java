package uniopet.edu.br.filmes;

import java.util.ArrayList;

class Filme {
    ArrayList<Results> results;

    public Filme(ArrayList<Results> results) {
        this.results = results;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
