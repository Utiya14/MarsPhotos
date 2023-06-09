package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

//Kelas ini mengimplementasikan [RecyclerView] [ListAdapter] yang menggunakan Data Binding untuk
//menampilkan data [List], termasuk menghitung perbedaan antara daftar.
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {
    //Konstruktor MarsPhotosViewHolder mengambil variabel binding dari GridViewItem terkait,
    //yang memberikan akses ke informasi [MarsPhoto] lengkap.
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // memaksa data binding untuk segera dieksekusi,
            // yang memungkinkan RecyclerView melakukan pengukuran ukuran tampilan yang tepat
            binding.executePendingBindings()
        }
    }
    //Memungkinkan RecyclerView menentukan item mana yang telah berubah ketika [List] dari
    //[MarsPhoto] diperbarui.
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
    //Membuat tampilan item [RecyclerView] baru (dipanggil oleh layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
    // Mengganti isi dari suatu tampilan (dipanggil oleh layout manager)
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
