package com.codecoffee.mvvm.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codecoffee.mvvm.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel){

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),


    ){
        login(Modifier.align(Alignment.Center),viewModel);
    }

}
@Composable
fun login(modifier: Modifier,viewModel: LoginViewModel){
    val email:String by viewModel.email.observeAsState(initial = "")
    val password:String by viewModel.password.observeAsState(initial = "")
    val loginEnabled:Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val isLoading:Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScape= rememberCoroutineScope()
    if (isLoading){
       Box(modifier = Modifier.fillMaxSize()){
           CircularProgressIndicator(modifier=Modifier.align(alignment=Alignment.Center))
       }

    }else{
        Column(modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(16.dp))
            ParwordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            ForgoutPassword(Modifier.align(
                Alignment.End
            ))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnabled){
                coroutineScape.launch{
                    viewModel.onLoginSelected()
                }
              }
        }
    }

}

@Composable
fun EmailField(email:String,onchangeField: (String)->Unit){
    TextField(value = email,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        onValueChange = {onchangeField(it)},
         placeholder ={ Text(text = "Email")},
        keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine=true,
        maxLines = 1
        )
}

@Composable
fun ParwordField(password:String,onchangeField: (String)->Unit){

    TextField(
        value = password,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {onchangeField(it)},
        placeholder ={ Text(text = "Password")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine=true,
        maxLines = 1,
    )

}
@Composable
fun ForgoutPassword(modifier: Modifier){
    Text(text = "Olvidaste la contrasenia",
        modifier = modifier.clickable {  },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )


}
@Composable
fun LoginButton(loginEnabled:Boolean,onLoginSelected:()->Unit){
    Button(onClick = {onLoginSelected }, modifier = Modifier
        .fillMaxWidth()
        .height(48.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green,
        disabledBackgroundColor = Color.LightGray ,
        contentColor = Color.Black, disabledContentColor = Color.White), enabled = loginEnabled) {
        Text(text = "Login")

    }
}
@Composable
fun HeaderImage(modifier: Modifier){
Image(painter=painterResource(id = R.drawable.perfil),
    contentDescription = "fondo", modifier = modifier.clip(CircleShape).shadow(12.dp).size(180.dp))
}
