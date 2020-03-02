package pl.ing.domain


class WeatherRsp {
    var cod: String? = null

    var message: Double = 0.toDouble()

    var cnt: Int = 0

    var list: MutableCollection<List>? = null

    var city: City? = null

    override fun toString(): String {
        return "WeatherRsp(cod=$cod, message=$message, cnt=$cnt, list=$list, city=$city)"
    }
}
