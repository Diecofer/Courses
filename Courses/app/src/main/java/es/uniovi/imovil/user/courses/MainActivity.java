package es.uniovi.imovil.user.courses;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements CourseListFragment.Callbacks {

	private CourseAdapter mAdapter = null;
	private int mCourseCount = 0;
	boolean mTwoPanes = false;



		@Override
		public void onCourseSelected(Course course) {
			if (mTwoPanes){
				TextView tv = (TextView) findViewById(R.id.descripcion);
				tv.setText(course.getDescripcion());

			}
			else {
				Intent intent = new Intent(this, CourseDetailsActivity.class);
				intent.putExtra(CourseDetailsActivity.DESCRIPTION, course.getDescripcion());
				startActivity(intent);
				Log.i("Main", course.getDescripcion());
			}
		}

/*
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		// Arrancar la actividad para mostrar los detalles
		Intent intent = new Intent(this, CourseDetailsActivity.class);
		Course course = (Course) parent.getItemAtPosition(position);
		intent.putExtra(CourseDetailsActivity.DESCRIPTION, course.getDescripcion());
		startActivity(intent);

	}
*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (findViewById(R.id.course_details_container) != null) {
			mTwoPanes = true;
		}
		/*
		// Configurar la lista
		ListView lvItems = (ListView) findViewById(R.id.list_view_courses);
		String [] courses = getResources().getStringArray(R.array.courses);
		String [] teachers = getResources().getStringArray(R.array.teachers);
		String [] descripciones = getResources().getStringArray(R.array.descripcion);
		mAdapter = new CourseAdapter(this, createCourseList(courses, teachers,descripciones));
		lvItems.setAdapter(mAdapter);
		ListView lv = (ListView)findViewById(R.id.list_view_courses);
		lv.setOnItemClickListener(this);
		*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflar el men� y a�adir acciones al action bar si existe
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.action_add_course:
				FragmentManager fragmentManager = getSupportFragmentManager();
				CourseListFragment fragment = (CourseListFragment)
						fragmentManager.findFragmentById(R.id.course_list_frag);

				String name = String.format(getString(R.string.default_course_format), ++mCourseCount);
				String teacher = String.format(getString(R.string.default_teacher_format),  mCourseCount);
				String description = "";

				Course course = new Course(name, teacher, description);
				fragment.addCourse(course);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	




}
