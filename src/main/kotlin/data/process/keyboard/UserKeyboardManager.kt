package data.process.keyboard

import org.jire.arrowhead.keyPressed
import java.awt.Robot
import java.awt.event.InputEvent

class UserKeyboardManager {
    private val robot = Robot()

    fun leftClick() {
        with(robot) {
            mousePress(InputEvent.BUTTON1_MASK)
            delay((60..100).random())
            mouseRelease(InputEvent.BUTTON1_MASK)
        }
    }

    fun mouseWheelDown() {
        with(robot) {
            mouseWheel((60..100).random())
            delay((60..100).random())
            mouseWheel((60..100).random())
        }
    }

    fun keyPress(keyCode: Int) {
        with(robot) {
            keyPress(keyCode)
            delay((60..100).random())
            keyRelease(keyCode)
        }
    }

    fun isKeyPressed(keyCode: Int) = keyPressed(keyCode)
}