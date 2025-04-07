### Paso 1: Crear un nuevo proyecto en Android Studio

1. Abre Android Studio y selecciona "New Project".
2. Elige "Empty Activity" y haz clic en "Next".
3. Nombra tu proyecto como "ExampleEx".
4. Asegúrate de que el lenguaje sea Kotlin o Java, según tu preferencia.
5. Configura el "Minimum API level" según tus necesidades y haz clic en "Finish".

### Paso 2: Configurar permisos de Internet

Para conectarte a un servidor, necesitarás permisos de Internet. Abre el archivo `AndroidManifest.xml` y añade el siguiente permiso:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

### Paso 3: Crear el diseño de la pantalla principal

En `res/layout/activity_main.xml`, crea un diseño simple con un `RecyclerView` para mostrar la lista de contactos:

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</RelativeLayout>
```

### Paso 4: Crear el modelo de datos

Crea una clase de modelo para representar un contacto. Por ejemplo, `Contact.kt`:

```kotlin
data class Contact(
    val id: String,
    val name: String,
    val info: String // Puedes agregar más campos según lo que necesites
)
```

### Paso 5: Configurar el RecyclerView

Crea un adaptador para el `RecyclerView`. Por ejemplo, `ContactAdapter.kt`:

```kotlin
class ContactAdapter(private val contacts: List<Contact>, private val clickListener: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.contactName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.itemView.setOnClickListener { clickListener(contact) }
    }

    override fun getItemCount() = contacts.size
}
```

Crea un diseño para cada elemento de la lista, `res/layout/contact_item.xml`:

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/contactName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"/>
</LinearLayout>
```

### Paso 6: Obtener datos del servidor

En tu `MainActivity.kt`, realiza la solicitud al servidor para obtener la lista de contactos. Puedes usar `Retrofit` o `OkHttp` para esto. Aquí hay un ejemplo básico usando `OkHttp`:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchContacts()
    }

    private fun fetchContacts() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://ccardoso.multics.org/fca")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseData ->
                    // Aquí debes parsear la respuesta y llenar la lista de contactos
                    // Por simplicidad, supongamos que ya tienes una lista de contactos
                    // contacts.addAll(parsedContacts)

                    runOnUiThread {
                        contactAdapter = ContactAdapter(contacts) { contact ->
                            val intent = Intent(this@MainActivity, ContactDetailActivity::class.java)
                            intent.putExtra("contact_info", contact.info)
                            startActivity(intent)
                        }
                        recyclerView.adapter = contactAdapter
                    }
                }
            }
        })
    }
}
```

### Paso 7: Crear la pantalla de detalles del contacto

Crea una nueva actividad llamada `ContactDetailActivity.kt` y su diseño `activity_contact_detail.xml`:

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/contactInfo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:padding="16dp"/>
</RelativeLayout>
```

En `ContactDetailActivity.kt`, muestra la información del contacto:

```kotlin
class ContactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactInfoTextView: TextView = findViewById(R.id.contactInfo)
        val contactInfo = intent.getStringExtra("contact_info")
        contactInfoTextView.text = contactInfo
    }
}
```

### Paso 8: Probar la aplicación

Ahora puedes ejecutar tu aplicación en un emulador o dispositivo real. Asegúrate de que el servidor esté disponible y que la URL sea correcta.

### Notas finales

- Asegúrate de manejar correctamente los errores y las excepciones, especialmente al realizar solicitudes de red.
- Considera usar bibliotecas como `Gson` para el análisis de JSON si la respuesta del servidor está en ese formato.
- Puedes mejorar la interfaz de usuario y la experiencia del usuario según tus necesidades.

¡Espero que esta guía te ayude a comenzar con tu proyecto "ExampleEx"! Si tienes más preguntas o necesitas más detalles, no dudes en preguntar.