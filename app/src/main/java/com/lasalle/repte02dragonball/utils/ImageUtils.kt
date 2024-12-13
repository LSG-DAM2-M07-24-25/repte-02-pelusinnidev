fun getCharacterImage(characterId: Int): Int {
    return when (characterId) {
        0 -> R.drawable.goku
        1 -> R.drawable.vegeta
        2 -> R.drawable.gohan
        3 -> R.drawable.piccolo
        4 -> R.drawable.trunks
        5 -> R.drawable.goten
        else -> R.drawable.goku
    }
} 