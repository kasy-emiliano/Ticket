

create database smmcfil;
create role smmcfil login password 'smmcfil';
alter database smmcfil owner to smmcfil;


create table attente(
    id serial primary key,
    numero int,
    date_attente date default now()
);



insert into attente(numero) values(1);
insert into attente(date_attente)values('05-10-2023');
insert into attente(numero) values(1);



CREATE SEQUENCE numero_sequence START 1;




CREATE OR REPLACE FUNCTION reset_numero() RETURNS TRIGGER AS $$
DECLARE
    dernier_numero integer;
BEGIN
    -- Recherchez le dernier numéro pour la date actuelle
    SELECT numero INTO dernier_numero
    FROM attente
    WHERE date_attente = NEW.date_attente
    ORDER BY numero DESC
    LIMIT 1;

    IF dernier_numero IS NULL THEN
        -- Aucune entrée pour la date actuelle, réinitialisez à 1
        NEW.numero = 1;
    ELSE
        -- Incrémentez le numéro
        NEW.numero = dernier_numero + 1;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reset_numero_trigger
    BEFORE INSERT ON attente
    FOR EACH ROW
    EXECUTE FUNCTION reset_numero();


SELECT * FROM attente WHERE numero IS NOT NULL ORDER BY id DESC LIMIT 1;

package com.example.filattente;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public Button btnExecuteCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExecuteCode = findViewById(R.id.btnExecuteCode);
        MyApiService apiService = NetworkManager.getApiService();
        ApiServiceGet apiServiceGet = NetworkManagerGet.GetApiServiceGet();
        btnExecuteCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Au clic du bouton, appelez le service REST pour déclencher la méthode insertAttente sur le backend
                Call<Void> call = apiService.insertAttente();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Gérez la réponse du serveur si nécessaire
                        if (response.isSuccessful()) {
                            Log.e("MainActivity", "Données inserer avec succès");
                        } else {
                            Log.e("MainActivity", "Données NON inserer ");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Gérez les erreurs de réseau
                    }
                });


            }

        });
    }


}
