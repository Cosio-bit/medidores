package com.example.registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registro.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val measurements = mutableListOf<Measurement>() // Lista de mediciones
    private lateinit var adapter: MeasurementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el RecyclerView y el adaptador
        adapter = MeasurementAdapter(measurements)
        binding.recyclerViewMeasurements.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMeasurements.adapter = adapter

        // Recibir los datos del SecondFragment y añadir a la lista de mediciones
        setFragmentResultListener("requestKey") { _, bundle ->
            val meterNumber = bundle.getString("meterNumber")
            val date = bundle.getString("date")
            val meterType = bundle.getString("meterType")

            // Crear un nuevo objeto Measurement y agregarlo a la lista
            val newMeasurement = Measurement(meterType ?: "", meterNumber ?: "", date ?: "")
            measurements.add(newMeasurement)
            adapter.notifyItemInserted(measurements.size - 1)
        }

        // Configurar el FloatingActionButton para navegar al SecondFragment
        binding.fabAddMeasurement.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
