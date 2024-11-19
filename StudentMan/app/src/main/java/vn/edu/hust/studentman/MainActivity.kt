package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
  private val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
  )

  private lateinit var studentAdapter: StudentAdapter
  private var lastDeletedStudent: StudentModel? = null
  private var lastDeletedPosition: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(students, ::onEditStudent, ::onDeleteStudent)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddStudentDialog()
    }
  }

  private fun showAddStudentDialog() {
    val dialogView = layoutInflater.inflate(R.layout.dialog_add_edit_student, null)
    val inputName = dialogView.findViewById<TextInputEditText>(R.id.input_student_name)
    val inputId = dialogView.findViewById<TextInputEditText>(R.id.input_student_id)

    AlertDialog.Builder(this)
      .setTitle("Add New Student")
      .setView(dialogView)
      .setPositiveButton("Add") { _, _ ->
        val name = inputName.text.toString().trim()
        val id = inputId.text.toString().trim()

        if (name.isNotEmpty() && id.isNotEmpty()) {
          students.add(StudentModel(name, id))
          studentAdapter.notifyItemInserted(students.size - 1)
        } else {
          Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun onEditStudent(position: Int) {
    val student = students[position]
    val dialogView = layoutInflater.inflate(R.layout.dialog_add_edit_student, null)
    val inputName = dialogView.findViewById<TextInputEditText>(R.id.input_student_name)
    val inputId = dialogView.findViewById<TextInputEditText>(R.id.input_student_id)

    inputName.setText(student.studentName)
    inputId.setText(student.studentId)

    AlertDialog.Builder(this)
      .setTitle("Edit Student")
      .setView(dialogView)
      .setPositiveButton("Save") { _, _ ->
        val name = inputName.text.toString().trim()
        val id = inputId.text.toString().trim()

        if (name.isNotEmpty() && id.isNotEmpty()) {
          students[position] = StudentModel(name, id)
          studentAdapter.notifyItemChanged(position)
        } else {
          Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun onDeleteStudent(position: Int) {
    val student = students[position]

    AlertDialog.Builder(this)
      .setTitle("Delete Student")
      .setMessage("Are you sure you want to delete ${student.studentName}?")
      .setPositiveButton("Yes") { _, _ ->
        lastDeletedStudent = student
        lastDeletedPosition = position

        students.removeAt(position)
        studentAdapter.notifyItemRemoved(position)

        Snackbar.make(findViewById(R.id.main), "${student.studentName} deleted", Snackbar.LENGTH_LONG)
          .setAction("Undo") {
            lastDeletedStudent?.let {
              students.add(lastDeletedPosition!!, it)
              studentAdapter.notifyItemInserted(lastDeletedPosition!!)
            }
          }
          .show()
      }
      .setNegativeButton("No", null)
      .show()
  }
}
