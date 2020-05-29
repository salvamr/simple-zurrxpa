package domain.model

import com.sun.javafx.geom.Vec3d
import org.jire.arrowhead.Struct

class VectorX(_x: Double = 0.0, _y: Double = 0.0, _z: Double = 0.0) : Vec3d(_x, _y, _z) {
    fun calculateAngle(otherVector: VectorX): Double = Math.toDegrees(dot(otherVector) / (length() * otherVector.length()))
}