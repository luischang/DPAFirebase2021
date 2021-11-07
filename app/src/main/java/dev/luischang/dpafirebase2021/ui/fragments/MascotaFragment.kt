package dev.luischang.dpafirebase2021.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Distribution
import dev.luischang.dpafirebase2021.R
import dev.luischang.dpafirebase2021.ui.fragments.adapter.MascotaAdapter
import dev.luischang.dpafirebase2021.ui.fragments.client.MascotaClient
import dev.luischang.dpafirebase2021.ui.fragments.model.MascotaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_mascota, container, false)
        val rvMascota: RecyclerView = view.findViewById(R.id.rvMascota)
        rvMascota.layoutManager = LinearLayoutManager(requireActivity())

        val call: Call<List<MascotaModel>> = MascotaClient
            .retrofitService.listarMascota()

        call.enqueue(object: Callback<List<MascotaModel>>{
            override fun onResponse(
                call: Call<List<MascotaModel>>,
                response: Response<List<MascotaModel>>
            ) {
                rvMascota.adapter = MascotaAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<MascotaModel>>, t: Throwable) {
                Log.e("Error","Error en listar las mascotas..")
            }
        })
        return view
    }
}