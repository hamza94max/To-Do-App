package com.hamza.todoapp.ui.addTaskDialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.DialogFragment
import com.hamza.todoapp.R
import com.hamza.todoapp.databinding.AddTaskDialogBinding
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.domain.models.TaskPriority
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskDialog @Inject constructor() : DialogFragment() {

    private lateinit var listener: OnInputListener
    private lateinit var priority: TaskPriority

    private var _binding: AddTaskDialogBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddTaskDialogBinding.inflate(LayoutInflater.from(context))

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.add_task_dialog)

        binding.dateOfTaskEditText.setOnClickListener {
            openDatePickerDialog(it, requireActivity())
        }

        binding.timeOfTaskEditText.setOnClickListener {
            showTimePicker(it, requireActivity())
        }

        binding.addBtn.setOnClickListener {
            if (isDialogEmpty())
                Toast.makeText(activity, "Fill all fields", Toast.LENGTH_LONG).show()
            else {
                when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.lowRadiobtn -> priority = TaskPriority.LOW
                    R.id.mediumRadiobtn -> priority = TaskPriority.MEDIUM
                    R.id.highRadiobtn -> priority = TaskPriority.HIGH
                }
                val task = Task(
                    title = binding.titleOfTaskEditText.text.toString(),
                    date = binding.dateOfTaskEditText.text.toString(),
                    time = binding.timeOfTaskEditText.text.toString(),
                    priority = priority,
                    isReminder = binding.reminderSwitch.isChecked,
                    isCompleted = false
                )
                listener.onTaskAdded(task)
                dialog!!.dismiss()
            }
        }

        binding.cancelBtn.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.show()

        return binding.root
    }

    private fun isDialogEmpty(): Boolean {
        return binding.titleOfTaskEditText.text.isEmpty() ||
                binding.dateOfTaskEditText.text.isEmpty() ||
                binding.timeOfTaskEditText.text.isEmpty() ||
                binding.radioGroup.isEmpty()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as OnInputListener
        } catch (e: ClassCastException) {
        }
    }

    private fun openDatePickerDialog(v: View, activity: Activity) {
        val cal: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            activity,
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, monthOfYear)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                when (v.id) {
                    R.id.dateOfTaskEditText -> (v as EditText).setText(formattedDate)
                }
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = cal.timeInMillis - 1
        datePickerDialog.show()
    }


    private fun showTimePicker(view: View, activity: Activity) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime[Calendar.HOUR_OF_DAY]
        val minute = currentTime[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            activity, { timePicker, selectedHour, selectedMinute ->
                val selectedTime = "$selectedHour:$selectedMinute"
                when (view.id) {
                    R.id.timeOfTaskEditText -> (view as EditText).setText(selectedTime)
                }
            }, hour, minute, true
        )
        timePickerDialog.setTitle("Select Time Of task")
        timePickerDialog.show()
    }
}
