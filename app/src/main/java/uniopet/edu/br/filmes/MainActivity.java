package uniopet.edu.br.filmes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    private EditText username, password, password2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
            telalogado();
        }else{
            Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
        }

    }
    public void login(View view) {
        String login = username.getText().toString();
        String senha = password.getText().toString();
        mAuth.signInWithEmailAndPassword(login, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                telalogado();
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
        String login = username.getText().toString();
        String senha = password.getText().toString();
        String confirmaSenha = password2.getText().toString();
        if(senha.length() >= 8){
            if(senha.equals(confirmaSenha)){
                mAuth.createUserWithEmailAndPassword(login, senha).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        telalogado();
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(this, "As senhas não conferem", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "A senha precisa ter no mínimo 8 caracteres", Toast.LENGTH_LONG).show();
        }
    }

    public void telalogado(){
        Intent logado = new Intent(this, LogadoFilmes.class);
        startActivity(logado);
    }
}
