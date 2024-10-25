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

class GenreViewModelFactory(val application: Application)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return GenreViewModel(application) as T
    }
}

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let{
                val viewModel: GenreViewModel = viewModel(
                    it,
                    "BookViewModel",
                    GenreViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                GenreMain(viewModel)
            }
        }
    }
}

@Composable
fun GenreMain(vm: GenreViewModel = viewModel()) {
    val genreList by vm.genreList.observeAsState(listOf())
    Column{
        GenreInsert(vm)
        Button(
            {vm.addGenre()}, Modifier.padding(8.dp)
        ){
            Text("Добавить", fontSize = 22.sp)
        }
        GenreList(genries = genreList, delete = {vm.deleteGenre(it)})
    }
}

@Composable
fun GenreInsert(vm: GenreViewModel = viewModel()){
    Column{
        OutlinedTextField(vm.type, modifier= Modifier.padding(8.dp), label = { Text("Type") }, onValueChange = {vm.changeType(it)})
    }
}

@Composable
fun GenreList(genries: List<GenreEntity>, delete: (Int) -> Unit) {
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
        GenreTitleRow()
        LazyColumn(Modifier.fillMaxWidth()) {
            items (genries) {genre -> GenreRow(genre, {delete(genre.id)})}
        }
    }
}

@Composable
fun GenreRow(genre: GenreEntity, delete: (Int)->Unit){
    Row(Modifier.fillMaxWidth().padding(5.dp)){
        Text(genre.id.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(genre.type, Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Удалить", Modifier.weight(0.2f).clickable{delete(genre.id)})
    }
}


@Composable
fun GenreTitleRow(){
    Row(Modifier.background(Color.LightGray).fillMaxWidth().padding(5.dp)){
        Text("Id", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Тип", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
    }
}