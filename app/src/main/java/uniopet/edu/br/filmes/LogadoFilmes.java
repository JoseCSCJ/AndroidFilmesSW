package uniopet.edu.br.filmes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LogadoFilmes extends Activity {

    private FirebaseAuth mAuth;
    private TextView nomeUsuariologado,
            titulofilme1, idepisodio1, diretorfilme1,
            titulofilme2, idepisodio2, diretorfilme2,
            titulofilme3, idepisodio3, diretorfilme3,
            titulofilme4, idepisodio4, diretorfilme4,
            titulofilme5, idepisodio5, diretorfilme5
         /*   titulofilme6, idepisodio6, diretorfilme6,
            titulofilme7, idepisodio7, diretorfilme7*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telalogado);

        mAuth = FirebaseAuth.getInstance();
        nomeUsuariologado = findViewById(R.id.nomeUsuariologado);
        titulofilme1 = findViewById(R.id.titulofilme1);
        idepisodio1 = findViewById(R.id.idepisodio1);
        diretorfilme1 = findViewById(R.id.diretorfilme1);
        titulofilme2 = findViewById(R.id.titulofilme2);
        idepisodio2 = findViewById(R.id.idepisodio2);
        diretorfilme2 = findViewById(R.id.diretorfilme2);
        titulofilme3 = findViewById(R.id.titulofilme3);
        idepisodio3 = findViewById(R.id.idepisodio3);
        diretorfilme3 = findViewById(R.id.diretorfilme3);
        titulofilme4 = findViewById(R.id.titulofilme4);
        idepisodio4 = findViewById(R.id.idepisodio4);
        diretorfilme4 = findViewById(R.id.diretorfilme4);
        titulofilme5 = findViewById(R.id.titulofilme5);
        idepisodio5 = findViewById(R.id.idepisodio5);
        diretorfilme5 = findViewById(R.id.diretorfilme5);
       /* titulofilme5 = findViewById(R.id.titulofilme6);
        idepisodio5 = findViewById(R.id.idepisodio6);
        diretorfilme5 = findViewById(R.id.diretorfilme6);
        titulofilme5 = findViewById(R.id.titulofilme7);
        idepisodio5 = findViewById(R.id.idepisodio7);
        diretorfilme5 = findViewById(R.id.diretorfilme7);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://swapi.co/api/films/";

        nomeUsuariologado.setText("Bem vindo, "+currentUser.getEmail());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Filme filme = gson.fromJson(response,Filme.class);

                        String titulo = filme.results.get(0).getTitle();
                        Integer id = filme.results.get(0).getEpisode_id();
                        String diretor = filme.results.get(0).getDirector();

                        titulofilme1.setText("Título: "+titulo);
                        idepisodio1.setText("ID: "+String.valueOf(id));
                        diretorfilme1.setText("Diretor: "+diretor);

                        titulo = filme.results.get(1).getTitle();
                        id = filme.results.get(1).getEpisode_id();
                        diretor = filme.results.get(1).getDirector();

                        titulofilme2.setText("Título: "+titulo);
                        idepisodio2.setText("ID: "+String.valueOf(id));
                        diretorfilme2.setText("Diretor: "+diretor);

                        titulo = filme.results.get(2).getTitle();
                        id = filme.results.get(2).getEpisode_id();
                        diretor = filme.results.get(2).getDirector();

                        titulofilme3.setText("Título: "+titulo);
                        idepisodio3.setText("ID: "+String.valueOf(id));
                        diretorfilme3.setText("Diretor: "+diretor);

                        titulo = filme.results.get(3).getTitle();
                        id = filme.results.get(3).getEpisode_id();
                        diretor = filme.results.get(3).getDirector();

                        titulofilme4.setText("Título: "+titulo);
                        idepisodio4.setText("ID: "+String.valueOf(id));
                        diretorfilme4.setText("Diretor: "+diretor);

                        titulo = filme.results.get(4).getTitle();
                        id = filme.results.get(4).getEpisode_id();
                        diretor = filme.results.get(4).getDirector();

                        titulofilme5.setText("Título: "+titulo);
                        idepisodio5.setText("ID: "+String.valueOf(id));
                        diretorfilme5.setText("Diretor: "+diretor);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogadoFilmes.this, "Não foi possível carregar as informações", Toast.LENGTH_SHORT).show();
                        Log.e("Erro ao puxar informações da api", error.toString());
                    }
                });

        queue.add(stringRequest);
    }

    public void logout(View view) {
        mAuth.signOut();
        telaprincipal();
    }
    public void telaprincipal(){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
    }
}
