package es.uniovi.imovil.user.courses;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CourseDetailsActivity extends ActionBarActivity {
    public static final String DESCRIPTION = "es.uniovi.imovil.user.courses.DESCRIPTION";
    CourseDetailsFragment mfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_activity);




        Intent intent = getIntent();
        String description = intent.getStringExtra(DESCRIPTION);
        Log.i("Main",description);
        // Existe el contenedor del fragmento?
        if (findViewById(R.id.fragment_container) != null) {
            Log.i("Entro","Entro123123");
            // Si estamos restaurando desde un estado previo no hacemos nada
            if (savedInstanceState != null) {
                Log.i("Entro","Entro");
                return;
            }

            // Crear el fragmento pasándole el parámetro
            CourseDetailsFragment fragment =
                    CourseDetailsFragment.newInstance(description);

            // Añadir el fragmento al contenedor 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }


        /*
        TextView tv = (TextView)findViewById(R.id.descripcion);
        tv.setText(description);
        */
    }
}
