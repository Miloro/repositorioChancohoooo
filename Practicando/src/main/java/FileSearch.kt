import org.uqbar.commons.model.annotations.Observable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@Observable
class FileSearch(var name :String, var directories : MutableList<String>, var currentDirectory : String,
                 var files : MutableList<File>, var foundFiles : MutableList<File>, var selectedFile :File?, var search : String){

    init{
        var search = ""
        currentDirectory = "/home/ui"
        directories = mutableListOf("/","/etc","home/ui","/var","home/ui/code")
        files = mutableListOf(
                newFile("index",123,"/etc","10/09/1995"),
                newFile("caracoles batman",123,"/etc","10/09/1995"),
                newFile("home.jsx",1233,"/home/ui","10/09/1995"),
                newFile("pasaronCosas.jsx",123213233,"/","10/09/1995"),
                newFile("text",12323,"/var","10/09/1995"))
        foundFiles = files.take(3).toMutableList()
    }

    fun newFile(name : String , size : Int, path : String ,  date : String ) : File{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return File(name,size,path, LocalDate.parse(date,formatter))
    }

    fun searchFiles(){
        this.foundFiles = this.files.filter { file -> file.path == this.currentDirectory && file.name.contains(this.search)}.toMutableList()
    }
}