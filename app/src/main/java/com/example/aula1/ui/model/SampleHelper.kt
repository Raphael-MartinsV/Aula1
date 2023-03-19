package com.example.aula1.ui.model

import com.example.aula1.ui.utils.sampleConst1
import com.example.aula1.ui.utils.sampleConst2
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleHelper(
    val title: CharSequence,
    val description: CharSequence
)

class InfoHelperBuilder {
    var title: CharSequence = ""
    var description: CharSequence = ""

    //realiza o build do SampleHelper
    fun build(): SampleHelper = SampleHelper(title, description)
}

//configura o builder referente ao infoHelper
fun infoHelper(block: InfoHelperBuilder.() -> Unit): SampleHelper =
    InfoHelperBuilder().apply(block).build()

//função cria um mapper que retornar um infoHelper baseado na string informada
fun setInfoHelper(): Map<String, SampleHelper> = mapOf(
    sampleConst1 to infoHelper {
        title = "Texto1"
        description= "Texto texto"
    },
    sampleConst2 to infoHelper {
        title = "Texto1"
        description= "Texto texto"
    },
)
