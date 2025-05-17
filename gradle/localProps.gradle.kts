import java.util.Properties

val localPropsFile = rootProject.file("local.properties")
val localProps = Properties().apply {
    if (localPropsFile.exists()) load(localPropsFile.inputStream())
}

extra["localProps"] = localProps