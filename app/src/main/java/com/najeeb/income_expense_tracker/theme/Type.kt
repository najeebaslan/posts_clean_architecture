
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val appTypography = Typography(
  titleLarge = AppTypography.titleLarge,
  titleMedium = AppTypography.titleMedium,
  titleSmall = AppTypography.titleSmall,
  bodyLarge = AppTypography.bodyLarge,
  bodyMedium = AppTypography.bodyMedium,
  bodySmall = AppTypography.bodySmall,
  displayLarge = AppTypography.displayLarge,
  displayMedium = AppTypography.displayMedium,
  labelLarge = AppTypography.labelLarge,
  headlineLarge = AppTypography.headlineLarge




)


object AppTypography {
  // Font families would be defined in your font resources
  val defaultFontFamily = FontFamily.Default
  val defaultFontFamilyMedium = FontFamily.Default // Replace with actual medium font
  val defaultFontFamilySemiBold = FontFamily.Default // Replace with actual semi-bold font

  val titleLarge = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.W500,
    fontFamily = defaultFontFamilySemiBold
  )

  val titleMedium = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.W500,
    fontFamily = defaultFontFamilyMedium,
    letterSpacing = 0.1.sp
  )

  val titleSmall = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
    fontFamily = defaultFontFamilyMedium
  )

  val bodyLarge = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W500,
    fontFamily = defaultFontFamily,
    letterSpacing = 0.1.sp
  )

  val bodyMedium = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    fontFamily = defaultFontFamily
  )

  val bodySmall = TextStyle(
    fontSize = 13.sp,
    fontWeight = FontWeight.W400,
    fontFamily = defaultFontFamily,
    letterSpacing = 0.1.sp
  )

  val displayLarge = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 1.5.em
  )

  val displayMedium = TextStyle(
    fontSize = 16.sp,
    lineHeight = 1.5.em
  )

  val labelLarge = TextStyle(
    fontSize = 14.sp,
    lineHeight = 1.em
  )

  val headlineLarge = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.W600,
    fontFamily = defaultFontFamilyMedium
  )
}