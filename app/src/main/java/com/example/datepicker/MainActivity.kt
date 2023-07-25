package com.example.datepicker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datepicker.ui.theme.DatePickerTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var pickedDate by remember {
                        mutableStateOf(LocalDate.now())
                    }
                    var pickedTime by remember {
                        mutableStateOf(LocalTime.now())
                    }
                    val formattime by remember {
                        derivedStateOf {
                            DateTimeFormatter.ofPattern("hh:mm").format(pickedTime)
                        }
                    }
                    val formatdate by remember {
                        derivedStateOf {
                            DateTimeFormatter.ofPattern("dd-mm-yy").format(pickedDate)
                        }

                    }


                    val dateDialogState = rememberMaterialDialogState()
                    val timeDialogState = rememberMaterialDialogState()
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


                        Button(onClick = { dateDialogState.show() }) {
                            Text(text = "Pick Date")

                        }
                        Text(text = formatdate)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { timeDialogState.show() }) {
                            Text(text = "Pick time")

                        }
                        Text(text = formattime)


                    }
                    MaterialDialog(dialogState = dateDialogState, buttons = {positiveButton(
                        text = "Birthday Select"
                    ){
                        Toast.makeText(applicationContext,"$formatdate is my birthday",Toast.LENGTH_LONG).show()
                    }

                        negativeButton(text= "give up")}) {
                        datepicker(initialDate = LocalDate.now(),
                            title = "Pick my birth date",
                            ){
                            pickedDate = it

                        }


                    }
                    MaterialDialog(dialogState = timeDialogState, buttons = {positiveButton(
                        text = "Just any time"
                    ){
                        Toast.makeText(applicationContext,"$formatdate is my birthday",Toast.LENGTH_LONG).show()
                    }

                        negativeButton(text= "cnacel")}) {
                        timepicker(initialTime = LocalTime.now(), title = "Pick a time"){
                            pickedTime =it
                        }




                    }


                }
            }
        }
    }
}

