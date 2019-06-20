import org.uqbar.commons.model.annotations.Observable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Observable

class File (var name : String , var size : Int , var path : String , var updated : LocalDate){
    fun formattedUpdated(){
        val formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY")
        updated.format(formatter)
    }
    
}