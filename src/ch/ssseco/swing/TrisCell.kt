package ch.ssseco.swing

import java.awt.Dimension
import javax.swing.JButton

class TrisCell(title: String) : JButton(title) {
    companion object {
        var CELL_SIZE = Dimension(100, 100)
    }
    
    init {
        size = CELL_SIZE
        preferredSize = CELL_SIZE
    }
}