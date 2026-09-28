package com.example.studentcourselist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentcourselist.dataclass.Course
import com.example.studentcourselist.dataclass.Student
import com.example.studentcourselist.ui.theme.StudentCourseListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val studentList = getStudetsList()

       var anotherList= studentList
            .flatMap { student -> student.list }
            .filter { course -> course.paidStatus == true }
            .groupingBy { it.courseName }
            .eachCount()
            .map { (coursename, count) ->
                CourseContent(coursename, count)
            }
            .sortedByDescending { it.count }
            .take(2)


        Log.e("DATA", anotherList.toString())


    }

    data class CourseContent(val courseName: String, val count: Int)

    fun getStudetsList(): List<Student> {
        var noorulCourse = listOf<Course>(
            Course(1, "Android", true),
            Course(2, "Java", true),
            Course(3, "Swift", true),
            Course(4, "Flutter", true)
        )
        var hassanCourse = listOf<Course>(
            Course(1, "Android", true),
            Course(2, "Java", true),
            Course(5, "Kotlin", true),
            Course(6, "React", true),
            Course(7, "Php", true)
        )
        var mohamedCourse = listOf<Course>(
            Course(7, "Php", true),
            Course(8, "Go", true),
            Course(9, "Python", true),
            Course(10, "JavaScript", true),
            Course(1, "Android", true)
        )

        var list = listOf<Student>(
            Student(1, "Noorul", noorulCourse),
            Student(2, "Hassan", hassanCourse),
            Student(3, "mohamed", mohamedCourse)
        )
        return list
    }

}