package es.uniovi.imovil.user.courses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseListFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    private CourseAdapter mAdapter = null;
    private int mCourseCount = 0;
    Callbacks mCallback;
    private ArrayList<Course> listacursos;
    public static final String LISTACURSOS = "ListaBundle";
    public static final String CONTADORCURSO = "ContadorCursos";
    public static final String COURSE_LIST_FILENAME ="FILENAME";

    public interface Callbacks {
        public void onCourseSelected(Course course);
    }

    public static CourseListFragment newInstance() {

        CourseListFragment fragment = new CourseListFragment();
        return fragment;
    }

    public CourseListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            mCallback = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement Callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        rootView = inflater.inflate(R.layout.course_list_fragment,
                container, false);

        // Inicializar el layout
        ListView lvItems = (ListView) rootView.findViewById(R.id.list_view_courses);

        if (savedInstanceState != null) {

            // Obtener la lista del bundle con getParcelableArrayList()
            listacursos =  savedInstanceState.getParcelableArrayList(LISTACURSOS);
            mCourseCount = savedInstanceState.getInt(CONTADORCURSO);
        } else {

                // Lista por defecto
                String [] courses = getResources().getStringArray(R.array.courses);
                String [] teachers = getResources().getStringArray(R.array.teachers);
                String [] descripciones = getResources().getStringArray(R.array.descripcion);
                listacursos = createCourseList(courses,teachers,descripciones);


        }


        mAdapter = new CourseAdapter(this.getActivity(),listacursos);
        lvItems.setAdapter(mAdapter);
        ListView lv = (ListView)rootView.findViewById(R.id.list_view_courses);
        lv.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LISTACURSOS,listacursos);
        outState.putInt(CONTADORCURSO,mCourseCount);
    }

    private ArrayList<Course> createCourseList(String[] names, String[] teachers,
                                               String[] descriptions) {

        if (names.length != teachers.length) {
            throw new IllegalStateException();
        }

        ArrayList<Course> courses = new ArrayList<Course>(names.length);
        for (int i = 0; i < names.length; i++) {
            courses.add(new Course(names[i], teachers[i],descriptions[i]));
        }
        return courses;
    }

    public void addCourse(Course course) {

        String name = String.format(getString(R.string.default_course_format), ++mCourseCount);
        String teacher = String.format(getString(R.string.default_teacher_format), mCourseCount);
        String descripcion = String.format("Descripcion %d" , mCourseCount);
        course = new Course(name, teacher,descripcion);

        listacursos.add(course);
       // mAdapter.addCourse(course);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // Arrancar la actividad para mostrar los detalles
        Intent intent = new Intent(this.getActivity(), CourseDetailsActivity.class);
        Course course = (Course) parent.getItemAtPosition(position);
        intent.putExtra(CourseDetailsActivity.DESCRIPTION, course.getDescripcion());
      //  startActivity(intent);
        mCallback.onCourseSelected(course);
    }







}