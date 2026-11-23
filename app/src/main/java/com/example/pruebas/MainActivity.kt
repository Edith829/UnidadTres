package com.example.pruebas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import android.provider.Settings
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.pruebas.ui.theme.PruebasTheme
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.annotation.Contract
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext




class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplash }

        lifecycleScope.launch {
            delay(1000)
            keepSplash = false
        }

        super.onCreate(savedInstanceState)
        ShowToast("Funcion OnCreate()")
        enableEdgeToEdge()
        setContent {

// Cambiar al nombre del tema de su proyecto
            PruebasTheme {
                ViewContainer()
            }
        }
    }

    override fun onStart(){
        super.onStart()
        ShowToast("Función onStart")
    }

    override fun onResume(){
        super.onResume()
        ShowToast("Función onResume")
        Log.i(TAG, "La app entro a onResume")
    }
    override fun onPause() {
        super.onPause()
        ShowToast("Función onPause")
        Log.i(TAG, "La app entro a onPause")
    }

    override fun onStop() {
        super.onStop()
        ShowToast("Función onStop")
        Log.i(TAG, "La app entro a onStop")
    }

    override fun onRestart() {
        super.onRestart()
        ShowToast("Función onRestart")
        Log.i(TAG, "La app entro a onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        ShowToast("Función onDestroy")
        Log.i(TAG, "La app entro a onDestroy")
    }


    fun ShowToast(message:String){
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        )
       //toast.show()
        Log.i(TAG, message);
    }

}

//  Preview horizontal
// device = "spec:width=411dp,height=891dp, orientation=landscape"

@SuppressLint("UnusedMateriaal3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    locale = "en-rUS"
    )

@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current



    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.options_menu),
                tint= Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.profile)) },
                onClick = {
                    expanded = false
                // Acción para Perfil
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.config)) },
                onClick = {
                    expanded = false
                // Acción para Configuración
                },
                leadingIcon = {
                    Icon(Icons.Default.Settings, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.call)) },
                onClick = {
                    expanded = false
                    val intent = Intent (Intent.ACTION_DIAL).apply{
                        data = Uri.parse("tel:4491147177")
                    }
                    context.startActivity(intent)
                    // Acción para Llamada
                },
                leadingIcon = {
                    Icon(Icons.Default.Call, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.bluetooth)) },
                onClick = {
                    expanded = false
                    val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
                    context.startActivity(intent)
                    // Acción para Bluetooth
                },
                leadingIcon = {
                    Icon(Icons.Default.Bluetooth, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.contacts)) },
                onClick = {
                    expanded = false
                    val intent = Intent (Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    context.startActivity(intent)
                    // Acción para Contactos
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = { Text(stringResource(R.string.logout)) },
                onClick = {
                    expanded = false
// Acción para Cerrar sesión
                },
                leadingIcon = {
                    Icon(Icons.Default.ExitToApp, contentDescription = null)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ViewContainer() {
    val intentContext = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Menú de Navegación",
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
// val intentConte = LocalContext.current
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.home_screen))
                        Text(
                            text = "Inicio",
                            color = Color.White,
                            fontSize = 30.sp
                        )
                    },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme
                            .secondary,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    },

                    actions = {
                        IconButton(onClick = { openWebScreen(context) }) {
                            Icon(
                                imageVector = Icons.Default.AddLink,
                                contentDescription = "Marcar como favorito",
                                tint = Color.White
                            )
                        }
                        val intentContext = LocalContext.current
                        IconButton(
                            onClick =
                                {
                                    val intent = Intent(
                                        intentContext,
                                        SecondActivity::class.java
                                    )
                                    intentContext.startActivity(intent)
                                }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartir",
                                tint = Color.White
                            )
                        }
                        DropDownMenu()
                    }
                )
            },

            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.bottom_app_bar),
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }
            },

            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val toast = Toast.makeText(
                        intentContext,
                        R.string.home_fab_text,
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Presionaste el botón"
                        )
                    }
                }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource
                            (R.string.home_fab_text)
                    )
                }

            },

            )
        { innerPadding ->
            ContentView(
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}

fun openWebScreen(context: Context){
    try {
        val url = Uri.parse("https://aguascalientes.tecnm.mx/")
        val intent = Intent(Intent.ACTION_VIEW, url)
        context.startActivity(intent)
    } catch (e:Exception){
        Toast.makeText(context, "No hay un navegador instalado", Toast.LENGTH_SHORT).show()
    } //catch (e: Exception) {
        //Log.e("MiApp", "Error al abrir la página", e)
        //Toast.makeText(context, "No se pudo abrir la página", Toast.LENGTH_SHORT).show()
    //}
}

@Composable
fun ContentView(modifier: Modifier) {
    var counter by rememberSaveable { mutableStateOf (0)}



    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
        ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.android1920x1080),
                contentDescription = "Ilustración de Android",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 65.dp)
            )
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.baseline_cruelty_free_24),
                    contentDescription = "Like",
                    modifier = Modifier.clickable { counter++ }
                        .size(100.dp)
                        .height(200.dp)
                        .padding(start = 10.dp)

                )
                Text(
                    text = counter.toString(),
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier

                )
            }

            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.nombre),
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.apellido),
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 7.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            {
                item {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im1),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im2),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im3),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im1),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im2),
                        contentDescription = "Mobile Apps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.im3),
                        contentDescription = "Mobile Apps"
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaCompletaPreview() {
    PruebasTheme {
        ViewContainer()
    }
}


