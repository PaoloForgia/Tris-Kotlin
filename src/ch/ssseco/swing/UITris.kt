package ch.ssseco.swing

import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel

class UITris : JFrame("Tris") {

    init {
        createUI()
    }

    private fun createUI() {
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
        isResizable = false
        layout = BorderLayout()

        createField()

        // Finalize window
        pack()
        isVisible = true
    }

    private fun createField() {
        val fieldPanel = JPanel(GridLayout(3, 3))

        for (i in 0..2) {
            for (j in 0..2) {
                fieldPanel.add(TrisCell("$i|$j"))
            }
        }
        add(fieldPanel, BorderLayout.CENTER)
    }
}