import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder

class FileMainWindows(model : FileSearch) : MainWindow<FileSearch>(model){
    override fun createContents(mainPanel : Panel) {
        this.title = "Buscador De Archivos"
        mainPanel.setLayout(VerticalLayout())
        val panel2 = Panel(mainPanel)
        panel2.setLayout(HorizontalLayout())
        val selector = Selector<String>(panel2)
        selector.bindValueToProperty<String, ControlBuilder>("currentDirectory")
        selector.bindItems<String>(ObservableProperty(model, "directories"))
        val input = TextBox(panel2)
                .setWidth(300)
                .bindValueToProperty<String, ControlBuilder>("search")
        val boton = Button(panel2)
                .setCaption("buscar")
                .setAsDefault()
                .onClick{model.source.searchFiles()}

        val panel3 = Panel(mainPanel)
        val table = Table<File>(panel3,File::class.java)
        table.setNumberVisibleRows(5)
        table.bindValueToProperty<FileSearch, ControlBuilder>("selectedFile")
        table.bindItemsToProperty("foundFiles")
        Column<File>(table)
                .setTitle("Nombre del archivo")
                .setFixedSize(150)
                .bindContentsToProperty("name")
        Column<File>(table)
                .setTitle("Tamaño")
                .setFixedSize(50)
                .bindContentsToProperty("size")
        Column<File>(table)
                .setTitle("Modificado")
                .setFixedSize(50)
                .bindContentsToProperty("updated")
        Column<File>(table)
                .setTitle("Ubicacion")
                .setFixedSize(250)
                .bindContentsToProperty("path")
        
    }



}


fun main(args: Array<String>){
    FileMainWindows(FileSearch("file", mutableListOf(),"", mutableListOf(),
            mutableListOf(),null,"")).startApplication()
    }