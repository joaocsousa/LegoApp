package xyz.aranhapreta.feature.characters.entities

sealed interface Status {
    data object Alive : Status
    data object Dead : Status
    data object Unknown : Status
}