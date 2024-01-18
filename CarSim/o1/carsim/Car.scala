package o1.carsim
import o1.Pos

class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos) {
  private var fuelLevel: Double = initialFuel
  private var currentLocation: Pos = initialLocation
  private var totalMetersDriven: Double = 0.0

  def location: Pos = currentLocation

  def fuel(toBeAdded: Double): Double = {
    val spaceAvailable = tankSize - fuelLevel
    val addedFuel = math.min(toBeAdded, spaceAvailable)
    fuelLevel += addedFuel
    addedFuel
  }

  def fuel(): Double = {
    val spaceAvailable = tankSize - fuelLevel
    fuelLevel += spaceAvailable
    spaceAvailable
  }

  def fuelRatio: Double = {
    if (tankSize == 0.0) then {
      0.0
    } else {
      (fuelLevel / tankSize) * 100.0
    }
  }

  def metersDriven: Double = totalMetersDriven

  def fuelRange: Double = {
    if fuelConsumption == 0.0 then {
      Double.MaxValue
    } else {
      (fuelLevel / fuelConsumption) * 100000.0 // Convert to meters
    }
  }

  def drive(destination: Pos, metersToDestination: Double): Unit = {
    val distanceToDestination = currentLocation.distance(destination)

    if fuelLevel > 0 && distanceToDestination > 0 then {
      val fuelRequired = (distanceToDestination / 100000.0) * fuelConsumption // Convert to liters
      val fuelAvailable = fuelLevel

      if (fuelRequired <= fuelAvailable) then
        // Car has enough fuel to reach the destination
        currentLocation = destination
        totalMetersDriven += metersToDestination
        fuelLevel -= fuelRequired
       else {
        // Car will run out of fuel before reaching the destination
        val fuelToDestination = (fuelAvailable / fuelRequired) * metersToDestination
        val ratio = fuelToDestination / distanceToDestination
        val newX = currentLocation.x + ratio * (destination.x - currentLocation.x)
        val newY = currentLocation.y + ratio * (destination.y - currentLocation.y)
        currentLocation = Pos(newX, newY)
        totalMetersDriven += fuelToDestination
        fuelLevel = 0.0
      }
    }
  }
}
