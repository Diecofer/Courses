package es.uniovi.imovil.user.courses;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Course implements Parcelable , Serializable {
	
	private String mName;



	private String descripcion;
	private String mTeacher;
	
	public Course(String name, String teacher, String descripcion) {
		
		if (name == null || teacher == null || name.isEmpty() || teacher.isEmpty() || descripcion == null ){ // descripcion.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		mName = name;
		mTeacher = teacher;
		this.descripcion=descripcion;
	}

	public String getName() {
		
		return mName;
	}

	public String getTeacher() {
		
		return mTeacher;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Course(Parcel parcel) {

			readFromParcel(parcel);
		}

		@Override
		public int describeContents() {

			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

			dest.writeString(mName);
			dest.writeString(mTeacher);
			dest.writeString(descripcion);
		}

	private void readFromParcel(Parcel parcel) {

		mName = parcel.readString();
		mTeacher = parcel.readString();
		descripcion = parcel.readString();
	}

	public static final Parcelable.Creator<Course> CREATOR =
			new Parcelable.Creator<Course>() {

				public Course createFromParcel(Parcel in) {
					return new Course(in);
				}

				public Course[] newArray(int size) {
					return new Course[size];
				}
			};
}
