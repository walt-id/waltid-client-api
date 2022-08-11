package id.walt.clientapi.model

data class InputDescriptorField (
  val path: List<String>,
  val id: String? = null,
  val purpose: String? = null,
  val filter: Map<String, Any>? = null
)
