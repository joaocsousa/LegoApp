package xyz.aranhapreta.feature.characters.usecases.contract

interface LoadMoreCharacters {
    suspend operator fun invoke()
}