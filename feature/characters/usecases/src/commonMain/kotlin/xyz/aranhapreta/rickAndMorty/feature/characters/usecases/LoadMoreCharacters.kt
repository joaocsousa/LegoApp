package xyz.aranhapreta.rickAndMorty.feature.characters.usecases

interface LoadMoreCharacters {
    suspend operator fun invoke()
}