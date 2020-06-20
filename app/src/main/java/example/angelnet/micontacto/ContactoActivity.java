package example.angelnet.micontacto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    EditText etNombreE,etApellidoE, etTelefonoE,etNumCelE, etEmailE,etDireccionE;
    DaoContacto daocontacto;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        etNombreE = (EditText) findViewById(R.id.etNombreE);
        etApellidoE = (EditText) findViewById(R.id.etApellidoE);
        etTelefonoE = (EditText) findViewById(R.id.etTelefonoE);
        etNumCelE = (EditText) findViewById(R.id.etNumCelE);
        etEmailE = (EditText) findViewById(R.id.etEmailE);
        etDireccionE = (EditText) findViewById(R.id.etDireccionE);
        daocontacto = new DaoContacto(this);

        if (bundle != null) {
            contacto = new Contacto(bundle.get("email").toString(),
                    bundle.get("phone").toString(),
                    bundle.get("nombre").toString(),
                    Integer.parseInt(bundle.get("id").toString()));
            etNombreE.setText(contacto.getNombre());
            etApellidoE.setText(contacto.getApellido());
            etTelefonoE.setText(contacto.getTelefono());
            etNumCelE.setText(contacto.getNumcel());
            etEmailE.setText(contacto.getEmail());
            etDireccionE.setText(contacto.getDireccion());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guardar:
                if (!etNombreE.getText().toString().equals("") &&
                        !etApellidoE.getText().toString().equals("") &&
                        !etTelefonoE.getText().toString().equals("") &&
                        !etNumCelE.getText().toString().equals("") &&
                        !etEmailE.getText().toString().equals("")&&
                        !etDireccionE.getText().toString().equals("")
                        ) {

                    if (etEmailE.getText().toString().matches(PATTERN_EMAIL)) {

                        daocontacto.updateEntry(new Contacto(etDireccionE.getText().toString()
                                ,etEmailE.getText().toString()
                                ,etNumCelE.getText().toString()
                                , etTelefonoE.getText().toString()
                                , etNombreE.getText().toString()
                                , etApellidoE.getText().toString()
                                , contacto.getId()));
                        Intent i = new Intent(ContactoActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast.makeText(ContactoActivity.this,"Editado Correctamente",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ContactoActivity.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ContactoActivity.this, "Ingreso los datos requeridos", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}