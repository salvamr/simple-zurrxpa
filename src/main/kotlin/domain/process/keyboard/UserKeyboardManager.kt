package domain.process.keyboard

import org.jire.arrowhead.keyPressed
import java.awt.Robot
import java.awt.event.InputEvent

class UserKeyboardManager(private val robot: Robot) {
    fun leftClick() {
        with(robot) {
            mousePress(InputEvent.BUTTON1_MASK)
            delay(addDelay())
            mouseRelease(InputEvent.BUTTON1_MASK)
        }
    }

    fun mouseWheelDown() {
        with(robot) {
            mouseWheel(addDelay())
            delay(addDelay())
            mouseWheel(addDelay())
        }
    }

    fun keyPress(keyCode: Int) {
        with(robot) {
            keyPress(keyCode)
            delay(addDelay())
            keyRelease(keyCode)
        }
    }

    fun isKeyPressed(keyCode: Int) = keyPressed(keyCode)

    private fun addDelay() = (60..100).random()
}