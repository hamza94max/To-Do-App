package com.hamza.todoapp.ui.Dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.view.isEmpty
import androidx.fragment.app.DialogFragment
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.R
import com.hamza.todoapp.Util.Priority
import java.util.*


class TaskDialog : DialogFragment() {


    interface OnInputListener {
        fun sendInput(input: Task)
    }

    lateinit var listener: OnInputListener
    private lateinit var priority: Priority

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.add_task_dialog, container, false)

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.add_task_dialog)

        val title = view.findViewById<EditText>(R.id.whatsTaskEditText)

        val date = view.findViewById<EditText>(R.id.dateOfTaskEditText)
        date.setOnClickListener {
            openDatePickerDialog(it, requireActivity())
        }

        val timeEditText = view.findViewById<EditText>(R.id.timeOfTaskEditText)
        timeEditText.setOnClickListener {
            showTimePicker(it, requireActivity())
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)


        val addBtn = view.findViewById<Button>(com.hamza.todoapp.R.id.addBtn)
        addBtn.setOnClickListener {
            if (title.text.isEmpty() || date.text.isEmpty() || timeEditText.text.isEmpty()
                || radioGroup.isEmpty()
            )
                Toast.makeText(activity, "Fill all views", Toast.LENGTH_LONG).show()
            else {
                when (radioGroup.checkedRadioButtonId) {
                    R.id.lowRadiobtn -> priority = Priority.LOW
                    R.id.mediumRadiobtn -> priority = Priority.MEDIUM
                    R.id.highRadiobtn -> priority = Priority.HIGH
                }
                val task = Task(
                    2,
                    title.text.toString(),
                    date.text.toString(),
                    timeEditText.text.toString(),
                    priority,
                    false
                )
                listener.sendInput(task)
                dialog!!.dismiss()
            }
        }


        val cancelBtn = view.findViewById<Button>(com.hamza.todoapp.R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.show()


        return view
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
            { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate =
                    dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                when (v.id) {
                    R.id.dateOfTaskEditText -> (v as EditText).setText(selectedDate)
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = cal.timeInMillis - 1
        datePickerDialog.show()
    }

    private fun showTimePicker(view: View, activity: Activity) {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            activity, { timePicker, selectedHour, selectedMinute ->
                val selectedTime = "$hour:$minute"
                when (view.id) {
                    R.id.timeOfTaskEditText -> (view as EditText).setText(selectedTime)
                }

            }, hour, minute, true
        )
        timePickerDialog.setTitle("Select Time Of task")
        timePickerDialog.show()
    }


}