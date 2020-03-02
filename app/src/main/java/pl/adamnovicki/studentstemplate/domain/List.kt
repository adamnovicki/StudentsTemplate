package pl.ing.domain


class List {
    var dt: Int = 0

    var main: Main? = null

    var weather: MutableCollection<Weather>? = null

    var clouds: Clouds? = null

    var wind: Wind? = null

    var sys: Sys? = null

    var dtTxt: String? = null

    var rain: Rain? = null
}
