package es.uniovi.imovil.user.courses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Diecofer on 01/03/2017.
 */

public class CourseDetailsFragment  extends Fragment {

    private static final String DESCRIPTION_ARG = "description";

    public static CourseDetailsFragment newInstance(String desc) {

        CourseDetailsFragment fragment = new CourseDetailsFragment();

        Bundle args = new Bundle();
        args.putString(DESCRIPTION_ARG, desc);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        rootView = inflater.inflate(R.layout.course_details_fragment,
                container, false);


        Bundle args = getArguments();
        if (args != null) {
            String desc = args.getString(DESCRIPTION_ARG);
            Log.i("desc",desc);
            TextView tv = (TextView)rootView.findViewById(R.id.descripcion);
            tv.setText(desc);
        }
        return rootView;
    }

}
