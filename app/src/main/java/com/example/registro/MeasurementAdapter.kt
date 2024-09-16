package com.example.registro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registro.databinding.ItemMeasurementBinding

class MeasurementAdapter(private val measurements: List<Measurement>) :
    RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val binding = ItemMeasurementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MeasurementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val measurement = measurements[position]
        holder.bind(measurement)
    }

    override fun getItemCount(): Int = measurements.size

    class MeasurementViewHolder(private val binding: ItemMeasurementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(measurement: Measurement) {
            binding.textViewType.text = measurement.type
            binding.textViewMeterNumber.text = measurement.meterNumber
            binding.textViewDate.text = measurement.date
        }
    }
}
