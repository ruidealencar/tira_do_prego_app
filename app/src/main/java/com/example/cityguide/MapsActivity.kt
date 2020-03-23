package com.example.cityguide

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Double


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    override fun onMarkerClick(p0: Marker?) = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var map: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        var latitude: String? = ""
        var longitude: String? = ""

        setUpMap()

            val call = RetrofitInitializer().apiRetrofitService().getPrestador()

            call.enqueue(object :  Callback<List<Prestador>> {

                override fun onResponse(call: Call<List<Prestador>>?, response: Response<List<Prestador>>?) {

                    response?.let {

                        val prestadores: List<Prestador>? = response.body()

                        val qt = prestadores?.size?.toInt()
                        var contador = 0



                            while (contador < qt!!) {
                                println(contador)
                                latitude = prestadores?.get(contador)?.latitude
                                longitude = prestadores?.get(contador)?.longitude
                                val borracharia = LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude) )
                                if (prestadores?.get(contador)?.tipo == "borracharia"){
                                    map.addMarker(
                                        MarkerOptions().position(borracharia).title(prestadores?.get(contador)?.nome).snippet("Horario de Funcionamento: "+prestadores?.get(contador)?.horario).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)
                                        )
                                    )
                                }
                                else{
                                    map.addMarker(
                                        MarkerOptions().position(borracharia).title(prestadores?.get(contador)?.nome).snippet("Telefone: "+prestadores?.get(contador)?.telefone).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_reboque)
                                        )
                                    )
                                }
                                contador++
                            }


                        Log.i("Prestador", prestadores.toString())

                    }
                }

                override fun onFailure(call: Call<List<Prestador>>?, t: Throwable?) {
                    Log.e("Erro", t?.message)
                }
            })

        val fortaleza = LatLng(-3.76, -38.55)


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(fortaleza, 10.0f))

        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        map.isMyLocationEnabled = true

    }


}

