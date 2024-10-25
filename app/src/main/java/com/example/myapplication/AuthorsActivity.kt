package com.example.myapplication

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

class AuthorViewModelFactory(val application: Application)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return AuthorViewModel(application) as T
    }
}

class AuthorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let{
                val viewModel: AuthorViewModel = viewModel(
                    it,
                    "BookViewModel",
                    AuthorViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                AuthorMain(viewModel)
            }
        }
    }
}

@Composable
fun AuthorMain(vm: AuthorViewModel = viewModel()) {
    val authorList by vm.authorList.observeAsState(listOf())
    Column{
        AuthorInsert(vm)
        Button(
            {vm.addAuthor()}, Modifier.padding(8.dp)
        ){
            Text("Добавить", fontSize = 22.sp)
        }
        AuthorList(authors = authorList, delete = {vm.deleteAuthor(it)})
    }
}

@Composable
fun AuthorInsert(vm: AuthorViewModel = viewModel()){
    Column{
        OutlinedTextField(vm.name, modifier= Modifier.padding(8.dp), label = { Text("Name") }, onValueChange = {vm.changeName(it)})
    }
}

@Composable
fun AuthorList(authors: List<AuthorEntity>, delete: (Int) -> Unit) {
    val mContext = LocalContext.current
    Column () {
        Row{
            Button(
                onClick = {
                    mContext.startActivity(Intent(mContext, GenreActivity::class.java))
                }
            ){
                Text("Жанры")
            }

            Button(
                onClick = {
                    mContext.startActivity(Intent(mContext, AuthorsActivity::class.java))
                }
            ){
                Text("Авторы")
            }

            Button(
                onClick = {
                    mContext.startActivity(Intent(mContext, MainActivity::class.java))
                }
            ){
                Text("Книги")
            }
        }
        AuthorTitleRow()
        LazyColumn(Modifier.fillMaxWidth()) {
            items (authors) {author -> AuthorRow(author, {delete(author.id)})}
        }
    }
}

@Composable
fun AuthorRow(author: AuthorEntity, delete: (Int)->Unit){
    Row(Modifier.fillMaxWidth().padding(5.dp)){
        Text(author.id.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(author.name, Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Удалить", Modifier.weight(0.2f).clickable{delete(author.id)})
    }
}


@Composable
fun AuthorTitleRow(){
    Row(Modifier.background(Color.LightGray).fillMaxWidth().padding(5.dp)){
        Text("Id", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("ФИО", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
    }
}