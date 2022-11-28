package by.mankevich.currencyexchanger.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    private val viewModelClass: Class<VM>
) : AppCompatActivity() {

    protected lateinit var viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = checkNotNull(_binding) {
        "Binding isn't initialized"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDaggerComponent()
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory)[viewModelClass]

        _binding = initBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected abstract fun initDaggerComponent()

    protected abstract fun initBinding(): VB

    protected open fun initListeners() = Unit
}
