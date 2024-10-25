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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

class BookViewModelFactory(val application: Application)
    : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>) : T {
            return BookViewModel(application) as T
        }
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let{
                val viewModel: BookViewModel = viewModel(
                    it,
                    "BookViewModel",
                    BookViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                Main(viewModel)
            }
        }
    }
}

@Composable
fun Main(vm: BookViewModel = viewModel()) {
    val bookList by vm.bookList.observeAsState(listOf())
    val mContext = LocalContext.current
    Column{
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
        BookInsert(vm)
        Button(
            {vm.addBook()}, Modifier.padding(8.dp)
        ){
            Text("Добавить", fontSize = 22.sp)
        }
        BookList(books = bookList, delete = {vm.deleteBook(it)})
    }
}

@Composable
fun BookInsert(vm: BookViewModel = viewModel()){
    Column{
        OutlinedTextField(vm.idAuthor.toString(), modifier= Modifier.padding(8.dp), label = { Text("idAuthor") }, onValueChange = {vm.changeIdAuthor(it)})
        OutlinedTextField(vm.idGenre.toString(), modifier= Modifier.padding(8.dp), label = { Text("idGenre") }, onValueChange = {vm.changeIdGenre(it)})
        OutlinedTextField(vm.title, modifier= Modifier.padding(8.dp), label = { Text("Title") }, onValueChange = {vm.changeTitle(it)})
        OutlinedTextField(vm.year.toString(), modifier= Modifier.padding(8.dp), label = { Text("Year") }, onValueChange = {vm.changeYear(it)})
    }
}

@Composable
fun BookList(books: List<BookInfoTuple>, delete: (Int) -> Unit) {
    Column () {
        BookTitleRow()
        LazyColumn(Modifier.fillMaxWidth()) {
            items (books) {book -> BookRow(book, {delete(book.id)})}
        }
    }
}

@Composable
fun BookRow(book: BookInfoTuple, delete: (Int)->Unit){
    Row(Modifier.fillMaxWidth().padding(5.dp)){
        Text(book.id.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(book.author.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(book.genre.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(book.title, Modifier.weight(0.1f), fontSize = 22.sp)
        Text(book.year.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Удалить", Modifier.weight(0.2f).clickable{delete(book.id)})
    }
}


@Composable
fun BookTitleRow(){
    Row(Modifier.background(Color.LightGray).fillMaxWidth().padding(5.dp)){
        Text("Id", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Автор", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Жанр", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Заголовок", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Год", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
    }
}
