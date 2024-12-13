import com.lasalle.repte02dragonball.R

fun getCharacterImage(index: Int): Int {
    return when(index) {
        0 -> R.drawable.goku
        1 -> R.drawable.gomah
        2 -> R.drawable.masked_majin
        3 -> R.drawable.piccolo
        4 -> R.drawable.supreme
        5 -> R.drawable.vegeta
        else -> R.drawable.goku
    }
}